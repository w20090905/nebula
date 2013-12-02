package nebula.lang;

import java.io.File;
import java.util.List;
import java.util.Map;

import nebula.data.Entity;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import util.NamesEncoding;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public class AsmCompiler implements Opcodes, CompilerBase {
	static Map<String, OperatorExpr> opTypes = Maps.newHashMap();

	static OperatorExpr resolveOperator(Type type) {
		OperatorExpr op = opTypes.get(type.getName());
		if (op == null && type.getSuperType() != null) op = resolveOperator(type.getSuperType());
		Preconditions.checkNotNull(op);
		return op;
	}

	final ClassWriter cw;

	// final CompilerContext context;

	final MethodVisitor mv;

	public AsmCompiler(ClassWriter cw, MethodVisitor mv) {
		this.cw = cw;
		this.mv = mv;
		// this.context = context;
		opTypes.put(RawTypes.Long.name(), new LongOperator());
		// if (log.isDebugEnabled()) {
		if (!new File("tmp").exists()) new File("tmp/").mkdir();
		// }
	}

	@Override
	public void arithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		resolveOperator(e1.getType()).arithmetic(this, op, e1, e2);
	}

	@Override
	public void block(List<Statement> statements) {
		for (Statement st : statements) {
			st.compile(this);
		}
	}

	@Override
	public void call(Expr<Object> value) {
		value.compile(this);
	}

	@Override
	public void callMethod(Expr<Entity> e1, String actionName) {
		Type type = e1.getType();
		type = type.getActionByName(actionName).getResideType();

		String name = EntityAction.class.getSimpleName() + "_" + NamesEncoding.encode(type.getFullName(), false) + "_"
				+ NamesEncoding.encode(actionName, false);
		String internalName = name.replace('.', '/');

		mv.visitFieldInsn(GETSTATIC, internalName, "instance", "Lnebula/lang/EntityAction;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitVarInsn(ALOAD, 2);
		e1.compile(this);
		mv.visitMethodInsn(INVOKEINTERFACE, "nebula/lang/EntityAction", "exec", "(Lnebula/lang/RuntimeContext;Lnebula/data/DataRepos;Lnebula/data/Entity;)V");
	}

	@Override
	public void conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
		// compiles e1
		e1.compile(this);
		// tests if e1 is false
		mv.visitInsn(DUP);
		Label end = new Label();
		if (op == Operator.AND) {
			mv.visitJumpInsn(IFEQ, end);
		} else if (op == Operator.OR) {
			mv.visitJumpInsn(IFNE, end);
		} else {
			throw new UnsupportedOperationException();
		}
		// case where e1 is true : e1 && e2 is equal to e2
		mv.visitInsn(POP);
		e2.compile(this);
		// if e1 is false, e1 && e2 is equal to e1:
		// we jump directly to this label, without evaluating e2
		mv.visitLabel(end);
	}

	@Override
	public void constDate(long value) {
		constTempral(value);
	}

	@Override
	public void constDatetime(long value) {
		constTempral(value);
	}

	@Override
	public void constDecimal(String text) {
		mv.visitTypeInsn(NEW, "java/math/BigDecimal");
		mv.visitInsn(DUP);
		mv.visitLdcInsn(text);
		mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");
	}

	@Override
	public void constLong(Long value) {
		mv.visitLdcInsn(value);
	}

	@Override
	public void constString(String value) {
		mv.visitLdcInsn(value);
	}

	private void constTempral(long value) {
		mv.visitTypeInsn(NEW, "org/joda/time/DateTime");
		mv.visitInsn(DUP);
		mv.visitLdcInsn(value);
		mv.visitMethodInsn(INVOKESPECIAL, "org/joda/time/DateTime", "<init>", "(J)V");
	}

	@Override
	public void constTime(long value) {
		constTempral(value);
	}

	@Override
	public void constTimestamp(long value) {
		constTempral(value);
	}

	@Override
	public void constYesno(int value) {
		mv.visitLdcInsn(value);
	}

	@Override
	public void datastoreGet(Expr<Object> repos, String name) {
		repos.compile(this);
		mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
		mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
		mv.visitLdcInsn(name);
		mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/DataStore;");
		mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");
		mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore", "listAll", "()Ljava/util/List;");
	}

	@Override
	public void decrement(Expr<Object> e1) {
		resolveOperator(e1.getType()).decrement(this, e1);
	}

	protected void fromObject(final MethodVisitor mv, Type type) {
		switch (type.getRawType()) {
		case Boolean:
			mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z");
			break;
		case Long:
			mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J");
			break;
		case String:
			mv.visitTypeInsn(CHECKCAST, "java/lang/String");
			break;
		default:
			break;
		}
	}

	@Override
	public void get(Expr<Object> list, Expr<Object> index) {
		list.compile(this);
		mv.visitTypeInsn(CHECKCAST, "java/util/List");
		// index.index.compile(this);
		index.compile(this);
		mv.visitInsn(L2I);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
		mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
		// mv.visitInsn(POP);

	}

	@Override
	public void getField(Expr<Object> entity, String name, Type fieldType) {
		entity.compile(this);
		mv.visitLdcInsn(name);
		mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
		fromObject(mv, fieldType);
	}

	@Override
	public void increment(Expr<Object> e1) {
		resolveOperator(e1.getType()).increment(this, e1);
	}

	@Override
	public void listFilter(Expr<Object> list, List<Expr<Object>> ranges) {
		list.compile(this);

		mv.visitIntInsn(BIPUSH, ranges.size());
		mv.visitTypeInsn(ANEWARRAY, "nebula/lang/Range");

		for (int i = 0; i < ranges.size(); i++) {
			mv.visitInsn(DUP);
			mv.visitIntInsn(BIPUSH, i);
			ranges.get(i).compile(this);
			mv.visitInsn(AASTORE);
		}

		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/NebulaNative", "filter", "(Ljava/util/List;[Lnebula/lang/Range;)Ljava/util/List;");
	}

	@Override
	public void listFilterByClause(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {
		list.compile(this);
		String clauseName = EntityClauseComplier.DEFAULT.compile(list.getType(), clause); // (clause,
																							// context);
		mv.visitTypeInsn(NEW, clauseName);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, clauseName, "<init>", "()V");

		mv.visitIntInsn(BIPUSH, params.size());
		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

		for (int i = 0; i < params.size(); i++) {
			mv.visitInsn(DUP);
			mv.visitIntInsn(BIPUSH, i);

			params.get(i).compile(this);
			toObject(mv, params.get(i).getType());

			mv.visitInsn(AASTORE);
		}

		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/NebulaNative", "filter", "(Ljava/util/List;Lnebula/lang/Clause;[Ljava/lang/Object;)Ljava/util/List;");

	}

	@Override
	public void listGetItem(Expr<Object> list, int index) {
		// list TODO
		mv.visitVarInsn(ALOAD, index);

	}

	public void longArithmetic(Expr<Object> e1, Expr<Object> e2, int op) {
		e1.compile(this);
		e2.compile(this);
		mv.visitInsn(op);
	}

	public void longDecrement(Expr<Object> e1) {
		e1.compile(this);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LSUB);
	}

	public void longIncrement(Expr<Object> e1) {
		e1.compile(this);
		mv.visitInsn(LCONST_1);
		mv.visitInsn(LADD);
	}

	public void longNegates(Expr<Object> e1) {
		e1.compile(this);
		mv.visitInsn(LNEG);
	}

	public <V> void longRelational(final AsmCompiler compiler, Expr<V> e1, Expr<V> e2, int op) {
		e1.compile(compiler);
		e2.compile(compiler);

		mv.visitInsn(LCMP);
		Label ifFalse = new Label();
		mv.visitJumpInsn(op, ifFalse);
		mv.visitInsn(ICONST_1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		mv.visitLabel(ifFalse);
		mv.visitInsn(ICONST_0);
		mv.visitLabel(end);
	}

	@Override
	public void makeRange_0_To(Expr<Object> to) {
		to.compile(this);
		mv.visitInsn(L2I);
		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "atMost", "(I)Lnebula/lang/Range;");

	}

	@Override
	public void makeRange_From(Expr<Object> from) {
		from.compile(this);
		mv.visitInsn(L2I);
		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "atLeast", "(I)Lnebula/lang/Range;");

	}

	@Override
	public void makeRange_From_To(Expr<Object> from, Expr<Object> to) {
		from.compile(this);
		mv.visitInsn(L2I);
		to.compile(this);
		mv.visitInsn(L2I);

		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "closed", "(II)Lnebula/lang/Range;");

	}

	@Override
	public void makeRangeIndex(Expr<Object> index) {
		index.compile(this);
		mv.visitInsn(L2I);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "closed", "(II)Lnebula/lang/Range;");

	}

	@Override
	public void negates(Expr<Object> e1) {
		resolveOperator(e1.getType()).negates(this, e1);
	}

	@Override
	public void not(Expr<Boolean> e1) {
		// computes !e1 by evaluating 1 - e1
		mv.visitLdcInsn(new Integer(1));
		e1.compile(this);
		mv.visitInsn(ISUB);
	}

	@Override
	public void paramsRefer(Expr<Object> in, int params, int index) {
		mv.visitVarInsn(ALOAD, params);
		mv.visitIntInsn(SIPUSH, index);
		mv.visitInsn(AALOAD);
		fromObject(mv, in.getType());
	}

	@Override
	public void positive(Expr<Object> e1) {
		resolveOperator(e1.getType()).positive(this, e1);
	}

	@Override
	public void putVar(Var var, Expr<Object> initExpr) {
		// compiles e1, e2, and adds an instruction to multiply the two
		// values
		// mv.visitLdcInsn(value);
	}

	@Override
	public <V> void relational(final Operator op, Expr<V> e1, Expr<V> e2) {
		resolveOperator(e1.getType()).relational(this, op, e1, e2);
	}

	@Override
	public void setField(Expr<Object> parent, String name, Type fieldType, Expr<Object> value) {
		parent.compile(this);
		mv.visitLdcInsn(name);
		value.compile(this);
		toObject(mv, value.getType());
		mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put", "(Ljava/lang/String;Ljava/lang/Object;)V");
	}

	protected void toObject(final MethodVisitor mv, Type type) {
		switch (type.getRawType()) {
		case Boolean:
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
			break;
		case Long:
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
			break;
		default:
			break;
		}
	}

	// @Override
	// public void typeRefer(String name) {
	// mv.visitVarInsn(ALOAD, Compiler.REPOS);
	// mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
	// mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
	// mv.visitLdcInsn(name);
	// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define",
	// "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
	// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get",
	// "()Ljava/lang/Object;");
	// mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");
	//
	// //
	// //
	// // mv.visitVarInsn(ALOAD, 3);
	// // mv.visitLdcInsn("wangshilian");
	// // mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore",
	// // "get", "(Ljava/lang/Object;)Lnebula/data/Timable;");
	// // mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
	// // mv.visitVarInsn(ASTORE, 4);
	// //
	// //
	// // mv.visitVarInsn(ALOAD, 1);
	// // mv.visitLdcInsn("Age");
	// // mv.visitVarInsn(ALOAD, 4);
	// // mv.visitLdcInsn("Age");
	// // mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get",
	// // "(Ljava/lang/String;)Ljava/lang/Object;");
	// // mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put",
	// // "(Ljava/lang/String;Ljava/lang/Object;)V");
	//
	// }

	public void varRefer(int index) {
		mv.visitVarInsn(ALOAD, index);
	}

	@Override
	public void varRefer(Var var) {
		mv.visitVarInsn(ALOAD, var.index);
	}
}

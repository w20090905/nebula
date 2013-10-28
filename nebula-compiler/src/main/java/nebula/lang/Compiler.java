package nebula.lang;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import nebula.data.DataRepos;
import nebula.data.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public class Compiler {
	Log log = LogFactory.getLog(getClass());
	public final static int SYSTEM_THIS = 0;
	public final static int CONTEXT = 1;
	public final static int REPOS = 2;
	public final static int THIS = 3;
	public final static int PARAMS = 4;

	CompilerContext context;

	static Map<String, OperatorExpr> opTypes = Maps.newHashMap();

	static OperatorExpr resolveOperator(Type type) {
		OperatorExpr op = opTypes.get(type.getName());
		if (op == null && type.getSuperType() != null) op = resolveOperator(type.getSuperType());
		Preconditions.checkNotNull(op);
		return op;
	}

	Compiler(CompilerContext context) {
		this.context = context;
		opTypes.put(RawTypes.Long.name(), new LongOperator());
		if (log.isDebugEnabled()) {
			if (!new File("tmp").exists()) new File("tmp/").mkdir();
		}
	}

	EntityExpressionComplier exprCompiler = EntityExpressionComplier.DEFAULT;
	EntityActionComplier stCompiler = EntityActionComplier.DEFAULT;

	// EntityActionComplier

	// static class EntityExpressionAgent<T> implements EntityExpression {
	// final Expr<T> expr;
	// final Type type;
	//
	// EntityExpressionAgent(final Expr<T> expr, final Type type) {
	// this.expr = expr;
	// this.type = type;
	// }
	//
	// @Override
	// public T eval(Entity entity) {
	// return null;
	// }
	//
	// EntityExpression compile() {
	// return exprCompiler.compile(expr, type);
	// }
	// }

	public <T> EntityExpression compile(Type type, String name,Expr<T> expr) {
		return exprCompiler.compile(context, type, name, expr);
	}

	public EntityAction compile( Type type, String name,Statement statement) {
		return stCompiler.compile(context, type, name, statement);
	}

	public Expr<Boolean> opConditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Conditional(op, e1, e2);
	}

	public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	public <V extends Comparable<V>> Expr<Boolean> opRelational(Operator op, Expr<V> e1, Expr<V> e2) {
		return new Relational<V>(op, e1, e2);
	}

	public Expr<Object> range(Expr<Object> from, Expr<Object> to) {
		if (from == null) {
			return new Range_0_To(to);
		} else if (to == null) {
			return new Range_From_(from);
		} else {
			return new Range(from, to);
		}
	}

	public Expr<Object> index(Expr<Object> from) {
		return new Index(from);
	}

	public Expr<Object> entities(Expr<Object> repos, String string) {
		return new DatastoreGet(repos, string);
	}

	public Expr<Object> list(Expr<Object> list, List<Expr<Object>> ranges) {
		if (ranges.size() == 1 && ranges.get(0) instanceof Index) {
			return new ListGet(list, (Index) ranges.get(0));
		} else {
			return new ListRanges(list, ranges);
		}
	}

	public Expr<Object> list(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {
		return new ListClause(list, clause, params);
	}

	public Expr<Object> opUnit(Expr<Object> v, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Expr<Object> opFieldInList(Expr<Object> list, String name) {
		return new FieldOf(new ListThisRefer(list, THIS), name);
	}

	public Expr<Object> opArithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		return new Arithmetic(op, e1, e2);
	}

	public Expr<Object> opIncrement(Expr<Object> e1) {
		return new Increment(e1);
	}

	public Expr<Object> opDecrement(Expr<Object> e1) {
		return new Decrement(e1);
	}

	public Expr<Object> opPositive(Expr<Object> e1) {
		return new Positive(e1);
	}

	public Expr<Object> opNegates(Expr<Object> e1) {
		return new Negates(e1);
	}

	public Expr<Integer> opYesnoCst(boolean b) {
		return new YesnoCst(b);
	}

	public Expr<Long> opLongCst(String value) {
		return new LongCst(value);
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}

	public Expr<DateTime> opTimestampCst(String value) {
		return new TimestampCst(value);
	}

	public Expr<DateTime> opDatetimeCst(String value) {
		return new DatetimeCst(value);
	}

	public Expr<DateTime> opDateCst(String value) {
		return new DateCst(value);
	}

	public Expr<DateTime> opTimeCst(String value) {
		return new TimeCst(value);
	}

	public Expr<Object> opType(String text) {
		return new TypeRefer(text);
	}

	public Expr<Object> opLocal(Var var) {
		return new VarRefer(var);
	}

	public Expr<Object> opParamsl(Expr<Object> e1, int i) {
		return new ParamsRefer(e1, PARAMS, i);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		return new FieldOf(e1, name);
	}

	public Statement stPut(Expr<Object> field, Expr<Object> value) {
		return new PutField((FieldOf) field, value);
	}

	public Statement stPutVar(Var var, Expr<Object> initExpr) {
		return new PutVar(var, initExpr);
	}

	public Statement stBlock(List<Statement> statements) {
		return new Block(statements);
	}

	public Expr<Object> opMethodCall(Expr<Entity> e1, String name) {
		return new MethodCall(e1, name);
	}

	public Statement stCall(Expr<Object> e1) {
		return new CallStatment(e1);
	}

	abstract static class Expression<T> implements Opcodes, Expr<T> {
		@Override
		public T eval() {
			throw new UnsupportedOperationException();
		}

		protected void toObject(final MethodVisitor mv, Expr<Object> expr, CompilerContext context) {
			switch (expr.getExprType(context).getRawType()) {
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

		protected void fromObject(final MethodVisitor mv, Expr<Object> expr, CompilerContext context) {
			switch (expr.getExprType(context).getRawType()) {
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
	}

	abstract static class LogicExpr extends Expression<Boolean> {
		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Boolean.name());
		}
	}

	/**
	 * A logical "and" expression.
	 */
	static class Conditional extends LogicExpr {
		final Operator op;
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public Conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// compiles e1
			e1.compile(cw, mv, context);
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
			e2.compile(cw, mv, context);
			// if e1 is false, e1 && e2 is equal to e1:
			// we jump directly to this label, without evaluating e2
			mv.visitLabel(end);
		}

		@Override
		public Boolean eval() {
			return e1.eval() && e2.eval();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}
	}

	/**
	 * A logical "not" expression.
	 */
	static class Not extends LogicExpr {
		final Expr<Boolean> e1;

		Not(Expr<Boolean> e1) {
			this.e1 = e1;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// computes !e1 by evaluating 1 - e1
			mv.visitLdcInsn(new Integer(1));
			e1.compile(cw, mv, context);
			mv.visitInsn(ISUB);
		}

		@Override
		public Boolean eval() {
			return !e1.eval();
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}
	}

	/**
	 * An addition expression.
	 */
	static class Relational<V extends Comparable<V>> extends Expression<Boolean> implements Expr<Boolean> {
		final Operator op;
		final Expr<V> e1;
		final Expr<V> e2;

		Relational(final Operator op, Expr<V> e1, Expr<V> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		@Override
		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).relational(cw, mv, context, op, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) >= 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Boolean.name());
		}
	}

	static class Arithmetic extends Expression<Object> {
		final Expr<Object> e1;
		final Expr<Object> e2;
		final Operator op;

		Arithmetic(final Operator op, final Expr<Object> e1, final Expr<Object> e2) {
			this.e1 = e1;
			this.e2 = e2;
			this.op = op;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).arithmetic(cw, mv, context, op, e1, e2);
		}

		@Override
		public Long eval() {
			return (Long) e1.eval() + (Long) e2.eval();
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return e1.getExprType(context);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}
	}

	abstract static class UnaryArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;

		UnaryArithmeticExpr(final Expr<Object> e1) {
			this.e1 = e1;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return e1.getExprType(context);
		}
	}

	static class Increment extends UnaryArithmeticExpr {
		Increment(Expr<Object> e1) {
			super(e1);
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).increment(cw, mv, context, e1);
		}

		@Override
		public String toString() {
			return "(++" + e1.toString() + ")";
		}
	}

	static class Decrement extends UnaryArithmeticExpr {
		Decrement(Expr<Object> e1) {
			super(e1);
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).decrement(cw, mv, context, e1);
		}

		@Override
		public String toString() {
			return "(--" + e1.toString() + ")";
		}
	}

	static class Positive extends UnaryArithmeticExpr {
		Positive(Expr<Object> e1) {
			super(e1);
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).positive(cw, mv, context, e1);
		}

		@Override
		public String toString() {
			return "(+" + e1.toString() + ")";
		}
	}

	static class Negates extends UnaryArithmeticExpr {
		Negates(Expr<Object> e1) {
			super(e1);
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			resolveOperator(e1.getExprType(context)).negates(cw, mv, context, e1);
		}

		@Override
		public String toString() {
			return "(-" + e1.toString() + ")";
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
		}

		@Override
		public String eval() {
			return this.value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.String.name());
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitTypeInsn(NEW, "java/math/BigDecimal");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(text);
			mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");
		}

		@Override
		public BigDecimal eval() {
			return new BigDecimal(this.text);
		}

		@Override
		public String toString() {
			return text;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Decimal.name());
		}
	}

	static class YesnoCst extends Expression<Integer> {
		final int value;

		YesnoCst(boolean v) {
			if (v) this.value = 1;
			else this.value = 0;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
		}

		@Override
		public Integer eval() {
			return this.value;
		}

		@Override
		public String toString() {
			return value == 1 ? "Yes" : "No";
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Boolean.name());
		}
	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
		}

		@Override
		public Long eval() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Long.name());
		}
	}

	abstract static class Tempral<T> extends Expression<T> {
		final DateTimeFormatter formater;
		final long value;

		Tempral(String text) {
			this(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		Tempral(DateTimeFormatter formater, String text) {
			this.formater = formater;
			this.value = formater.parseDateTime(text).getMillis();
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitTypeInsn(NEW, "org/joda/time/DateTime");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(this.value);
			mv.visitMethodInsn(INVOKESPECIAL, "org/joda/time/DateTime", "<init>", "(J)V");
		}

		@Override
		public String toString() {
			return formater.print(value);
		}
	}

	static class TimestampCst extends Tempral<DateTime> {

		TimestampCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Timestamp.name());
		}
	}

	static class DatetimeCst extends Tempral<DateTime> {
		DatetimeCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Datetime.name());
		}
	}

	static class DateCst extends Tempral<DateTime> {
		DateCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Date.name());
		}
	}

	static class TimeCst extends Tempral<DateTime> {
		TimeCst(String text) {
			super(DateTimeFormat.forPattern("HH:mm:ss"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType(RawTypes.Time.name());
		}
	}

	// Type resolveType(Type type, String name) {
	// for (Field f : type.getFields()) {
	// if (f.name.equals(name)) {
	// return f.getType();
	// }
	// }
	// throw new RuntimeException("Cannot find field");
	// }

	// TODO Not realized MethodCall
	static class MethodCall extends Expression<Object> {
		final Expr<Entity> e1;
		final String name;

		MethodCall(Expr<Entity> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, Compiler.REPOS); // DataRepos
			// NebulaNative.execMethod(null, null, null, null, name) (null,
			// e1.getExprType(context), name, null);
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
		}

		@Override
		public Long eval() {
			return null;
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public Type getExprType(CompilerContext context) {
			Type type = e1.getExprType(context);
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					return f.getType();
				}
			}
			throw new RuntimeException("Cannot find field");
		}
	}

	static class TypeRefer extends Expression<Object> {
		final String name;

		TypeRefer(final String name) {
			this.name = name;
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, Compiler.REPOS);
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
			mv.visitLdcInsn(name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get", "()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");

			//
			//
			// mv.visitVarInsn(ALOAD, 3);
			// mv.visitLdcInsn("wangshilian");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore",
			// "get", "(Ljava/lang/Object;)Lnebula/data/Timable;");
			// mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
			// mv.visitVarInsn(ASTORE, 4);
			//
			//
			// mv.visitVarInsn(ALOAD, 1);
			// mv.visitLdcInsn("Age");
			// mv.visitVarInsn(ALOAD, 4);
			// mv.visitLdcInsn("Age");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get",
			// "(Ljava/lang/String;)Ljava/lang/Object;");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put",
			// "(Ljava/lang/String;Ljava/lang/Object;)V");

		}

		@Override
		public Type getExprType(CompilerContext context) {
			return context.resolveType("Type");
		}

		@Override
		public String toString() {
			return "$" + name;
		}
	}

	static class Index extends Expression<Object> {
		final Expr<Object> index;

		Index(final Expr<Object> index) {
			this.index = index;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return this.index.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			index.compile(cw, mv, context);
			mv.visitInsn(L2I);
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "closed", "(II)Lnebula/lang/Range;");
		}
	}

	static class Range_0_To extends Expression<Object> {
		final Expr<Object> to;

		Range_0_To(final Expr<Object> index) {
			this.to = index;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return this.to.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			to.compile(cw, mv, context);
			mv.visitInsn(L2I);
			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "atMost", "(I)Lnebula/lang/Range;");
		}
	}

	static class Range_From_ extends Expression<Object> {
		final Expr<Object> from;

		Range_From_(final Expr<Object> index) {
			this.from = index;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return this.from.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			from.compile(cw, mv, context);
			mv.visitInsn(L2I);
			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "atLeast", "(I)Lnebula/lang/Range;");
		}
	}

	static class Range extends Expression<Object> {
		final Expr<Object> from;
		final Expr<Object> to;

		Range(final Expr<Object> from, final Expr<Object> to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			if (from != null) return this.from.getExprType(context);
			else return this.to.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			from.compile(cw, mv, context);
			mv.visitInsn(L2I);
			to.compile(cw, mv, context);
			mv.visitInsn(L2I);

			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Range", "closed", "(II)Lnebula/lang/Range;");
		}
	}

	static class ListClause extends Expression<Object> {
		final Expr<Object> list;
		final Expr<Object> clause;
		final List<Expr<Object>> params;

		ListClause(final Expr<Object> list, final Expr<Object> clause, final List<Expr<Object>> params) {
			this.list = list;
			this.clause = clause;
			this.params = params;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return list.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			list.compile(cw, mv, context);
			String clauseName = EntityClauseComplier.DEFAULT.compile(context, this.list.getExprType(context), clause); //(clause, context);
			mv.visitTypeInsn(NEW, clauseName);
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, clauseName, "<init>", "()V");

			mv.visitIntInsn(BIPUSH, params.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			for (int i = 0; i < params.size(); i++) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, i);

				params.get(i).compile(cw, mv, context);
				toObject(mv, params.get(i), context);

				mv.visitInsn(AASTORE);
			}

			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/NebulaNative", "filter", "(Ljava/util/List;Lnebula/lang/Clause;[Ljava/lang/Object;)Ljava/util/List;");
		}

	}

	static class ListGet extends Expression<Object> {
		final Expr<Object> list;
		final Index index;

		ListGet(final Expr<Object> list, final Index index) {
			this.list = list;
			this.index = index;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return ((ListType) list.getExprType(context)).getUnderlyType();
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			list.compile(cw, mv, context);
			mv.visitTypeInsn(CHECKCAST, "java/util/List");
			index.index.compile(cw, mv, context);
			mv.visitInsn(L2I);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
			// mv.visitInsn(POP);
		}
	}

	static class DatastoreGet extends Expression<Object> {
		final Expr<Object> repos;
		final String name;

		DatastoreGet(final Expr<Object> repos, final String name) {
			this.repos = repos;
			this.name = name;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return new ListType(context.resolveType(name));
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			repos.compile(cw, mv, context);
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
			mv.visitLdcInsn(name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get", "()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore", "listAll", "()Ljava/util/List;");
		}
	}

	static class ListRanges extends Expression<Object> {
		final Expr<Object> list;
		final List<Expr<Object>> ranges;

		ListRanges(final Expr<Object> list, final List<Expr<Object>> ranges) {
			this.list = list;
			this.ranges = ranges;
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return list.getExprType(context);
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			list.compile(cw, mv, context);

			mv.visitIntInsn(BIPUSH, ranges.size());
			mv.visitTypeInsn(ANEWARRAY, "nebula/lang/Range");

			for (int i = 0; i < ranges.size(); i++) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, i);
				ranges.get(i).compile(cw, mv, context);
				mv.visitInsn(AASTORE);
			}

			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/NebulaNative", "filter", "(Ljava/util/List;[Lnebula/lang/Range;)Ljava/util/List;");
		}
	}

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, var.index);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return var.type;
		}

		@Override
		public String toString() {
			return var.name;
		}
	}

	static class ParamsRefer extends Expression<Object> {
		final Expr<Object> in;
		final int params;
		final int index;

		ParamsRefer(Expr<Object> in, int params, int index) {
			this.in = in;
			this.params = params;
			this.index = index;
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, params);
			mv.visitIntInsn(SIPUSH, index);
			mv.visitInsn(AALOAD);
			fromObject(mv, in, context);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return in.getExprType(context);
		}

		@Override
		public String toString() {
			return "params[" + String.valueOf(index) + "]";
		}
	}

	static class ListThisRefer extends Expression<Object> {
		final Expr<Object> list;
		final int index;

		ListThisRefer(final Expr<Object> list, int index) {
			this.list = list;
			this.index = index;
		}

		@Override
		public void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, index);
		}

		@Override
		public Type getExprType(CompilerContext context) {
			return ((ListType) list.getExprType(context)).getUnderlyType();
		}

		@Override
		public String toString() {
			return "this";// TODO
		}
	}

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			for (Statement st : statements) {
				st.compile(cw, mv, context);
			}
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			for (Statement st : statements) {
				st.exec(entity, repos);
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("{");
			for (Statement st : statements) {
				sb.append(st);
			}
			sb.append("}");
			return sb.toString();
		}
	}

	// TODO Not realized VarDefineField
	static class PutVar implements Opcodes, Statement {
		final Var var;
		final Expr<Object> initExpr;

		PutVar(Var var, Expr<Object> initExpr) {
			this.var = var;
			this.initExpr = initExpr;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);

		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			// return (Long) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return var.name + " = " + initExpr.toString() + ";";
		}
	}

	static class FieldOf extends Expression<Object> {
		final Expr<Object> e1;
		final String name;

		FieldOf(Expr<Object> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			e1.compile(cw, mv, context);
			mv.visitLdcInsn(this.name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			fromObject(mv, this, context);
		}

		@Override
		public Object eval() {
			return ((Entity) e1.eval()).get(name);
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public Type getExprType(CompilerContext context) {
			Type type = e1.getExprType(context);
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					return f.getType();
				}
			}
			throw new RuntimeException("Cannot find field");
		}
	}

	static class PutField extends Expression<Object> implements Opcodes, Statement {
		final Expr<Object> parent;
		final Expr<Object> value;
		final String name;

		PutField(FieldOf field, Expr<Object> value) {
			this.parent = field.e1;
			this.name = field.name;
			this.value = value;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			parent.compile(cw, mv, context);
			mv.visitLdcInsn(this.name);
			value.compile(cw, mv, context);
			toObject(mv, value, context);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put", "(Ljava/lang/String;Ljava/lang/Object;)V");
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			entity.put(name, value.eval());
		}

		@Override
		public String toString() {
			return parent.toString() + "." + name.toString() + " = " + value.toString() + ";";
		}

		@Override
		public Type getExprType(CompilerContext context) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// TODO Not realized CallStatment
	static class CallStatment implements Opcodes, Statement {
		final Expr<Object> value;

		CallStatment(Expr<Object> value) {
			this.value = value;
		}

		public void compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			// return (Long) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return value.toString() + ";";
		}
	}

}

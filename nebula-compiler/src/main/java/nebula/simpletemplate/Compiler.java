package nebula.simpletemplate;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class Compiler {
	static Log log = LogFactory.getLog(Compiler.class);

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			for (Statement st : statements) {
				st.compile(cw, mv, context);
			}
			return Type.VOID_TYPE;
		}

		@Override
		public void exec(Object root) {
			for (Statement st : statements) {
				st.exec(root);
			}
		}

		@Override
		public String toString(CompilerContext context) {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("{\n");
			for (Statement st : statements) {
				sb.append(st.toString(context));
			}
			sb.append("}");
			return sb.toString();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("{\n");
			for (Statement st : statements) {
				sb.append(st.toString());
			}
			sb.append("}");
			return sb.toString();
		}
	}

	static class Output implements Opcodes, Statement {
		final Expr<?> expr;

		Output(Expr<?> expr) {
			this.expr = expr;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, 1);
			Type type = expr.compile(cw, mv, context);

			
			if (String.class.getName().equals(type.getClassName())) {
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			} else if(type.getSort() != Type.OBJECT){
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + type.getDescriptor() + ")Ljava/lang/StringBuilder;");
			}else{
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + type.getDescriptor() + ")Ljava/lang/StringBuilder;");				
			}
			
			mv.visitInsn(POP);

			return Type.VOID_TYPE;
		}

		@Override
		public void exec(Object root) {
			expr.eval(root);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("\tsb.append(");
			sb.append(expr);
			sb.append(");\n");
			return sb.toString();
		}

		@Override
		public String toString(CompilerContext context) {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("\tsb.append(");
			sb.append(expr.toString(context));
			sb.append(");\n");
			return sb.toString();
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitTypeInsn(NEW, "java/math/BigDecimal");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(text);
			mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");

			return Type.getType(BigDecimal.class);
		}

		@Override
		public BigDecimal eval(Object root) {
			return new BigDecimal(this.text);
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}

		@Override
		public String toString() {
			return text;
		}
	}

	abstract static class Expression<T> implements Opcodes, Expr<T> {
		@Override
		public T eval(Object root) {
			throw new UnsupportedOperationException();
		}

		protected void fromObject(final MethodVisitor mv, Expr<Object> expr, CompilerContext context) {
			// switch (expr.getExprType(context).getRawType()) {
			// case Boolean:
			// mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
			// mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean",
			// "booleanValue", "()Z");
			// break;
			// case Long:
			// mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
			// mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue",
			// "()J");
			// break;
			// case String:
			// mv.visitTypeInsn(CHECKCAST, "java/lang/String");
			// break;
			// default:
			// break;
			// }
		}

		protected void toObject(final MethodVisitor mv, Expr<Object> expr, CompilerContext context) {
			// switch (expr.getExprType(context).getRawType()) {
			// case Boolean:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf",
			// "(Z)Ljava/lang/Boolean;");
			// break;
			// case Long:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf",
			// "(J)Ljava/lang/Long;");
			// break;
			// default:
			// break;
			// }
		}
	}

	static class FieldOf extends Expression<Object> {
		final Expr<Object> e1;
		final String name;

		FieldOf(Expr<Object> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			e1.compile(cw, mv, context);

			Method m = null;
			if (context.isMap) {
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
				Type type = Type.getType(Object.class);
				mv.visitTypeInsn(CHECKCAST, type.getInternalName());
				return type;
			} else if ((m = context.getProp(name)) != null) {
				Type retType = Type.getReturnType(m);
				mv.visitMethodInsn(INVOKEVIRTUAL, context.getInternalName(), m.getName(), "()"+retType.getDescriptor());
				return retType;
			} else {
				throw new RuntimeException("Cannot find field " + name);
			}
		}

		@Override
		public Object eval(Object root) {
			// return ((Entity) e1.eval()).get(name);
			return null;
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public String toString(CompilerContext context) {

			Method m = null;
			if (context.isMap) {
				return e1.toString() + ".get(\"" + name.toString() + "\")";
			} else if ((m = context.getProp(name)) != null) {
				return e1.toString() + "." + m.getName() + "()";
			} else {
				throw new RuntimeException("dd");
			}
		}
	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return Type.LONG_TYPE;
		}

		@Override
		public Long eval(Object root) {
			return this.value;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return Type.getType(String.class);
		}

		@Override
		public String eval(Object root) {
			return this.value;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}

		@Override
		public String toString() {
			return "\"" + value + "\"";
		}
	}

	// CompilerContext context;

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public Type compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, var.index);
			return var.type;
		}

		@Override
		public String toString() {
			return var.name;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class YesnoCst extends Expression<Integer> {
		final int value;

		YesnoCst(boolean v) {
			if (v) this.value = 1;
			else this.value = 0;
		}

		public Type compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return Type.BOOLEAN_TYPE;
		}

		@Override
		public Integer eval(Object root) {
			return this.value;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}

		@Override
		public String toString() {
			return value == 1 ? "Yes" : "No";
		}
	}

	ActionComplier actionComplier = ActionComplier.DEFAULT;

	public Action compile(Class<?> rootClass, String name, Statement statement) {
		CompilerContext context = new CompilerContext(rootClass);
		return actionComplier.compile(context, name, statement);
	}

	public final static int CONTEXT = 1;

	public final static int PARAMS = 4;

	public final static int REPOS = 2;

	public final static int SYSTEM_THIS = 0;

	public final static int THIS = 3;

	Compiler() {
		if (log.isDebugEnabled()) {
			if (!new File("tmp").exists()) new File("tmp/").mkdir();
		}
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		return new FieldOf(e1, name);
	}

	public Expr<Object> opLocal(Var var) {
		return new VarRefer(var);
	}

	public Expr<Long> opLongCst(String value) {
		return new LongCst(value);
	}

	public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}

	public Expr<Integer> opYesnoCst(boolean b) {
		return new YesnoCst(b);
	}

	public Statement stBlock(List<Statement> statements) {
		return new Block(statements);
	}

	public Statement stOutput(Expr<?> expr) {
		return new Output(expr);
	}
}

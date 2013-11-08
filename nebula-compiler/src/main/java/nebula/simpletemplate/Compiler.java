package nebula.simpletemplate;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import nebula.lang.Operator;
import nebula.simpletemplate.CompilerContext.Arg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class Compiler {
	static class ArgRefer extends Expression<Object> {
		final Var arg;
		final Var argv;

		ArgRefer(final Var argv, final Var var) {
			this.argv = argv;
			this.arg = var;
		}

		@Override
		public Class<?> compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, argv.index);
			switch (arg.index) {
			case 0:
				mv.visitInsn(ICONST_0);
				break;
			case 1:
				mv.visitInsn(ICONST_1);
				break;
			case 2:
				mv.visitInsn(ICONST_2);
				break;
			case 3:
				mv.visitInsn(ICONST_3);
				break;
			case 4:
				mv.visitInsn(ICONST_4);
				break;
			case 5:
				mv.visitInsn(ICONST_5);
				break;
			default:
				mv.visitIntInsn(BIPUSH, arg.index);
				break;
			}

			mv.visitInsn(AALOAD);
			Arg a = null;
			if (arg.index < context.arges.size()) {
				a = context.getArg(arg.index);
				if (a != null) {
					if (Map.class.isAssignableFrom(a.clz)) {
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Map.class));
						return a.clz;
					} else if (List.class.isAssignableFrom(a.clz)) {
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(List.class));
						return a.clz;
					} else if (String.class.isAssignableFrom(a.clz)) {
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(String.class));
						return String.class;
					} else if (StringBuilder.class.isAssignableFrom(a.clz)) {
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(StringBuilder.class));
						return StringBuilder.class;
					} else {
						// mv.visitTypeInsn(CHECKCAST,
						// Type.getInternalName(Object.class));
						return a.clz;
					}
				}
			}
			return Object.class;
		}

		@Override
		public String toString() {
			return arg.name;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			for (Statement st : statements) {
				st.compile(cw, mv, context);
			}
			return Void.TYPE;
		}

		@Override
		public void exec(Object root) {
			for (Statement st : statements) {
				st.exec(root);
			}
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
	}

	/**
	 * A logical "and" expression.
	 */
	static class Conditional extends Expression<Boolean> {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;
		final Operator op;

		public Conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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

			return Boolean.TYPE;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitTypeInsn(NEW, "java/math/BigDecimal");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(text);
			mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");

			return BigDecimal.class;
		}

		@Override
		public BigDecimal eval(Object root) {
			return new BigDecimal(this.text);
		}

		@Override
		public String toString() {
			return text;
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
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

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Class<?> clz = e1.compile(cw, mv, context);

			Method m = null;
			Field f = null;
			if (Map.class.isAssignableFrom(clz)) {
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
				return Object.class;
			} else if ((m = CompilerContext.getProp(clz, name)) != null) {
				Class<?> retClass = m.getReturnType();
				Class<?> defineClass = m.getDeclaringClass();
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
				if (clz.isInterface()) {
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClass));
					return retClass;
				} else {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClass));
					return retClass;
				}
			} else if ((f = CompilerContext.getField(clz, name)) != null) {
				Class<?> retClass = f.getType();
				Class<?> defineClass = f.getDeclaringClass();
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
				mv.visitFieldInsn(GETFIELD, Type.getInternalName(defineClass), name, Type.getDescriptor(retClass));
				return retClass;
			} else {
				throw new RuntimeException("Cannot find " + e1.toString(context) + "." + name);
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
			return e1.toString() + ".\"" + name.toString() + "\"";
		}
	}

	static class Include extends Expression<Object> {
		final List<Argument> args;
		final Expr<Object> group;
		final Expr<String> name;

		Include(Expr<Object> group, Expr<String> name, List<Argument> args) {
			this.group = group;
			this.name = name;
			this.args = args;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			group.compile(cw, mv, context);
			name.compile(cw, mv, context);
			mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "getTemplate",
					"(Ljava/lang/String;)" + Type.getDescriptor(TemplateImpl.class));

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			if (args.size() > 0) {

				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);
				Class<?> firstType = evalValue(args.get(0).value, cw, mv, context);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					evalValue(args.get(i).value, cw, mv, context);
					mv.visitInsn(AASTORE);
				}

				if (!List.class.isAssignableFrom(firstType)) {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "exec", "([Ljava/lang/Object;)Ljava/lang/String;");
				} else {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "execList", "([Ljava/lang/Object;)Ljava/lang/String;");
				}
			} else {
				mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "exec", "([Ljava/lang/Object;)Ljava/lang/String;");
			}
			return String.class;
		}

		private Class<?> evalValue(Expr<Object> value, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (value instanceof IncludeSubTemplate) {
				IncludeSubTemplate sub = (IncludeSubTemplate) value;
				sub.compileInlineToString(cw, mv, context);
				return String.class;
			} else {
				return value.compile(cw, mv, context);
			}
		}

		@Override
		public Object eval(Object root) {
			// return ((Entity) e1.eval()).get(name);
			return null;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name.toString());
			if (args != null && args.size() > 0) {
				sb.append("(");
				for (Argument arg : args) {
					sb.append(arg.value.toString());
					sb.append(',');
				}
				sb.setCharAt(sb.length() - 1, ')');
			} else {
				sb.append("()");
			}

			return sb.toString();
		}

		@Override
		public String toString(CompilerContext context) {

			// Method m = null;
			// if (context.isMap) {
			// return e1.toString() + ".get(\"" + name.toString() + "\")";
			// } else if ((m = context.getProp(name)) != null) {
			// return e1.toString() + "." + m.getName() + "()";
			// } else {
			// throw new RuntimeException("dd");
			// }
			return null;
		}
	}

	static class IncludeSubTemplate extends Expression<Object> {
		final Var argv;
		final Var sb;
		final List<Argument> args;
		final int subTemplateIndex;
		final Expr<Object> template;

		IncludeSubTemplate(Var argv, Var sb, Expr<Object> template, int subTemplateIndex, List<Argument> args) {
			this.sb = sb;
			this.argv = argv;
			this.template = template;
			this.subTemplateIndex = subTemplateIndex;
			this.args = args;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			template.compile(cw, mv, context);
			mv.visitFieldInsn(GETFIELD, Type.getInternalName(TemplateImpl.class), "implicitlyDefinedTemplates", Type.getDescriptor(List.class));
			mv.visitIntInsn(BIPUSH, subTemplateIndex);
			mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, Type.getInternalName(TemplateImpl.class));

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				Class<?> firstType = evalValue(args.get(0).value,cw, mv, context);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					evalValue(args.get(i).value,cw, mv, context);
					mv.visitInsn(AASTORE);
				}

				if (!List.class.isAssignableFrom(firstType)) {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "exec", "([Ljava/lang/Object;)Ljava/lang/String;");
				} else {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "execList", "([Ljava/lang/Object;)Ljava/lang/String;");
				}
			} else {
				mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(TemplateImpl.class), "exec", "([Ljava/lang/Object;)Ljava/lang/String;");
			}

			return String.class;
		}

		private Class<?> evalValue(Expr<Object> value, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (value instanceof IncludeSubTemplate) {
				IncludeSubTemplate sub = (IncludeSubTemplate) value;
				sub.compileInlineToString(cw, mv, context);
				return String.class;
			} else {
				return value.compile(cw, mv, context);
			}
		}
		
		public Class<?> compileInlineToString(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, argv.index); // backup parent argv to stack
			mv.visitVarInsn(ALOAD, sb.index);

			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
			mv.visitVarInsn(ASTORE, sb.index);

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();

			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				Class<?> firstType = evalValue(args.get(0).value,cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz =evalValue(args.get(i).value,cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}
			mv.visitVarInsn(ASTORE, argv.index); // store sub argv to argv

			CompilerContext subContext = new CompilerContext(context.impl, clzes);
			Code code = subContext.impl.implicitlyDefinedTemplates.get(subTemplateIndex).code;
			code.compile(cw, mv, subContext);
			
			mv.visitVarInsn(ALOAD, sb.index);
			mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(StringBuilder.class), "toString", "()Ljava/lang/String;");
			int locals = context.locals;			
			mv.visitVarInsn(ASTORE, locals);

			mv.visitVarInsn(ASTORE, sb.index); // store parent argv to argv
			mv.visitVarInsn(ASTORE, argv.index); // store parent argv to argv
													// locate

			mv.visitVarInsn(ALOAD, locals);
			return String.class;
		}

		public Class<?> compileInlineAppend(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, argv.index); // backup parent argv to stack


			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();

			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				Class<?> firstType = evalValue(args.get(0).value,cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz = evalValue(args.get(i).value,cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}
			mv.visitVarInsn(ASTORE, argv.index); // store sub argv to argv

			CompilerContext subContext = new CompilerContext(context.impl, clzes);
			Code code = subContext.impl.implicitlyDefinedTemplates.get(subTemplateIndex).code;
			code.compile(cw, mv, subContext);
						
//			mv.visitInsn(POP);
//			int locals = context.locals;			
//			mv.visitVarInsn(ASTORE, locals);

			mv.visitVarInsn(ASTORE, argv.index); // store parent argv to argv
			return Void.class;
		}

		@Override
		public Object eval(Object root) {
			return null;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(template.toString());
			sb.append("[" + this.subTemplateIndex + "]");
			if (args != null && args.size() > 0) {
				sb.append("(");
				for (Argument arg : args) {
					sb.append(arg.value.toString());
					sb.append(',');
				}
				sb.setCharAt(sb.length() - 1, ')');
			} else {
				sb.append("()");
			}

			return sb.toString();
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}

	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return Long.TYPE;
		}

		@Override
		public Long eval(Object root) {
			return this.value;
		}

		@Override
		public String toString(CompilerContext context) {
			return String.valueOf(value);
		}
	}

	static class Name extends Expression<String> {
		final String value;

		Name(String value) {
			this.value = value;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return String.class;
		}

		@Override
		public String eval(Object root) {
			return this.value;
		}

		@Override
		public String toString() {
			return "'" + value.toString() + "'";
		}

		@Override
		public String toString(CompilerContext context) {
			return "'" + value.toString() + "'";
		}
	}

	static class Not extends Expression<Boolean> {
		final Expr<Boolean> e1;

		Not(Expr<Boolean> e1) {
			this.e1 = e1;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// computes !e1 by evaluating 1 - e1
			mv.visitInsn(ICONST_1);
			e1.compile(cw, mv, context);
			mv.visitInsn(ISUB);
			return Boolean.TYPE;
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class EvalBoolean extends Expression<Boolean> {
		final Expr<?> e1;

		EvalBoolean(Expr<?> e1) {
			this.e1 = e1;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Class<?> clz = e1.compile(cw, mv, context);
			if (clz == Boolean.TYPE) {
				return clz;
			} else {
				Label ifFalse = new Label();
				Label ifEnd = new Label();

				mv.visitJumpInsn(IFNULL, ifFalse);
				mv.visitInsn(ICONST_1);
				mv.visitJumpInsn(GOTO, ifEnd);
				mv.visitLabel(ifFalse);
				mv.visitInsn(ICONST_0);
				mv.visitLabel(ifEnd);
			}
			return Boolean.TYPE;
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class If implements Opcodes, Statement {
		final List<Expr<?>> conditions;
		final List<Statement> statements;
		final Statement blockelse;

		If(List<Expr<?>> conditions, List<Statement> statements, Statement blockelse) {
			this.conditions = conditions;
			this.statements = statements;
			this.blockelse = blockelse;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Label ifEnd = new Label();
			Label ifFalse = null;

			int cnt = conditions.size();
			if (blockelse != null) {
				cnt++;
			}

			for (int i = 0; i < conditions.size(); i++) {
				if (cnt > 1) {
					ifFalse = new Label();
				} else {
					ifFalse = ifEnd;
				}
				Expr<?> e = conditions.get(i);
				Statement block = statements.get(i);

				Class<?> clz = e.compile(cw, mv, context);
				if (clz == Boolean.TYPE) {
					mv.visitJumpInsn(IFEQ, ifFalse);
					block.compile(cw, mv, context);
				} else {
					mv.visitJumpInsn(IFNULL, ifFalse);
					block.compile(cw, mv, context);
				}
				if (cnt > 1) {
					mv.visitJumpInsn(GOTO, ifEnd);
					mv.visitLabel(ifFalse);
				}
				cnt--;
			}

			if (blockelse != null) {
				blockelse.compile(cw, mv, context);
			}

			mv.visitLabel(ifEnd);

			return Boolean.TYPE;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < conditions.size(); i++) {
				Expr<?> e = conditions.get(i);
				Statement block = statements.get(i);
				sb.append("if");
				sb.append("(");
				sb.append(e.toString());
				sb.append(")");
				sb.append(block.toString());
			}
			if (blockelse != null) {
				sb.append("else");
				sb.append(blockelse.toString());
			}
			return sb.toString();
		}

		@Override
		public String toString(CompilerContext context) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < conditions.size(); i++) {
				Expr<?> e = conditions.get(i);
				Statement block = statements.get(i);
				sb.append("if");
				sb.append("(");
				sb.append(e.toString(context));
				sb.append(")");
				sb.append(block.toString(context));
			}
			if (blockelse != null) {
				sb.append(blockelse.toString(context));
			}
			return sb.toString();
		}

		@Override
		public void exec(Object root) {
			// TODO Auto-generated method stub

		}
	}

	// CompilerContext context;

	static class Output implements Opcodes, Statement {
		final Expr<?> expr;
		final Expr<?> sb;

		Output(Expr<?> sb, Expr<?> expr) {
			this.sb = sb;
			this.expr = expr;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if(expr instanceof IncludeSubTemplate){
				IncludeSubTemplate sub = (IncludeSubTemplate)expr;
				return sub.compileInlineAppend(cw, mv, context);
			}
			
			sb.compile(cw, mv, context);
			
			
			Class<?> clz = expr.compile(cw, mv, context);

			if (String.class == clz) {
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
				mv.visitInsn(POP);
			} else if (clz.isPrimitive()) {
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(clz) + ")Ljava/lang/StringBuilder;");
				mv.visitInsn(POP);
			} else if (StringBuilder.class == clz) {
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(clz) + ")Ljava/lang/StringBuilder;");
				mv.visitInsn(POP);
			} else if (List.class.isAssignableFrom(clz)) {

				int locals = context.locals;

				int listLocal = locals++;

				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);
				// }
				mv.visitInsn(POP); // pop StringBuilder from stack
			} else if (Map.class.isAssignableFrom(clz)) {

				int locals = context.locals;

				int listLocal = locals++;

				// // initial
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "values", "()Ljava/util/Collection;");
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Collection", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);

				mv.visitInsn(POP); // pop StringBuilder from stack
			} else if (ST.class.isAssignableFrom(clz)) {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(ST.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(ST.class), "render", "()Ljava/lang/String;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(String.class) + ")Ljava/lang/StringBuilder;");
				mv.visitInsn(POP);
			} else {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(Object.class) + ")Ljava/lang/StringBuilder;");
				mv.visitInsn(POP);
			}

			return Void.TYPE;
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

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return String.class;
		}

		@Override
		public String eval(Object root) {
			return this.value;
		}

		@Override
		public String toString() {
			return "\"" + value + "\"";
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public Class<?> compile(ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, var.index);
			return null;
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

		public Class<?> compile(ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			mv.visitLdcInsn(value);
			return Boolean.TYPE;
		}

		@Override
		public Integer eval(Object root) {
			return this.value;
		}

		@Override
		public String toString() {
			return value == 1 ? "Yes" : "No";
		}

		@Override
		public String toString(CompilerContext context) {
			return this.toString();
		}
	}

	public final static int CONTEXT = 1;

	public static final Map<String, String> funcs = ImmutableMap.of();

	static Log log = LogFactory.getLog(Compiler.class);

	public final static int PARAMS = 4;

	public final static int REPOS = 2;

	public final static int SYSTEM_THIS = 0;

	public final static int THIS = 3;

	ActionComplier actionComplier = ActionComplier.DEFAULT;

	Compiler() {
		if (log.isDebugEnabled()) {
			if (!new File("tmp").exists()) new File("tmp/").mkdir();
		}
	}

	// public Action compile(Class<?> rootClass, String name, Statement
	// statement) {
	// CompilerContext context = new CompilerContext(rootClass);
	// return actionComplier.compile(context, name, statement);
	// }

	public void opAddArgument(List<Argument> list, Argument arg) {
		arg.index = list.size();
		list.add(arg);
	}

	public void opAddArgument(final Var argv, final List<Argument> list, List<Var> srcArgs) {

		for (Var sa : srcArgs) {
			// boolean find = false;
			// for (Argument da : list) {
			// if(da.name.equals(sa.name) ){
			// find=true;
			// break;
			// }
			// }
			// if(!find){
			Argument da = new Argument(new ArgRefer(argv, sa));
			list.add(da);
			// }
		}
		// TODO
	}

	public Expr<Object> opArg(Var argv, Var var) {
		Preconditions.checkNotNull(var);
		return new ArgRefer(argv, var);
	}

	public Argument opArgument(Expr<Object> arg) {
		return new Argument(arg);
	}

	public Argument opArgument(String name, Argument arg) {
		arg.name = name;
		return arg;
	}

	public Expr<Boolean> opConditional(Operator op, Expr<?> e1, Expr<?> e2) {
		Expr<Boolean> eBoolean1 = new EvalBoolean(e1);
		Expr<Boolean> eBoolean2 = new EvalBoolean(e2);
		return new Conditional(op, eBoolean1, eBoolean2);
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		Preconditions.checkNotNull(value);
		return new DecimalCst(value);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		Preconditions.checkNotNull(e1);
		return new FieldOf(e1, name);
	}

	public Expr<Object> opInclude(Expr<Object> group, Expr<String> name, Expr<Object> target, List<Argument> args) {
		if (args == null) {
			args = Lists.newArrayList();
		}
		args.add(0, new Argument(target));
		return new Include(group, name, args);
	}

	public Expr<Object> opInclude(Expr<Object> group, Expr<String> name, List<Argument> args) {
		return new Include(group, name, args);
	}

	@SuppressWarnings("rawtypes")
	public Expr<Object> opInclude(Expr<Object> group, Expr<String> name, List<Expr> target, List<Argument> args) {
		List<Argument> leading = Lists.newArrayList();
		for (Expr<Object> e : target) {
			leading.add(new Argument(e));
		}
		args.addAll(0, leading);
		return new Include(group, name, args);
	}

	public Expr<Object> opIncludeSub(Var argv, Var sb, Expr<Object> template, int subTemplateIndex) {
		List<Argument> leading = Lists.newArrayList();
		return new IncludeSubTemplate(argv, sb, template, subTemplateIndex, leading);
	}

	public Expr<Object> opIncludeSub(Var argv, Var sb, Expr<Object> template, int subTemplateIndex, Expr<Object> target) {
		List<Argument> leading = Lists.newArrayList();
		leading.add(new Argument(target));
		return new IncludeSubTemplate(argv, sb, template, subTemplateIndex, leading);
	}

	@SuppressWarnings("rawtypes")
	public Expr<Object> opIncludeSub(Var argv, Var sb, Expr<Object> template, int subTemplateIndex, List<Expr> target) {
		List<Argument> leading = Lists.newArrayList();
		for (Expr<Object> e : target) {
			leading.add(new Argument(e));
		}
		return new IncludeSubTemplate(argv, sb, template, subTemplateIndex, leading);
	}

	public Expr<Object> opLocal(Var var) {
		Preconditions.checkNotNull(var);
		return new VarRefer(var);
	}

	public Expr<Long> opLongCst(String value) {
		Preconditions.checkNotNull(value);
		return new LongCst(value);
	}

	public Expr<String> opName(String value) {
		Preconditions.checkNotNull(value);
		return new Name(value);
	}

	public Expr<Boolean> opNot(Expr<?> e1) {
		Expr<Boolean> eBoolean = new EvalBoolean(e1);
		return new Not(eBoolean);
	}

	public Expr<String> opStringCst(String value) {
		Preconditions.checkNotNull(value);
		return new StringCst(value);
	}

	public Expr<Integer> opYesnoCst(boolean b) {
		return new YesnoCst(b);
	}

	public Statement stBlock(List<Statement> statements) {
		Preconditions.checkNotNull(statements);
		return new Block(statements);
	}

	public Statement stIf(List<Expr<?>> condition, List<Statement> statements, Statement blockElse) {
		return new If(condition, statements, blockElse);
	}

	public Statement stOutput(Expr<?> sb, Expr<?> expr) {
		Preconditions.checkNotNull(expr);
		return new Output(sb, expr);
	}

	public void tpReferTemplate(STGroup group, String name, TemplateImpl template) {
		template.name = name;
		group.knownsTemplate.put(name, template);
	}

	public TemplateImpl tpTemplate(STGroup group, Statement statement, List<Var> arges, List<TemplateImpl> implicitlyDefinedTemplates) {
		Preconditions.checkNotNull(group);
		Preconditions.checkNotNull(statement);
		List<String> argNames = Lists.newArrayList();
		for (Var var : arges) {
			argNames.add(var.name);
		}
		if (implicitlyDefinedTemplates.size() > 0) {
			return new TemplateImpl(group, statement, argNames, implicitlyDefinedTemplates);
		} else {
			return new TemplateImpl(group, statement, argNames);
		}
	}
}

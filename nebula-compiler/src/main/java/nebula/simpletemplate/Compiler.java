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
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class Compiler implements Opcodes {

	static void _Print_(MethodVisitor mv, String msg) {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn(msg);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
	}

	static void _IConst_(MethodVisitor mv, int i) {
		switch (i) {
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
			mv.visitIntInsn(BIPUSH, i);
			break;
		}
	}

	static Class<?> _box_(MethodVisitor mv, Class<?> clz) {
		Class<?> pclz = null;
		if (clz == Boolean.TYPE) {
			pclz = Boolean.class;
		} else if (clz == Byte.TYPE) {
			pclz = Byte.class;
		} else if (clz == Character.TYPE) {
			pclz = Character.class;
		} else if (clz == Double.TYPE) {
			pclz = Double.class;
		} else if (clz == Float.TYPE) {
			pclz = Float.class;
		} else if (clz == Short.TYPE) {
			pclz = Short.class;
		} else if (clz == Integer.TYPE) {
			pclz = Integer.class;
		} else if (clz == Long.TYPE) {
			pclz = Integer.class;
		} else {
			throw new RuntimeException("not primary type");
		}

		mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(pclz), "valueOf", "(" + Type.getDescriptor(clz) + ")" + Type.getDescriptor(pclz));

		return pclz;
	}

	static class ArgRefer extends Expression<Object> {
		final Var arg;
		final Var argv;

		ArgRefer(final Var argv, final Var var) {
			this.argv = argv;
			this.arg = var;
		}

		@Override
		public Class<?> compile(String clzName, ClassWriter cw, MethodVisitor mv, CompilerContext context) {
			mv.visitVarInsn(ALOAD, argv.index);

			_IConst_(mv, arg.index);

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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			for (Statement st : statements) {
				st.compile(clzName, cw, mv, context);
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// compiles e1
			e1.compile(clzName, cw, mv, context);
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
			e2.compile(clzName, cw, mv, context);
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Class<?> retClz = null;
			Class<?> parentClz = e1.compile(clzName, cw, mv, context);

			Method m = null;
			Field f = null;
			if (Map.class.isAssignableFrom(parentClz)) {
				mv.visitInsn(DUP);
				Label ifEnd = new Label();
				mv.visitJumpInsn(IFNULL, ifEnd);
				{
					mv.visitLdcInsn(name);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
				}
				mv.visitLabel(ifEnd);
				retClz = Object.class;
			} else if ((m = CompilerContext.getProp(parentClz, name)) != null) {
				retClz = m.getReturnType();
				Class<?> defineClass = m.getDeclaringClass();
				if (retClz.isPrimitive()) {
					mv.visitInsn(DUP);
					Label ifEnd = new Label();
					Label ifFalse = new Label();
					mv.visitJumpInsn(IFNULL, ifFalse);
					{
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
						if (parentClz.isInterface()) {
							mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
						} else {
							mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
						}
						mv.visitVarInsn(ALOAD, 0); // push not null
						mv.visitJumpInsn(GOTO, ifEnd);
					}
					mv.visitLabel(ifFalse);
					{
						mv.visitInsn(POP);
						mv.visitLdcInsn(0);
						mv.visitInsn(ACONST_NULL);
					}
					mv.visitLabel(ifEnd);
				} else {
					mv.visitInsn(DUP);
					Label ifEnd = new Label();
					mv.visitJumpInsn(IFNULL, ifEnd);
					{
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
						if (parentClz.isInterface()) {
							mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
						} else {
							mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
						}
					}
					mv.visitLabel(ifEnd);
				}
			} else if ((f = CompilerContext.getField(parentClz, name)) != null) {
				retClz = f.getType();
				Class<?> defineClass = f.getDeclaringClass();

				if (retClz.isPrimitive()) {
					mv.visitInsn(DUP);
					Label ifEnd = new Label();
					Label ifFalse = new Label();
					mv.visitJumpInsn(IFNULL, ifFalse);
					{
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
						mv.visitFieldInsn(GETFIELD, Type.getInternalName(defineClass), name, Type.getDescriptor(retClz));
						mv.visitVarInsn(ALOAD, 0); // push not null
						mv.visitJumpInsn(GOTO, ifEnd);
					}
					mv.visitLabel(ifFalse);
					{
						mv.visitInsn(POP);
						mv.visitLdcInsn(0);
						mv.visitInsn(ACONST_NULL);
					}
					mv.visitLabel(ifEnd);
				} else {
					mv.visitInsn(DUP);
					Label ifEnd = new Label();
					mv.visitJumpInsn(IFNULL, ifEnd);
					{
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
						mv.visitFieldInsn(GETFIELD, Type.getInternalName(defineClass), name, Type.getDescriptor(retClz));
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(retClz));
					}
					mv.visitLabel(ifEnd);
				}
			} else {
				throw new RuntimeException("Cannot find " + e1.toString(context) + "." + name);
			}

			return retClz;
		}

		public Class<?> compile(Label ifAllFalse, String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Class<?> retClz = null;
			Class<?> parentClz = null;
			if (e1 instanceof FieldOf) {
				parentClz = ((FieldOf) e1).compile(ifAllFalse, clzName, cw, mv, context);
			} else {
				parentClz = e1.compile(clzName, cw, mv, context);
			}
			mv.visitInsn(DUP);
			mv.visitJumpInsn(IFNULL, ifAllFalse);

			Method m = null;
			Field f = null;
			if (Map.class.isAssignableFrom(parentClz)) {
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
				retClz = Object.class;
			} else if ((m = CompilerContext.getProp(parentClz, name)) != null) {
				retClz = m.getReturnType();
				Class<?> defineClass = m.getDeclaringClass();
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
				if (parentClz.isInterface()) {
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
				} else {
					mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(defineClass), m.getName(), "()" + Type.getDescriptor(retClz));
				}
//				if (!retClz.isPrimitive()) {
//					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(retClz));
//				}
			} else if ((f = CompilerContext.getField(parentClz, name)) != null) {
				retClz = f.getType();
				Class<?> defineClass = f.getDeclaringClass();
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(defineClass));
				mv.visitFieldInsn(GETFIELD, Type.getInternalName(defineClass), name, Type.getDescriptor(retClz));
//				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(retClz));
			} else {
				throw new RuntimeException("Cannot find " + e1.toString(context) + "." + name);
			}

			return retClz;
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

		static final int THIS = 0;
		static final int GROUP = 1;
		static final int TEMPLATE = 2;
		static final int STRINGBILDER = 3;
		static final int ARGV = 4;

		Include(Expr<Object> group, Expr<String> name, List<Argument> args) {
			this.group = group;
			this.name = name;
			this.args = args;
		}

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			return compileInlineToString(clzName, cw, mv, context);
		}

		private Class<?> evalValue(String clzName, Expr<Object> value, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (value instanceof IncludeSubTemplate) {
				IncludeSubTemplate sub = (IncludeSubTemplate) value;
				sub.compileInlineToString(clzName, cw, mv, context);
				return String.class;
			} else {
				Class<?> retClass = null;
				if (value instanceof FieldOf) {
					Label ifNull = new Label();
					retClass = ((FieldOf) value).compile(ifNull, clzName, cw, mv, context);
					{
						if (retClass.isPrimitive()) {
							retClass = _box_(mv, retClass);
						}
					}
					mv.visitLabel(ifNull);
				} else {
					retClass = value.compile(clzName, cw, mv, context);
				}
				return retClass;
			}
		}

		public Class<?> compileInlineToString(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			int localStringBuilder = context.locals++; // 0 + 1 = 1

			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
			mv.visitVarInsn(ASTORE, localStringBuilder);

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();
			Class<?> firstType = null;
			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				firstType = evalValue(clzName, args.get(0).value, cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz = evalValue(clzName, args.get(i).value, cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}

			int localSubArgv = context.locals++; // 1 + 1 = 2

			mv.visitVarInsn(ASTORE, localSubArgv); // store sub argv to argv

			if (args.size() == 0) {
				if (this.name instanceof Name) {
					String templateName = ((Name) this.name).value;

					mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitVarInsn(ASTORE, ARGV);

					mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent argv
															// to
															// argv
					mv.visitVarInsn(ALOAD, localStringBuilder);
					mv.visitVarInsn(ASTORE, STRINGBILDER);

					CompiledST subImpl = context.impl.nativeGroup.lookupTemplate(templateName);

					boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

					if (needSubTemplate) {
						mv.visitVarInsn(ALOAD, TEMPLATE);

						group.compile(clzName, cw, mv, context);
						name.compile(clzName, cw, mv, context);
						mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
								"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
						mv.visitVarInsn(ASTORE, TEMPLATE);
					}

					CompilerContext subContext = new CompilerContext(subImpl, clzes);
					subContext.locals = context.locals;
					subImpl.code.compile(clzName, cw, mv, subContext);

					if (needSubTemplate) {
						mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv
															// to
					}

					mv.visitVarInsn(ASTORE, STRINGBILDER);
					mv.visitVarInsn(ASTORE, ARGV);
				} else {
					// String templateName = ((StringCst)this.name).value;
					//
					// mv.visitVarInsn(ALOAD, ARGV); // store parent argv to
					// argv
					// mv.visitVarInsn(ALOAD, localSubArgv);
					// mv.visitVarInsn(ASTORE, ARGV);
					//
					// mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent
					// argv
					// // to
					// // argv
					// mv.visitVarInsn(ALOAD, localStringBuilder);
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					//
					// CompilerContext subContext = new
					// CompilerContext(context.impl, clzes);
					// Code code =
					// subContext.impl.nativeGroup.lookupTemplate(templateName).code;
					// code.compile(clzName, cw, mv, subContext);
					//
					// group.compile(clzName, cw, mv, context);
					// name.compile(clzName, cw, mv, context);
					// mv.visitMethodInsn(INVOKEVIRTUAL,
					// Type.getInternalName(STGroup.class), "lookupTemplate",
					// "(Ljava/lang/String;)" +
					// Type.getDescriptor(TemplateImpl.class));
					//
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					// mv.visitVarInsn(ASTORE, ARGV);
				}

			} else if (List.class.isAssignableFrom(firstType)) {
				int listLocal = context.locals++; // 2+1=3

				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					// subArgv[0] = list.next()
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);

					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitInsn(AASTORE);

					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);
					mv.visitInsn(AALOAD);

					int localFirstArg = context.locals++; // 3+1 =4

					{
						mv.visitVarInsn(ASTORE, localFirstArg);
						doCall(clzName, cw, mv, context, localStringBuilder, localSubArgv, localFirstArg);
					}
					context.locals--;// localFirstArg 4-1 = 3

				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);

				context.locals--;// listLocal 3-1 = 2

			} else {
				if (this.name instanceof Name) {
					String templateName = ((Name) this.name).value;

					mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitVarInsn(ASTORE, ARGV);

					mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent argv
															// to
															// argv
					mv.visitVarInsn(ALOAD, localStringBuilder);
					mv.visitVarInsn(ASTORE, STRINGBILDER);

					CompiledST subImpl = context.impl.nativeGroup.lookupTemplate(templateName);
					boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

					if (needSubTemplate) {
						mv.visitVarInsn(ALOAD, TEMPLATE);

						group.compile(clzName, cw, mv, context);
						name.compile(clzName, cw, mv, context);
						mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
								"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
						mv.visitVarInsn(ASTORE, TEMPLATE);
					}

					CompilerContext subContext = new CompilerContext(subImpl, clzes);
					subContext.locals = context.locals;
					subImpl.code.compile(clzName, cw, mv, subContext);

					if (needSubTemplate) {
						mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv
															// to
					}

					mv.visitVarInsn(ASTORE, STRINGBILDER);
					mv.visitVarInsn(ASTORE, ARGV);
				} else {
					// String templateName = ((StringCst)this.name).value;
					//
					// mv.visitVarInsn(ALOAD, ARGV); // store parent argv to
					// argv
					// mv.visitVarInsn(ALOAD, localSubArgv);
					// mv.visitVarInsn(ASTORE, ARGV);
					//
					// mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent
					// argv
					// // to
					// // argv
					// mv.visitVarInsn(ALOAD, localStringBuilder);
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					//
					// CompilerContext subContext = new
					// CompilerContext(context.impl, clzes);
					// Code code =
					// subContext.impl.nativeGroup.lookupTemplate(templateName).code;
					// code.compile(clzName, cw, mv, subContext);
					//
					// group.compile(clzName, cw, mv, context);
					// name.compile(clzName, cw, mv, context);
					// mv.visitMethodInsn(INVOKEVIRTUAL,
					// Type.getInternalName(STGroup.class), "lookupTemplate",
					// "(Ljava/lang/String;)" +
					// Type.getDescriptor(TemplateImpl.class));
					//
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					// mv.visitVarInsn(ASTORE, ARGV);
				}
			}
			context.locals--;// localSubArgv // 2-1 = 1

			mv.visitVarInsn(ALOAD, localStringBuilder);
			mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(StringBuilder.class), "toString", "()Ljava/lang/String;");
			context.locals--; // localStringBuilder 1-1=0

			return String.class;
		}

		static int cntInclude = 0;

		private void doCall(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context, int localStringBuilder, int localSubArgv,
				int localFirstArg) {
			{
				FieldVisitor fv;
				String templateActionFieldName, templateTemplateFieldName, templateClzFieldName;
				if (this.name instanceof Name) {
					templateActionFieldName = "action_" + ((Name) this.name).value + "_" + cntInclude;
					templateTemplateFieldName = "temp_" + ((Name) this.name).value + "_" + cntInclude;
					templateClzFieldName = "clz_" + ((Name) this.name).value + "_" + cntInclude++;
				} else {
					templateActionFieldName = "temp_" + cntInclude;
					templateTemplateFieldName = "temp_" + cntInclude;
					templateClzFieldName = "clz_" + this.name + cntInclude++;
				}
				{// Define field
					fv = cw.visitField(ACC_PRIVATE, templateActionFieldName, Type.getDescriptor(Action.class), null, null);
					fv.visitEnd();
					fv = cw.visitField(ACC_PRIVATE, templateTemplateFieldName, Type.getDescriptor(CompiledST.class), null, null);
					fv.visitEnd();
					fv = cw.visitField(ACC_PRIVATE, templateClzFieldName, "Ljava/lang/Class;", "Ljava/lang/Class<*>;", null);
					fv.visitEnd();
				}

				// prepare first arg
				mv.visitVarInsn(ALOAD, localFirstArg);

				Label lblAllEnd = new Label();
				{
					Label lblNotNull = new Label();
					mv.visitJumpInsn(IFNULL, lblNotNull); // if(o != null)
					{
						// this.clz1
						mv.visitVarInsn(ALOAD, THIS);
						mv.visitFieldInsn(GETFIELD, clzName, templateClzFieldName, "Ljava/lang/Class;");

						// arg1.getClass()
						mv.visitVarInsn(ALOAD, localFirstArg);
						mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");

						// if this.clz1 = arg1.getClass()
						Label lblIfNotEq = new Label();
						mv.visitJumpInsn(IF_ACMPNE, lblIfNotEq);
						{
							// template1.exec(group, template, out, argv);
							doInvokeActionExec(mv, clzName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
							mv.visitJumpInsn(GOTO, lblAllEnd);
						}
						// else
						mv.visitLabel(lblIfNotEq);
						{

							{// this.clz1 = arg1.getClass()
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitVarInsn(ALOAD, localFirstArg);
								mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
								mv.visitFieldInsn(PUTFIELD, clzName, templateClzFieldName, "Ljava/lang/Class;");
							}

							{// template1Action =
								// template.get(o.getClass().getName(),
								// tempalte1LeadingClass);
								mv.visitVarInsn(ALOAD, THIS);

								mv.visitInsn(DUP);

								group.compile(clzName, cw, mv, context);
								name.compile(clzName, cw, mv, context);
								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
										"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
								mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));

								// cost this
								mv.visitFieldInsn(PUTFIELD, clzName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));

								mv.visitInsn(DUP); // dup this
								mv.visitFieldInsn(GETFIELD, clzName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));// cost

								{// arg1.getClass().getName
									mv.visitVarInsn(ALOAD, localFirstArg);
									mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
									mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
								}

								mv.visitVarInsn(ALOAD, localSubArgv);

								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(CompiledST.class), "get", "(Ljava/lang/String;[Ljava/lang/Object;)"
										+ Type.getDescriptor(Action.class));
								mv.visitFieldInsn(PUTFIELD, clzName, templateActionFieldName, Type.getDescriptor(Action.class));
							}

							doInvokeActionExec(mv, clzName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);

						}
						mv.visitJumpInsn(GOTO, lblAllEnd);
					}
					mv.visitLabel(lblNotNull);
					{// if (this.clz1 == Void.class) {
						mv.visitVarInsn(ALOAD, THIS);
						mv.visitFieldInsn(GETFIELD, clzName, templateClzFieldName, "Ljava/lang/Class;");
						mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
						Label _notEq = new Label();
						mv.visitJumpInsn(IF_ACMPNE, _notEq);
						{
							doInvokeActionExec(mv, clzName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
							mv.visitJumpInsn(GOTO, lblAllEnd);
						}
						mv.visitLabel(_notEq);
						{

							{ // this.clz1 = Void.class;
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
								mv.visitFieldInsn(PUTFIELD, clzName, templateClzFieldName, "Ljava/lang/Class;");
							}

							{
								// template1 =
								// template.get(Void.class.getName(), );
								mv.visitVarInsn(ALOAD, THIS);

								mv.visitInsn(DUP); // dup this

								// group.lookupTemplate(name)
								group.compile(clzName, cw, mv, context);
								name.compile(clzName, cw, mv, context);
								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
										"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
								mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));

								// cost this
								mv.visitFieldInsn(PUTFIELD, clzName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));

								mv.visitInsn(DUP); // dup this
								mv.visitFieldInsn(GETFIELD, clzName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));// cost

								// Void.class.getName
								mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
								mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");

								mv.visitVarInsn(ALOAD, localSubArgv);

								// template.get
								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(CompiledST.class), "get", "(Ljava/lang/String;[Ljava/lang/Object;)"
										+ Type.getDescriptor(Action.class));
								mv.visitFieldInsn(PUTFIELD, clzName, templateActionFieldName, Type.getDescriptor(Action.class));
							}

							doInvokeActionExec(mv, clzName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
						}

					}
				}
				mv.visitLabel(lblAllEnd);
			}
		}

		private void doInvokeActionExec(MethodVisitor mv, String thisClassName, String templateFieldName, String templateTemplateFieldName,
				int localStringBuilder, int localSubArgv) {
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, thisClassName, templateFieldName, Type.getDescriptor(Action.class));

			mv.visitVarInsn(ALOAD, 1);

			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, thisClassName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));

			mv.visitVarInsn(ALOAD, localStringBuilder);
			mv.visitVarInsn(ALOAD, localSubArgv);
			mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(Action.class), "exec",
					"(" + Type.getDescriptor(STGroup.class) + Type.getDescriptor(CompiledST.class) + Type.getDescriptor(StringBuilder.class)
							+ "[Ljava/lang/Object;)V");
		}

		public Class<?> compileInlineAppend(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();
			Class<?> firstType = null;
			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				firstType = evalValue(clzName, args.get(0).value, cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz = evalValue(clzName, args.get(i).value, cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}

			int localSubArgv = context.locals++; // 1 + 1 = 2

			mv.visitVarInsn(ASTORE, localSubArgv); // store sub argv to argv

			if (args.size() == 0) {
				if (this.name instanceof StringCst) {
					String templateName = ((StringCst) this.name).value;

					mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitVarInsn(ASTORE, ARGV);

					CompiledST subImpl = context.impl.nativeGroup.lookupTemplate(templateName);

					boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;
					if (needSubTemplate) {
						mv.visitVarInsn(ALOAD, TEMPLATE);

						group.compile(clzName, cw, mv, context);
						name.compile(clzName, cw, mv, context);
						mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
								"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
						mv.visitVarInsn(ASTORE, TEMPLATE);
					}
					CompilerContext subContext = new CompilerContext(subImpl, clzes);
					subContext.locals = context.locals;
					subImpl.code.compile(clzName, cw, mv, subContext);

					if (needSubTemplate) {
						mv.visitVarInsn(ASTORE, TEMPLATE);
					}
					mv.visitVarInsn(ASTORE, ARGV);
				} else {
					// String templateName = ((StringCst)this.name).value;
					//
					// mv.visitVarInsn(ALOAD, ARGV); // store parent argv to
					// argv
					// mv.visitVarInsn(ALOAD, localSubArgv);
					// mv.visitVarInsn(ASTORE, ARGV);
					//
					// mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent
					// argv
					// // to
					// // argv
					// mv.visitVarInsn(ALOAD, localStringBuilder);
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					//
					// CompilerContext subContext = new
					// CompilerContext(context.impl, clzes);
					// Code code =
					// subContext.impl.nativeGroup.lookupTemplate(templateName).code;
					// code.compile(clzName, cw, mv, subContext);
					//
					// group.compile(clzName, cw, mv, context);
					// name.compile(clzName, cw, mv, context);
					// mv.visitMethodInsn(INVOKEVIRTUAL,
					// Type.getInternalName(STGroup.class), "lookupTemplate",
					// "(Ljava/lang/String;)" +
					// Type.getDescriptor(TemplateImpl.class));
					//
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					// mv.visitVarInsn(ASTORE, ARGV);
				}
			} else if (List.class.isAssignableFrom(firstType)) {
				int localStringBuilder = 3; // 0 + 1 = 1
				int listLocal = context.locals++; // 2+1=3

				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					// subArgv[0] = list.next()
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);

					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitInsn(AASTORE);

					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);
					mv.visitInsn(AALOAD);

					int localFirstArg = context.locals++; // 3+1 =4
					{
						mv.visitVarInsn(ASTORE, localFirstArg);
						doCall(clzName, cw, mv, context, localStringBuilder, localSubArgv, localFirstArg);
					}
					context.locals--;// localFirstArg 4-1 = 3
				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);

				context.locals--;// listLocal 3-1 = 2
			} else {
				if (this.name instanceof StringCst) {
					String templateName = ((StringCst) this.name).value;

					mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitVarInsn(ASTORE, ARGV);

					CompiledST subImpl = context.impl.nativeGroup.lookupTemplate(templateName);

					boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;
					if (needSubTemplate) {
						mv.visitVarInsn(ALOAD, TEMPLATE);

						group.compile(clzName, cw, mv, context);
						name.compile(clzName, cw, mv, context);
						mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(STGroup.class), "lookupTemplate",
								"(Ljava/lang/String;)" + Type.getDescriptor(CompiledST.class));
						mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
						mv.visitVarInsn(ASTORE, TEMPLATE);
					}
					CompilerContext subContext = new CompilerContext(subImpl, clzes);
					subContext.locals = context.locals;
					subImpl.code.compile(clzName, cw, mv, subContext);

					if (needSubTemplate) {
						mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv
															// to
					}
					mv.visitVarInsn(ASTORE, ARGV);
				} else {
					// String templateName = ((StringCst)this.name).value;
					//
					// mv.visitVarInsn(ALOAD, ARGV); // store parent argv to
					// argv
					// mv.visitVarInsn(ALOAD, localSubArgv);
					// mv.visitVarInsn(ASTORE, ARGV);
					//
					// mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent
					// argv
					// // to
					// // argv
					// mv.visitVarInsn(ALOAD, localStringBuilder);
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					//
					// CompilerContext subContext = new
					// CompilerContext(context.impl, clzes);
					// Code code =
					// subContext.impl.nativeGroup.lookupTemplate(templateName).code;
					// code.compile(clzName, cw, mv, subContext);
					//
					// group.compile(clzName, cw, mv, context);
					// name.compile(clzName, cw, mv, context);
					// mv.visitMethodInsn(INVOKEVIRTUAL,
					// Type.getInternalName(STGroup.class), "lookupTemplate",
					// "(Ljava/lang/String;)" +
					// Type.getDescriptor(TemplateImpl.class));
					//
					// mv.visitVarInsn(ASTORE, STRINGBILDER);
					// mv.visitVarInsn(ASTORE, ARGV);
				}
			}
			context.locals--;// localSubArgv // 2-1 = 1

			return Void.class;
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
		// final Var argv;
		// final Var sb;s
		final Expr<Object> template;
		final int subTemplateIndex;
		final List<Argument> args;

		IncludeSubTemplate(Var argv, Var sb, Expr<Object> template, int subTemplateIndex, List<Argument> args) {
			// this.sb = sb;
			// this.argv = argv;
			this.template = template;
			this.subTemplateIndex = subTemplateIndex;
			this.args = args;
		}

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			return this.compileInlineToString(clzName, cw, mv, context);
		}

		private Class<?> evalValue(String clzName, Expr<Object> value, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (value instanceof IncludeSubTemplate) {
				IncludeSubTemplate sub = (IncludeSubTemplate) value;
				sub.compileInlineToString(clzName, cw, mv, context);
				return String.class;
			} else {
				Class<?> retClass = null;
				if (value instanceof FieldOf) {
					Label ifNull = new Label();
					retClass = ((FieldOf) value).compile(ifNull, clzName, cw, mv, context);
					{
						if (retClass.isPrimitive()) {
							retClass = _box_(mv, retClass);
						}
					}
					mv.visitLabel(ifNull);
				} else {
					retClass = value.compile(clzName, cw, mv, context);
				}
				return retClass;
			}
		}

		static final int THIS = 0;
		static final int GROUP = 1;
		static final int TEMPLATE = 2;
		static final int STRINGBILDER = 3;
		static final int ARGV = 4;

		public Class<?> compileInlineToString(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			int localStringBuilder = context.locals++; // 0 + 1 = 1
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
			mv.visitVarInsn(ASTORE, localStringBuilder);

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();
			Class<?> firstType = null;
			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				firstType = evalValue(clzName, args.get(0).value, cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz = evalValue(clzName, args.get(i).value, cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}

			int localSubArgv = context.locals++; // 1 + 1 = 2

			mv.visitVarInsn(ASTORE, localSubArgv); // store sub argv to argv

			if (args.size() == 0) {
				mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitVarInsn(ASTORE, ARGV);

				mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent argv to
														// argv
				mv.visitVarInsn(ALOAD, localStringBuilder);
				mv.visitVarInsn(ASTORE, STRINGBILDER);

				CompiledST subImpl = context.impl.implicitlyDefinedTemplates.get(subTemplateIndex);
				boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

				if (needSubTemplate) {
					mv.visitVarInsn(ALOAD, TEMPLATE);
					mv.visitInsn(DUP);
					mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates", Type.getDescriptor(List.class));

					mv.visitIntInsn(BIPUSH, subTemplateIndex);
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
					mv.visitVarInsn(ASTORE, TEMPLATE);
				}

				CompilerContext subContext = new CompilerContext(subImpl, clzes);
				subContext.locals = context.locals;
				subImpl.code.compile(clzName, cw, mv, subContext);

				if (needSubTemplate) {
					mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv to
				}

				mv.visitVarInsn(ASTORE, STRINGBILDER); // store parent argv to
														// argv
				mv.visitVarInsn(ASTORE, ARGV); // store parent argv to
												// argv

			} else if (List.class.isAssignableFrom(firstType)) {
				int listLocal = context.locals++; // 2+1=3

				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					// subArgv[0] = list.next()
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);

					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
					mv.visitInsn(AASTORE);

					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);
					mv.visitInsn(AALOAD);

					int localFirstArg = context.locals++; // 3+1 =4
					{
						mv.visitVarInsn(ASTORE, localFirstArg);
						doCall(clzName, cw, mv, context, localStringBuilder, localSubArgv, localFirstArg);
					}
					context.locals--;// localFirstArg 4-1 = 3
				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);

				context.locals--;// listLocal 3-1 = 2
			} else {
				mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitVarInsn(ASTORE, ARGV);

				mv.visitVarInsn(ALOAD, STRINGBILDER); // store parent argv to
														// argv
				mv.visitVarInsn(ALOAD, localStringBuilder);
				mv.visitVarInsn(ASTORE, STRINGBILDER);

				CompiledST subImpl = context.impl.implicitlyDefinedTemplates.get(subTemplateIndex);
				boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

				if (needSubTemplate) {
					mv.visitVarInsn(ALOAD, TEMPLATE);
					mv.visitInsn(DUP);
					mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates", Type.getDescriptor(List.class));

					mv.visitIntInsn(BIPUSH, subTemplateIndex);
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
					mv.visitVarInsn(ASTORE, TEMPLATE);
				}

				CompilerContext subContext = new CompilerContext(subImpl, clzes);
				subContext.locals = context.locals;
				subImpl.code.compile(clzName, cw, mv, subContext);

				if (needSubTemplate) {
					mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv to
				}
				mv.visitVarInsn(ASTORE, STRINGBILDER); // store parent argv to
														// argv
				mv.visitVarInsn(ASTORE, ARGV); // store parent argv to
												// argv
			}
			context.locals--;// localSubArgv // 2-1 = 1

			mv.visitVarInsn(ALOAD, localStringBuilder);
			mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(StringBuilder.class), "toString", "()Ljava/lang/String;");
			context.locals--; // localStringBuilder 1-1=0
			return String.class;
		}

		static int cnt = 0;

		private void doCall(String thisClassName, ClassWriter cw, final MethodVisitor mv, CompilerContext context, int localStringBuilder, int localSubArgv,
				int localFirstArg) {
			{
				FieldVisitor fv;

				String templateTemplateFieldName = "temp_" + subTemplateIndex + "_" + cnt;
				fv = cw.visitField(ACC_PRIVATE, templateTemplateFieldName, Type.getDescriptor(CompiledST.class), null, null);
				fv.visitEnd();

				String templateActionFieldName = "action_" + subTemplateIndex + "_" + cnt;
				fv = cw.visitField(ACC_PRIVATE, templateActionFieldName, Type.getDescriptor(Action.class), null, null);
				fv.visitEnd();

				String templateClzFieldName = "clz_" + subTemplateIndex + "_" + cnt++;
				fv = cw.visitField(ACC_PRIVATE, templateClzFieldName, "Ljava/lang/Class;", "Ljava/lang/Class<*>;", null);
				fv.visitEnd();

				// prepare first arg
				mv.visitVarInsn(ALOAD, localFirstArg);

				Label lblAllEnd = new Label();
				{
					Label lblNotNull = new Label();
					mv.visitJumpInsn(IFNULL, lblNotNull); // if(o != null)
					{

						// this.clz1
						mv.visitVarInsn(ALOAD, THIS);
						mv.visitFieldInsn(GETFIELD, thisClassName, templateClzFieldName, "Ljava/lang/Class;");

						// arg1.getClass()
						mv.visitVarInsn(ALOAD, localFirstArg);
						mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");

						// if this.clz1 = arg1.getClas()
						Label lblIfNotEq = new Label();
						mv.visitJumpInsn(IF_ACMPNE, lblIfNotEq);
						{
							// template1.exec(group, template, out, argv);
							doInvokeActionExec(mv, thisClassName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
							mv.visitJumpInsn(GOTO, lblAllEnd);
						}
						// else
						mv.visitLabel(lblIfNotEq);
						{
							{// this.clz1 = arg1.getClass()
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitVarInsn(ALOAD, localFirstArg);
								mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateClzFieldName, "Ljava/lang/Class;");
							}

							{// template1Action =
								// template.get(o.getClass().getName(),
								// tempalte1LeadingClass);
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitInsn(DUP);

								mv.visitVarInsn(ALOAD, TEMPLATE);
								mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates",
										Type.getDescriptor(List.class));

								mv.visitIntInsn(BIPUSH, subTemplateIndex);
								mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
								mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));

								// cost this
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));

								mv.visitInsn(DUP); // dup this
								mv.visitFieldInsn(GETFIELD, thisClassName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));// cost
																																			// this

								{// arg1.getClass().getName
									mv.visitVarInsn(ALOAD, localFirstArg);
									mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
									mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
								}
								mv.visitVarInsn(ALOAD, localSubArgv);
								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(CompiledST.class), "get", "(Ljava/lang/String;[Ljava/lang/Object;)"
										+ Type.getDescriptor(Action.class));
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateActionFieldName, Type.getDescriptor(Action.class));
							}
							doInvokeActionExec(mv, thisClassName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);

						}
						mv.visitJumpInsn(GOTO, lblAllEnd);
					}
					mv.visitLabel(lblNotNull);
					{// if (this.clz1 == Void.class) {
						mv.visitVarInsn(ALOAD, THIS);
						mv.visitFieldInsn(GETFIELD, thisClassName, templateClzFieldName, "Ljava/lang/Class;");
						mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
						Label _notEq = new Label();
						mv.visitJumpInsn(IF_ACMPNE, _notEq);
						{
							doInvokeActionExec(mv, thisClassName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
							mv.visitJumpInsn(GOTO, lblAllEnd);
						}
						mv.visitLabel(_notEq);
						{

							{ // this.clz1 = Void.class;
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateClzFieldName, "Ljava/lang/Class;");
							}

							{
								// template1 =
								// template.get(Void.class.getName(), );
								mv.visitVarInsn(ALOAD, THIS);
								mv.visitInsn(DUP);// dup this

								mv.visitVarInsn(ALOAD, TEMPLATE);
								mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates",
										Type.getDescriptor(List.class));
								mv.visitIntInsn(BIPUSH, subTemplateIndex);
								mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
								mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));

								// cost this
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));

								mv.visitInsn(DUP); // dup this
								mv.visitFieldInsn(GETFIELD, thisClassName, templateTemplateFieldName, Type.getDescriptor(CompiledST.class));// cost
																																			// this

								// Void.class.getName
								mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
								mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");

								mv.visitVarInsn(ALOAD, localSubArgv);

								// template.get
								mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(CompiledST.class), "get", "(Ljava/lang/String;[Ljava/lang/Object;)"
										+ Type.getDescriptor(Action.class));
								mv.visitFieldInsn(PUTFIELD, thisClassName, templateActionFieldName, Type.getDescriptor(Action.class));
							}

							doInvokeActionExec(mv, thisClassName, templateActionFieldName, templateTemplateFieldName, localStringBuilder, localSubArgv);
						}

					}
				}
				mv.visitLabel(lblAllEnd);
			}
		}

		private void doInvokeActionExec(MethodVisitor mv, String thisClassName, String tempalteActionFieldName, String tempalteTemplateFieldName,
				int localStringBuilder, int localSubArgv) {

			// action
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, thisClassName, tempalteActionFieldName, Type.getDescriptor(Action.class));

			// stgroup
			mv.visitVarInsn(ALOAD, 1);

			// template
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, thisClassName, tempalteTemplateFieldName, Type.getDescriptor(CompiledST.class));

			mv.visitVarInsn(ALOAD, localStringBuilder);
			mv.visitVarInsn(ALOAD, localSubArgv);
			mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(Action.class), "exec",
					"(" + Type.getDescriptor(STGroup.class) + Type.getDescriptor(CompiledST.class) + Type.getDescriptor(StringBuilder.class)
							+ "[Ljava/lang/Object;)V");
		}

		public Class<?> compileInlineAppend(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {

			mv.visitIntInsn(BIPUSH, args.size());
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");

			List<Class<?>> clzes = Lists.newArrayList();
			Class<?> firstType = null;
			if (args.size() > 0) {
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);

				firstType = evalValue(clzName, args.get(0).value, cw, mv, context);
				clzes.add(firstType);

				mv.visitInsn(AASTORE);

				for (int i = 1; i < args.size(); i++) {
					mv.visitInsn(DUP);
					mv.visitIntInsn(BIPUSH, i);
					Class<?> clz = evalValue(clzName, args.get(i).value, cw, mv, context);
					mv.visitInsn(AASTORE);
					clzes.add(clz);
				}
			}

			int localSubArgv = context.locals++; // 1 + 1 = 2

			mv.visitVarInsn(ASTORE, localSubArgv); // store sub argv to argv

			if (args.size() == 0) {
				mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitVarInsn(ASTORE, ARGV);

				CompiledST subImpl = context.impl.implicitlyDefinedTemplates.get(subTemplateIndex);

				boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

				if (needSubTemplate) {
					mv.visitVarInsn(ALOAD, TEMPLATE);
					mv.visitInsn(DUP);
					mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates", Type.getDescriptor(List.class));

					mv.visitIntInsn(BIPUSH, subTemplateIndex);
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
					mv.visitVarInsn(ASTORE, TEMPLATE);
				}
				CompilerContext subContext = new CompilerContext(subImpl, clzes);
				subContext.locals = context.locals;
				subImpl.code.compile(clzName, cw, mv, subContext);

				if (needSubTemplate) {
					mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv to
				}
				mv.visitVarInsn(ASTORE, ARGV); // store parent argv to

				// argv
			} else if (List.class.isAssignableFrom(firstType)) {
				int localStringBuilder = 3; // 0 + 1 = 1
				int listLocal = context.locals++; // 2+1=3

				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{

					// subArgv[0] = list.next()
					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);

					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
					mv.visitInsn(AASTORE);

					mv.visitVarInsn(ALOAD, localSubArgv);
					mv.visitIntInsn(BIPUSH, 0);
					mv.visitInsn(AALOAD);
					int localFirstArg = context.locals++; // 3+1 =4

					{
						mv.visitVarInsn(ASTORE, localFirstArg);
						doCall(clzName, cw, mv, context, localStringBuilder, localSubArgv, localFirstArg);
					}
					context.locals--;// localFirstArg 4-1 = 3

				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);

				context.locals--;// listLocal 3-1 = 2

			} else {
				mv.visitVarInsn(ALOAD, ARGV); // store parent argv to argv
				mv.visitVarInsn(ALOAD, localSubArgv);
				mv.visitVarInsn(ASTORE, ARGV);

				CompiledST subImpl = context.impl.implicitlyDefinedTemplates.get(subTemplateIndex);

				boolean needSubTemplate = subImpl.implicitlyDefinedTemplates != null && subImpl.implicitlyDefinedTemplates.size() > 0;

				if (needSubTemplate) {
					mv.visitVarInsn(ALOAD, TEMPLATE);
					mv.visitInsn(DUP);
					mv.visitFieldInsn(GETFIELD, Type.getInternalName(CompiledST.class), "implicitlyDefinedTemplates", Type.getDescriptor(List.class));

					mv.visitIntInsn(BIPUSH, subTemplateIndex);
					mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(List.class), "get", "(I)Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(CompiledST.class));
					mv.visitVarInsn(ASTORE, TEMPLATE);
				}
				CompilerContext subContext = new CompilerContext(subImpl, clzes);
				subContext.locals = context.locals;
				subImpl.code.compile(clzName, cw, mv, subContext);

				if (needSubTemplate) {
					mv.visitVarInsn(ASTORE, TEMPLATE); // store parent argv to
				}
				mv.visitVarInsn(ASTORE, ARGV); // store parent argv to
												// argv
			}
			context.locals--;// localSubArgv // 2-1 = 1

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

	static abstract class Const<T> extends Expression<T> {

	}

	static class LongCst extends Const<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			// computes !e1 by evaluating 1 - e1
			mv.visitInsn(ICONST_1);
			e1.compile(clzName, cw, mv, context);
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			Class<?> clz = e1.compile(clzName, cw, mv, context);
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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

				Class<?> clz = e.compile(clzName, cw, mv, context);
				if (clz == Boolean.TYPE) {
					mv.visitJumpInsn(IFEQ, ifFalse);
					block.compile(clzName, cw, mv, context);
				} else {
					mv.visitJumpInsn(IFNULL, ifFalse);
					block.compile(clzName, cw, mv, context);
				}
				if (cnt > 1) {
					mv.visitJumpInsn(GOTO, ifEnd);
					mv.visitLabel(ifFalse);
				}
				cnt--;
			}

			if (blockelse != null) {
				blockelse.compile(clzName, cw, mv, context);
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

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (expr instanceof Const) {
				sb.compile(clzName, cw, mv, context);// push sb
				Class<?> clz = expr.compile(clzName, cw, mv, context);
				output(clz, clzName, cw, mv, context);
				mv.visitInsn(POP); // pop sb
			} else if (expr instanceof IncludeSubTemplate) {
				IncludeSubTemplate sub = (IncludeSubTemplate) expr;
				sub.compileInlineAppend(clzName, cw, mv, context);
			} else if (expr instanceof ArgRefer) {
				sb.compile(clzName, cw, mv, context);
				Class<?> clz = expr.compile(clzName, cw, mv, context);

				if (clz.isPrimitive()) {
					output(clz, clzName, cw, mv, context);
				} else {
					Label ifFalse = new Label();
					Label ifEnd = new Label();
					mv.visitInsn(DUP);
					mv.visitJumpInsn(IFNULL, ifFalse);
					{
						output(clz, clzName, cw, mv, context);
						mv.visitJumpInsn(GOTO, ifEnd);
					}
					mv.visitLabel(ifFalse);
					{
						mv.visitInsn(POP); // pop object

					}
					mv.visitLabel(ifEnd);
				}
				mv.visitInsn(POP); // pop Stringbuilder
			} else if (expr instanceof FieldOf) {
				sb.compile(clzName, cw, mv, context);
				Label ifEnd = new Label();
				Label ifNull = new Label();

				Class<?> clz = ((FieldOf) expr).compile(ifNull, clzName, cw, mv, context);
				{
					output(clz, clzName, cw, mv, context);
					mv.visitJumpInsn(GOTO, ifEnd);
				}
				mv.visitLabel(ifNull);
				{
					mv.visitInsn(POP); // pop object
				}
				mv.visitLabel(ifEnd);

				mv.visitInsn(POP); // pop Stringbuilder
			} else {
				sb.compile(clzName, cw, mv, context);
				Class<?> clz = expr.compile(clzName, cw, mv, context);

				Label ifFalse = new Label();
				Label ifEnd = new Label();
				mv.visitInsn(DUP);
				mv.visitJumpInsn(IFNULL, ifFalse);
				{
					output(clz, clzName, cw, mv, context);
					mv.visitJumpInsn(GOTO, ifEnd);
				}
				mv.visitLabel(ifFalse);
				{
					mv.visitInsn(POP); // pop object
				}
				mv.visitLabel(ifEnd);

				mv.visitInsn(POP); // pop Stringbuilder
			}

			return Void.TYPE;
		}

		private void output(Class<?> clz, String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
			if (String.class == clz) {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(String.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			} else if (clz.isPrimitive()) {
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(clz) + ")Ljava/lang/StringBuilder;");
			} else if (StringBuilder.class == clz) {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(StringBuilder.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(clz) + ")Ljava/lang/StringBuilder;");
			} else if (List.class.isAssignableFrom(clz)) {

				int locals = context.locals;

				int listLocal = locals++;

				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(List.class));
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");

				mv.visitVarInsn(ASTORE, listLocal);
				Label forCompare = new Label();
				mv.visitJumpInsn(GOTO, forCompare);

				Label forBegin = new Label();
				mv.visitLabel(forBegin);
				{
					mv.visitVarInsn(ALOAD, listLocal);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
				}
				mv.visitLabel(forCompare);
				mv.visitVarInsn(ALOAD, listLocal);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, forBegin);
				// }
			} else if (Map.class.isAssignableFrom(clz)) {

				int locals = context.locals;

				int listLocal = locals++;

				// // initial
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Map.class));
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

			} else if (ST.class.isAssignableFrom(clz)) {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(ST.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(ST.class), "render", "()Ljava/lang/String;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(String.class) + ")Ljava/lang/StringBuilder;");
			} else {
				mv.visitTypeInsn(CHECKCAST, Type.getInternalName(Object.class));
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + Type.getDescriptor(Object.class) + ")Ljava/lang/StringBuilder;");
			}
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

	static class StringCst extends Const<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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
		public Class<?> compile(String clzName, ClassWriter cw, MethodVisitor mv, CompilerContext context) {
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

	static class YesnoCst extends Const<Integer> {
		final int value;

		YesnoCst(boolean v) {
			if (v) this.value = 1;
			else this.value = 0;
		}

		public Class<?> compile(String clzName, ClassWriter cw, final MethodVisitor mv, CompilerContext context) {
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
		if (args == null) {
			args = Lists.newArrayList();
		}
		return new Include(group, name, args);
	}

	@SuppressWarnings("rawtypes")
	public Expr<Object> opInclude(Expr<Object> group, Expr<String> name, List<Expr> target, List<Argument> args) {
		if (args == null) {
			args = Lists.newArrayList();
		}
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

	public void tpReferTemplate(STGroup group, String name, CompiledST template) {
		template.name = name;
		group.templates.put(name, template);
	}

	public CompiledST tpTemplate(STGroup group, Statement statement, List<Var> arges, List<CompiledST> implicitlyDefinedTemplates) {
		Preconditions.checkNotNull(group);
		Preconditions.checkNotNull(statement);
		List<String> argNames = Lists.newArrayList();
		for (Var var : arges) {
			argNames.add(var.name);
		}
		if (implicitlyDefinedTemplates.size() > 0) {
			return new CompiledST(group, statement, argNames, implicitlyDefinedTemplates);
		} else {
			return new CompiledST(group, statement, argNames);
		}
	}

	public Statement trimLastNEWLINE(Statement t1) {
		Block block = (Block) t1;
		if (block.statements.size() == 0) return t1;
		int last = block.statements.size() - 1;

		Statement lastStatement = block.statements.get(last);
		if (!(lastStatement instanceof Output)) return t1;

		Output output = (Output) lastStatement;
		if (!(output.expr instanceof StringCst)) return t1;

		StringCst s = (StringCst) output.expr;
		if (!("\n".equals(s.value) || "\r\n".equals(s))) return t1;
		block.statements.remove(block.statements.size() - 1);
		return t1;
	}
}

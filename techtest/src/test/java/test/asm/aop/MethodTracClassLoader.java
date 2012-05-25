package test.asm.aop;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.NEW;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTracClassLoader extends ClassLoader {
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> type, String name) {
		try {
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassVisitor ana = new MethodAnalysis(cw);
			ClassReader classReader = new ClassReader(name);
			classReader.accept(ana, ClassReader.SKIP_FRAMES);

			byte[] code = cw.toByteArray();
			Object o = this.defineClass(name, code, 0, code.length).newInstance();
			return (T) o;
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		// try {
		//
		// Class<?> exampleClass = new
		// MethodTracClassLoader().load(Sample.class);
		// Method m = exampleClass.getMethod("main", String[].class);
		// m.invoke(null, new Object[] { null });
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	class MethodAnalysis extends ClassVisitor {
		public MethodAnalysis(ClassVisitor cv) {
			super(Opcodes.ASM4, cv);
		}

		String className;

		@Override
		public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
			className = name;
			super.visit(version, access, name, signature, superName, interfaces);
		}

		public MethodVisitor visitMethod(final int access, final String name, final String desc,
				final String signature, final String[] exceptions) {

			// final String methodName = name;
			// System.out.println(name);

			if ("<init>".equals(name) || (Opcodes.ACC_STATIC & access) > 0) {
				return cv.visitMethod(access, name, desc, signature, exceptions);
			} else {
				MethodVisitor mv;
				mv = cv.visitMethod(access, name, desc, signature, exceptions);
				mv = new AddSecurityCheckMethodAdapter(mv, name);
				return mv;
			}
		}

		class AddSecurityCheckMethodAdapter extends MethodVisitor {
			String name;

			public AddSecurityCheckMethodAdapter(MethodVisitor mv, String name) {
				super(Opcodes.ASM4, mv);
				this.name = name;
			}

			public void visitCode() {
				mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
				mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
				mv.visitInsn(DUP);
				mv.visitLdcInsn("** - exec " + name + " at ");
				mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			}
		}

		//
		//
		// public MethodVisitor visitMethod(final int access, final String name,
		// final String desc,
		// final String signature, final String[] exceptions) {
		//
		// // final String methodName = name;
		// // System.out.println(name);
		//
		// if ("<init>".equals(name) || (Opcodes.ACC_STATIC & access) > 0) {
		// return cv.visitMethod(access, name, desc, signature, exceptions);
		// } else {
		// String newName = name + "_hide";
		// MethodVisitor mv;
		// ClassVisitor cw = cv;
		// {
		// mv = cw.visitMethod(access, name, desc, signature, exceptions);
		//
		// if (desc.charAt(desc.length() - 1) != 'V') {
		// mv.visitCode();
		// mv.visitInsn(RETURN);
		// mv.visitMaxs(0, 1);
		// mv.visitEnd();
		// } else {
		// mv.visitCode();
		// mv.visitInsn(ACONST_NULL);
		// mv.visitInsn(ARETURN);
		// mv.visitMaxs(1, 1);
		// mv.visitEnd();
		// }
		//
		// // mv.visitCode();
		// // mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// // "Ljava/io/PrintStream;");
		// // mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		// // mv.visitInsn(DUP);
		// // mv.visitLdcInsn("** - exec " + name + " at ");
		// // mv.visitMethodInsn(INVOKESPECIAL,
		// // "java/lang/StringBuilder", "<init>",
		// // "(Ljava/lang/String;)V");
		// // mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
		// // "currentTimeMillis", "()J");
		// // mv.visitMethodInsn(INVOKEVIRTUAL,
		// // "java/lang/StringBuilder", "append",
		// // "(J)Ljava/lang/StringBuilder;");
		// // mv.visitMethodInsn(INVOKEVIRTUAL,
		// // "java/lang/StringBuilder", "toString",
		// // "()Ljava/lang/String;");
		// // mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
		// // "println", "(Ljava/lang/String;)V");
		// // //
		// // mv.visitVarInsn(ALOAD, 0);
		// // mv.visitVarInsn(ALOAD, 1);
		// // mv.visitMethodInsn(INVOKESPECIAL, className, newName,
		// // desc);
		// // if (desc.charAt(desc.length() - 1) != 'V') {
		// // mv.visitInsn(ARETURN);
		// // } else {
		// // mv.visitInsn(RETURN);
		// // }
		// // mv.visitMaxs(0, 0);
		// // mv.visitEnd();
		// }
		// // MethodVisitor mvRaw = cv.visitMethod(Opcodes.ACC_PRIVATE,
		// // newName, desc, signature, exceptions);
		// MethodVisitor mvRaw = cv.visitMethod(access, newName, desc,
		// signature, exceptions);
		// return mvRaw;
		// }
		// }
	}
}

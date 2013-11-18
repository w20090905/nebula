package nebula.simpletemplate;

import nebula.lang.NebulaClassLoader;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ActionComplier_anonymous__nop_0Dump implements Opcodes {

	public static void main(String[] args) {
		try {
			byte[] b = dump();

			Class<?> expClass = NebulaClassLoader.defineClass("ActionComplier_anonymous__nop_0", b);
			// instantiates this compiled expression class...
			expClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "ActionComplier_anonymous__nop_0", null, "java/lang/Object", new String[] { "nebula/simpletemplate/Action" });

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "exec",
					"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V", null,
					new String[] { "java/io/IOException" });
			mv.visitCode();
			mv.visitInsn(ICONST_1);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);
			Label ifFalse = new Label();
			mv.visitJumpInsn(IFNULL, ifFalse);
			mv.visitInsn(ICONST_1);
//			mv.visitVarInsn(ISTORE, 5);
			Label ifEnd = new Label();
			mv.visitJumpInsn(GOTO, ifEnd);
			mv.visitLabel(ifFalse);
			mv.visitInsn(ICONST_0);
//			mv.visitVarInsn(ISTORE, 5);
			mv.visitLabel(ifEnd);
//			mv.visitInsn(ISUB);
//			Label l2 = new Label();
//			mv.visitJumpInsn(IFEQ, l2);
//			mv.visitVarInsn(ALOAD, 3);
//			mv.visitLdcInsn("xx");
//			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
//			mv.visitInsn(POP);
//			mv.visitLabel(l2);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

}

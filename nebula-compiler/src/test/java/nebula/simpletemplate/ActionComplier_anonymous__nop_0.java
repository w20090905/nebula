package nebula.simpletemplate;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ActionComplier_anonymous__nop_0 implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

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
			mv.visitVarInsn(ALOAD, 3);
			mv.visitLdcInsn("load ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitInsn(POP);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("box");
			mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/STGroup", "getTemplate", "(Ljava/lang/String;)Lnebula/simpletemplate/TemplateImpl;");
			mv.visitIntInsn(BIPUSH, 1);
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
			mv.visitInsn(DUP);
			mv.visitIntInsn(BIPUSH, 0);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitVarInsn(ALOAD, 4);
			
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
			mv.visitVarInsn(ASTORE, 4);
			mv.visitIntInsn(BIPUSH, 0);
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
			mv.visitVarInsn(ASTORE, 4);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
			mv.visitVarInsn(ASTORE, 5);
			mv.visitVarInsn(ASTORE, 4);
			mv.visitVarInsn(ASTORE, 4);
			mv.visitVarInsn(ALOAD, 5);
			mv.visitInsn(AASTORE);
			mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/TemplateImpl", "exec", "([Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitInsn(POP);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitLdcInsn(";");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitInsn(POP);
			mv.visitInsn(RETURN);
			mv.visitMaxs(9, 6);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

package test.language.antlr.StringTemplate;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class templateDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_5, ACC_PUBLIC, "template", null, "java/lang/Object", new String[] { "org/lilystudio/smarty4j/IParser" });

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
			mv = cw.visitMethod(ACC_PUBLIC, "merge", "(Lorg/lilystudio/smarty4j/Context;Ljava/io/Writer;)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL, "org/lilystudio/smarty4j/Context", "getTemplate", "()Lorg/lilystudio/smarty4j/Template;");
			mv.visitVarInsn(ASTORE, 3);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("person");
			mv.visitMethodInsn(INVOKEVIRTUAL, "org/lilystudio/smarty4j/Context", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitVarInsn(ASTORE, 4);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn("<html>\r\n<head>\r\n<title>");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("name");
			mv.visitMethodInsn(INVOKESTATIC, "org/lilystudio/smarty4j/expression/MapExtended", "getValue",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn("</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write", "(Ljava/lang/String;)V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(3, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

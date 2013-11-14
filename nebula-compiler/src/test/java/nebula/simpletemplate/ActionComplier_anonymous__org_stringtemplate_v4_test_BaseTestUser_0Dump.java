package nebula.simpletemplate;

import nebula.lang.NebulaClassLoader;

import org.mockito.asm.Type;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ActionComplier_anonymous__org_stringtemplate_v4_test_BaseTestUser_0Dump implements Opcodes {
	public static void main(String[] args) {
		try {
			byte[] b = dump();

			Class<?> expClass = NebulaClassLoader.defineClass("ActionComplier_anonymous__org_stringtemplate_v4_test_BaseTestUser_0", b);
			// instantiates this compiled expression class...
			Action expr = (Action) expClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] dump1() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "ActionComplier_anonymous__org_stringtemplate_v4_test_BaseTestUser_0", null, "java/lang/Object",
				new String[] { "nebula/simpletemplate/Action" });

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
					"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/CompiledST;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V", null,
					new String[] { "java/io/IOException" });
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 3);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);

			mv.visitInsn(DUP);
			Label ifEnd = new Label();
			Label ifFalse = new Label();
			mv.visitJumpInsn(IFNULL, ifFalse);
			{
				mv.visitTypeInsn(CHECKCAST, "org/stringtemplate/v4/test/BaseTest$User");
				mv.visitFieldInsn(GETFIELD, "org/stringtemplate/v4/test/BaseTest$User", "id", "I");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitJumpInsn(GOTO, ifEnd);
			}
			mv.visitLabel(ifFalse);
			{
				mv.visitInsn(POP);
				mv.visitLdcInsn(0);
				mv.visitInsn(ACONST_NULL);
			}
			mv.visitLabel(ifEnd);

			Label if2False = new Label();
			Label if2End = new Label();
			mv.visitJumpInsn(IFNULL, if2False);
			{
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
				mv.visitJumpInsn(GOTO, if2End);
			}
			mv.visitLabel(if2False);
			{
				mv.visitInsn(POP);
				// mv.visitInsn(POP);
			}
			mv.visitLabel(if2End);

			mv.visitInsn(POP); // pop SB
			mv.visitInsn(RETURN);
			mv.visitMaxs(3, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "ActionComplier_anonymous__org_stringtemplate_v4_test_BaseTestUser_0", null, "java/lang/Object",
				new String[] { "nebula/simpletemplate/Action" });

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
					"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/CompiledST;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V", null,
					new String[] { "java/io/IOException" });
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 3);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);
			mv.visitInsn(DUP);
			Label l0 = new Label();
			mv.visitJumpInsn(IFNULL, l0);
			mv.visitTypeInsn(CHECKCAST, "org/stringtemplate/v4/test/BaseTest$User");
			mv.visitMethodInsn(INVOKEVIRTUAL, "org/stringtemplate/v4/test/BaseTest$User", "getName", "()Ljava/lang/String;");
			mv.visitLabel(l0);
			mv.visitInsn(DUP);
			Label l1 = new Label();
			mv.visitJumpInsn(IFNULL, l1);
			mv.visitTypeInsn(CHECKCAST, "java/lang/String");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			Label l2 = new Label();
			mv.visitJumpInsn(GOTO, l2);
			mv.visitLabel(l1);
			mv.visitInsn(POP);
			mv.visitLabel(l2);
			mv.visitInsn(POP);
			mv.visitInsn(RETURN);
			mv.visitMaxs(3, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

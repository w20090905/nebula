package nebula.simpletemplate;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class TestCodeMapDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "nebula/simpletemplate/TestCodeMap", null, "java/lang/Object", new String[] { "nebula/simpletemplate/Action" });

		cw.visitSource("TestCodeMap.java", null);

		{
			fv = cw.visitField(0, "tempalte1LeadingClass", "Ljava/lang/Class;", "Ljava/lang/Class<*>;", null);
			fv.visitEnd();
		}
		{
			fv = cw.visitField(0, "template1Action", "Lnebula/simpletemplate/Action;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(8, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lnebula/simpletemplate/TestCodeMap;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "exec",
					"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V", null,
					new String[] { "java/io/IOException" });
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(17, l0);
			mv.visitVarInsn(ALOAD, 4);
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);
			mv.visitVarInsn(ASTORE, 5);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(18, l1);
			mv.visitVarInsn(ALOAD, 5);

			Label _gotoAllEnd = new Label();
			Label _gotoNULL = new Label();
			mv.visitJumpInsn(IFNULL, _gotoNULL);// if (o != null) {
			{

				// // if (tempalte1LeadingClass == o.getClass()) {
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "tempalte1LeadingClass", "Ljava/lang/Class;");
				mv.visitVarInsn(ALOAD, 5);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");

				Label _gotoNotEq = new Label();
				mv.visitJumpInsn(IF_ACMPNE, _gotoNotEq);
				{
					{ // call template1Action.exec(group, template, out, argv);
						mv.visitVarInsn(ALOAD, 0);
						mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
						mv.visitVarInsn(ALOAD, 1);
						mv.visitVarInsn(ALOAD, 2);
						mv.visitVarInsn(ALOAD, 3);
						mv.visitVarInsn(ALOAD, 4);
						mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
								"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
					}

					mv.visitJumpInsn(GOTO, _gotoAllEnd);
				}

				mv.visitLabel(_gotoNotEq); // else
				{
					// template1Action = template.get(Void.class.getName(),
					// Void.class);
					{
						mv.visitVarInsn(ALOAD, 0);
						mv.visitVarInsn(ALOAD, 2);
						mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
						mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
						mv.visitInsn(ICONST_1);
						mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
						mv.visitInsn(DUP);
						mv.visitInsn(ICONST_0);
						mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
						mv.visitInsn(AASTORE);
						mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/TemplateImpl", "get",
								"(Ljava/lang/String;[Ljava/lang/Object;)Lnebula/simpletemplate/Action;");
						mv.visitFieldInsn(PUTFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
					}

					{// tempalte1LeadingClass = Void.class;
						mv.visitVarInsn(ALOAD, 0);
						mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
						mv.visitFieldInsn(PUTFIELD, "nebula/simpletemplate/TestCodeMap", "tempalte1LeadingClass", "Ljava/lang/Class;");
					}

					{// template1Action.exec(group, template, out, argv);
						mv.visitVarInsn(ALOAD, 0);
						mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
						mv.visitVarInsn(ALOAD, 1);
						mv.visitVarInsn(ALOAD, 2);
						mv.visitVarInsn(ALOAD, 3);
						mv.visitVarInsn(ALOAD, 4);
						mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
								"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
					}
					mv.visitJumpInsn(GOTO, _gotoAllEnd);
				}
			}
			mv.visitLabel(_gotoNULL);
			{
				// if (tempalte1LeadingClass == Void.class) {

				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "tempalte1LeadingClass", "Ljava/lang/Class;");
				mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
				Label _notEq = new Label();
				mv.visitJumpInsn(IF_ACMPNE, _notEq);
				{
					{
						mv.visitVarInsn(ALOAD, 0);
						mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
						mv.visitVarInsn(ALOAD, 1);
						mv.visitVarInsn(ALOAD, 2);
						mv.visitVarInsn(ALOAD, 3);
						mv.visitVarInsn(ALOAD, 4);
						mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
								"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
					}
					mv.visitJumpInsn(GOTO, _gotoAllEnd);
				}
				mv.visitLabel(_notEq);
				mv.visitLineNumber(30, _notEq);
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitVarInsn(ALOAD, 5);
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
					mv.visitFieldInsn(PUTFIELD, "nebula/simpletemplate/TestCodeMap", "tempalte1LeadingClass", "Ljava/lang/Class;");
					mv.visitVarInsn(ALOAD, 0);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitVarInsn(ALOAD, 5);
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
					mv.visitInsn(ICONST_1);
					mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
					mv.visitInsn(DUP);
					mv.visitInsn(ICONST_0);
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "tempalte1LeadingClass", "Ljava/lang/Class;");
					mv.visitInsn(AASTORE);
					mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/TemplateImpl", "get",
							"(Ljava/lang/String;[Ljava/lang/Object;)Lnebula/simpletemplate/Action;");

					{

						mv.visitFieldInsn(PUTFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
					}

					{
						mv.visitVarInsn(ALOAD, 0);
						mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TestCodeMap", "template1Action", "Lnebula/simpletemplate/Action;");
						mv.visitVarInsn(ALOAD, 1);
						mv.visitVarInsn(ALOAD, 2);
						mv.visitVarInsn(ALOAD, 3);
						mv.visitVarInsn(ALOAD, 4);
						mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
								"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
					}
				}
			}
			mv.visitMaxs(7, 6);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

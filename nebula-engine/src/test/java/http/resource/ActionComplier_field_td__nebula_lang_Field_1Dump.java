package http.resource;

import nebula.lang.NebulaClassLoader;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ActionComplier_field_td__nebula_lang_Field_1Dump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "ActionComplier_field_td__nebula_lang_Field_1", null, "java/lang/Object",
				new String[] { "nebula/simpletemplate/Action" });

		{
			fv = cw.visitField(ACC_PRIVATE, "temp_0_0", "Lnebula/simpletemplate/Action;", null, null);
			fv.visitEnd();
		}
		{
			fv = cw.visitField(ACC_PRIVATE, "clz_0_0", "Ljava/lang/Class;", "Ljava/lang/Class<*>;", null);
			fv.visitEnd();
		}
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
			int TMP_SB = 5;
			int tmpSubArgv = 6;
			mv = cw.visitMethod(ACC_PUBLIC, "exec",
					"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V", null,
					new String[] { "java/io/IOException" });
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 3);

			{ // tmpSb = new Stringbuilder
				mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
				mv.visitInsn(DUP);
				mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
				mv.visitVarInsn(ASTORE, TMP_SB);
			}

			{// tmpSubArgv = new Object[]
				mv.visitIntInsn(BIPUSH, 1);
				mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitVarInsn(ALOAD, 4);
				mv.visitInsn(ICONST_0);
				mv.visitInsn(AALOAD);
				mv.visitInsn(AASTORE);
				mv.visitVarInsn(ASTORE, tmpSubArgv);
			}

			mv.visitVarInsn(ALOAD, 4);
			mv.visitVarInsn(ALOAD, tmpSubArgv);
			mv.visitVarInsn(ASTORE, 4);

			mv.visitVarInsn(ALOAD, 3);
			mv.visitVarInsn(ALOAD, TMP_SB);
			mv.visitVarInsn(ASTORE, 3);
			{
				int TMP_sub_sub_ARGV = 7;
				int tmpList= 9;
				mv.visitIntInsn(BIPUSH, 1);
				mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
				mv.visitInsn(DUP);
				mv.visitIntInsn(BIPUSH, 0);
				
				mv.visitVarInsn(ALOAD, 4);
				mv.visitInsn(ICONST_0);
				mv.visitInsn(AALOAD);
				mv.visitTypeInsn(CHECKCAST, "nebula/lang/Field");
				mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/lang/Field", "getType", "()Lnebula/lang/Type;");
				mv.visitTypeInsn(CHECKCAST, "nebula/lang/Type");
				mv.visitMethodInsn(INVOKEINTERFACE, "nebula/lang/Type", "getFields", "()Ljava/util/List;");
				mv.visitInsn(AASTORE);
				
				mv.visitVarInsn(ASTORE, TMP_sub_sub_ARGV);
				mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
				
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");
				mv.visitVarInsn(ASTORE, tmpList);
				Label l0 = new Label();
				mv.visitJumpInsn(GOTO, l0);
				Label l1 = new Label();
				mv.visitLabel(l1);
				mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitVarInsn(ALOAD, tmpList);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
				mv.visitTypeInsn(CHECKCAST, "java/lang/Object");
				mv.visitInsn(AASTORE);
				mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitInsn(AALOAD);
				mv.visitVarInsn(ASTORE, 7);
				mv.visitVarInsn(ALOAD, 7);
				Label l2 = new Label();
				mv.visitJumpInsn(IFNULL, l2);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "clz_0_0", "Ljava/lang/Class;");
				mv.visitVarInsn(ALOAD, 7);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				Label l3 = new Label();
				mv.visitJumpInsn(IF_ACMPNE, l3);
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
					mv.visitVarInsn(ALOAD, 1);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitVarInsn(ALOAD, 3);
					mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
					mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
							"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
				}
				mv.visitJumpInsn(GOTO, l0);
				mv.visitLabel(l3);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ALOAD, 7);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitFieldInsn(PUTFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "clz_0_0", "Ljava/lang/Class;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TemplateImpl", "implicitlyDefinedTemplates", "Ljava/util/List;");
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
				mv.visitTypeInsn(CHECKCAST, "nebula/simpletemplate/TemplateImpl");
				mv.visitVarInsn(ALOAD, 7);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
				mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/TemplateImpl", "get",
						"(Ljava/lang/String;[Ljava/lang/Object;)Lnebula/simpletemplate/Action;");
				mv.visitFieldInsn(PUTFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
					mv.visitVarInsn(ALOAD, 1);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitVarInsn(ALOAD, 3);
					mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
					mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
							"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
				}
				mv.visitJumpInsn(GOTO, l0);
				mv.visitLabel(l2);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "clz_0_0", "Ljava/lang/Class;");
				mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
				Label l4 = new Label();
				mv.visitJumpInsn(IF_ACMPNE, l4);
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
					mv.visitVarInsn(ALOAD, 1);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitVarInsn(ALOAD, 3);
					mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
					mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
							"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
				}
				mv.visitJumpInsn(GOTO, l0);
				mv.visitLabel(l4);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
				mv.visitFieldInsn(PUTFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "clz_0_0", "Ljava/lang/Class;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitFieldInsn(GETFIELD, "nebula/simpletemplate/TemplateImpl", "implicitlyDefinedTemplates", "Ljava/util/List;");
				mv.visitIntInsn(BIPUSH, 0);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
				mv.visitTypeInsn(CHECKCAST, "nebula/simpletemplate/TemplateImpl");
				mv.visitLdcInsn(Type.getType("Ljava/lang/Void;"));
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
				mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/simpletemplate/TemplateImpl", "get",
						"(Ljava/lang/String;[Ljava/lang/Object;)Lnebula/simpletemplate/Action;");
				mv.visitFieldInsn(PUTFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD, "ActionComplier_field_td__nebula_lang_Field_1", "temp_0_0", "Lnebula/simpletemplate/Action;");
					mv.visitVarInsn(ALOAD, 1);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitVarInsn(ALOAD, 3);
					mv.visitVarInsn(ALOAD, TMP_sub_sub_ARGV);
					mv.visitMethodInsn(INVOKEINTERFACE, "nebula/simpletemplate/Action", "exec",
							"(Lnebula/simpletemplate/STGroup;Lnebula/simpletemplate/TemplateImpl;Ljava/lang/StringBuilder;[Ljava/lang/Object;)V");
				}
				mv.visitLabel(l0);
				mv.visitVarInsn(ALOAD, tmpList);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, l1);
			}
			mv.visitVarInsn(ASTORE, 3);
			mv.visitVarInsn(ASTORE, 4);
			mv.visitVarInsn(ALOAD, TMP_SB);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
			mv.visitInsn(POP);
			mv.visitInsn(RETURN);
			mv.visitMaxs(8, 8);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		Class<?> clz = NebulaClassLoader.defineClass("ActionComplier_field_td__nebula_lang_Field_1", ActionComplier_field_td__nebula_lang_Field_1Dump.dump());
		clz.newInstance();
	}
}

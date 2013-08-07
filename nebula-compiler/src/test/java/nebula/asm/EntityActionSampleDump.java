package nebula.asm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class EntityActionSampleDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "nebula/asm/EntityActionSample", null, "java/lang/Object", new String[] { "nebula/lang/EntityAction" });

		cw.visitSource("EntityActionSample.java", null);

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
			mv.visitLocalVariable("this", "Lnebula/asm/EntityActionSample;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "exec", "(Lnebula/data/Entity;Lnebula/data/DataRepos;)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(12, l0);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
			mv.visitLdcInsn(Type.getType("Lnebula/data/Entity;"));
			mv.visitLdcInsn("Person");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get", "()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");
			mv.visitVarInsn(ASTORE, 3);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(13, l1);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitLdcInsn("wangshilian");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore", "get", "(Ljava/lang/Object;)Lnebula/data/Timable;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
			mv.visitVarInsn(ASTORE, 4);
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLineNumber(14, l2);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("Age");
			mv.visitVarInsn(ALOAD, 4);
			mv.visitLdcInsn("Age");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put", "(Ljava/lang/String;Ljava/lang/Object;)V");
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLineNumber(15, l3);
			mv.visitInsn(RETURN);
			Label l4 = new Label();
			mv.visitLabel(l4);
			mv.visitLocalVariable("this", "Lnebula/asm/EntityActionSample;", null, l0, l4, 0);
			mv.visitLocalVariable("entity", "Lnebula/data/Entity;", null, l0, l4, 1);
			mv.visitLocalVariable("repos", "Lnebula/data/DataRepos;", null, l0, l4, 2);
			mv.visitLocalVariable("d", "Lnebula/data/DataStore;", "Lnebula/data/DataStore<Lnebula/data/Entity;>;", l1, l4, 3);
			mv.visitLocalVariable("e", "Lnebula/data/Entity;", null, l2, l4, 4);
			mv.visitMaxs(4, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
	
	public static void main(String[] args) {
		try {
			byte[] bt = dump();
			FileOutputStream fos = new FileOutputStream("tmp\\EntityActionSample.class");
			fos.write(bt);
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

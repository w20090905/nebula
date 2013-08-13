package nebula.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CopyOfEntityFunctionSampleDump extends ClassLoader implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "EntityAction0", null, "java/lang/Object", new String[] { "nebula/lang/EntityAction" });

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
			mv = cw.visitMethod(ACC_PUBLIC, "exec", "(Lnebula/data/Entity;Lnebula/data/DataRepos;)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("Height");

			mv.visitMethodInsn(INVOKESTATIC, "nebula/lang/Nebula", "filter", "(Ljava/util/List;Lnebula/lang/Clause;[Ljava/lang/Object;)Ljava/util/List;");
			
			mv.visitLdcInsn("Age");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J");
			mv.visitLdcInsn(new Long(10L));
			mv.visitInsn(LADD);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put", "(Ljava/lang/String;Ljava/lang/Object;)V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(9, 3);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	public void cp() throws Exception {
		byte[] bt = dump();
		Class<?> clz = this.defineClass("nebula.asm.EntityFunctionSample1", bt, 0, bt.length);
		clz.newInstance();
	}

	public static void main(String[] args) {
		try {
			new CopyOfEntityFunctionSampleDump().cp();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

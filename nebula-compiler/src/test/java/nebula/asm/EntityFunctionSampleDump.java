package nebula.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EntityFunctionSampleDump extends ClassLoader implements Opcodes {

	public static byte[] dump () throws Exception {

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;

		cw.visit(51, ACC_PUBLIC + ACC_SUPER, "nebula/asm/EntityFunctionSample1", "Ljava/lang/Object;Lcom/google/common/base/Function<Lnebula/data/Entity;Ljava/lang/Boolean;>;", "java/lang/Object", new String[] { "com/google/common/base/Function" });

//		cw.visitSource("EntityFunctionSample1.java", null);

		{
		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		}
		{
		mv = cw.visitMethod(ACC_PUBLIC, "apply", "(Lnebula/data/Entity;)Ljava/lang/Boolean;", null, null);
		mv.visitCode();
		mv.visitInsn(ICONST_0);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
		mv.visitInsn(ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		}
		{
		mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "apply", "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
		mv.visitMethodInsn(INVOKEVIRTUAL, "nebula/asm/EntityFunctionSample1", "apply", "(Lnebula/data/Entity;)Ljava/lang/Boolean;");
		mv.visitInsn(ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
		}
	
	public void cp() throws Exception {
		byte[]  bt = dump();
		Class<?> clz=  this.defineClass("nebula.asm.EntityFunctionSample1",  bt,0,bt.length);
		clz.newInstance();
	}
	
	public static void main(String[] args) {
		try {
			new EntityFunctionSampleDump().cp();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

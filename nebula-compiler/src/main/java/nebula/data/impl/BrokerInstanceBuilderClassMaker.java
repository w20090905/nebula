package nebula.data.impl;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class BrokerInstanceBuilderClassMaker implements Opcodes {

	private final static String interfaceName = BrokerInstanceBuilder.class.getName();

	public byte[] dump(String brokerName) {
		String name = interfaceName +"_" + brokerName.replace('.', '_');
		String brokerInnerName = brokerName.replace('.', '/');
		String nameInner = name.replace('.', '/');
		String interfaceInnerName = interfaceName.replace('.', '/');

		ClassWriter cw = new ClassWriter(0);
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, nameInner, null, "java/lang/Object", new String[] { interfaceInnerName });

		cw.visitSource(name, null);

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(5, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "L" + name.replace('.', '/') + ";", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "build", "()Ljava/lang/Object;", "<T:Ljava/lang/Object;>()TT;", null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(10, l0);
			mv.visitTypeInsn(NEW, brokerInnerName);
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, brokerInnerName, "<init>", "()V");
			mv.visitInsn(ARETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "L" + nameInner + ";", null, l0, l1, 0);
			mv.visitMaxs(2, 1);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}

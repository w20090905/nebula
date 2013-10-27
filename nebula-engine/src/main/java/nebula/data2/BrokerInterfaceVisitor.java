package nebula.data2;

import static org.objectweb.asm.Opcodes.ACC_BRIDGE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ACC_SYNTHETIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class BrokerInterfaceVisitor extends ClassVisitor {
	String name;
	String targetTypeName;
	final static String brokerTypeName = Broker.class.getName().replace('.', '/');

	public BrokerInterfaceVisitor(int api) {
		super(api);
	}

	public BrokerInterfaceVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.targetTypeName = name;
		this.name = name + "BrokerAuto";

		super.visit(version, ACC_PUBLIC + ACC_SUPER, this.name, "L" + brokerTypeName + "<L" + this.targetTypeName + ";>;L" + this.targetTypeName + ";", ""
				+ brokerTypeName + "", new String[] { name });

		MethodVisitor mv;
		{
			mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "" + brokerTypeName + "", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}

		{
			mv = super.visitMethod(ACC_PUBLIC, "get", "()L" + targetTypeName + ";", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
	}

	@Override
	public void visitSource(String source, String debug) {
		String newSourceName = this.name + ".java";
		super.visitSource(newSourceName, debug);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv;
		{
			mv = super.visitMethod(ACC_PUBLIC, name, desc, signature, exceptions);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, this.name, "actualValue", "Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, this.targetTypeName);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEINTERFACE, this.targetTypeName, name, desc);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		return mv;
	}

	@Override
	public void visitEnd() {
		MethodVisitor mv;
		{
			mv = super.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "get", "()Ljava/lang/Object;", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKEVIRTUAL, this.name, "get", "()L" + targetTypeName + ";");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		super.visitEnd();
	}
}

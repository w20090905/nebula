package test.asm.aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {
	MethodVisitor mv;
	public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
		super(Opcodes.ASM4, mv);
	}

	public void visitCode() {
		visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker", "checkSecurity", "()V");
	}
}

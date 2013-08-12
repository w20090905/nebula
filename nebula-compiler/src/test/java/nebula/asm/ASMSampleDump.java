package nebula.asm;

import nebula.lang.EntityExpression;
import nebula.lang.TestDump;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ASMSampleDump extends ClassLoader implements Opcodes {

	public static byte[] dump() throws Exception {
		String name = "nebula/lang/ASMSample";
		String actualClass = "java/lang/Integer";

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		MethodVisitor mv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name,"Ljava/lang/Object;Lnebula/lang/EntityExpression<L" + actualClass+ ";>;", "java/lang/Object",new String[] { "nebula/lang/EntityExpression" });



		// generic method
		{
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(Lnebula/data/Entity;)L" + actualClass + ";", null, null);
			mv.visitLdcInsn(new Integer(1014));
			mv.visitLdcInsn(new Integer(1024));
			mv.visitInsn(IMUL);
			
			
			MethodVisitor mv1  = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv1.visitVarInsn(ALOAD, 0);
			mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv1.visitInsn(RETURN);
			mv1.visitMaxs(0, 0);
			mv1.visitEnd();
			
			mv.visitMethodInsn(INVOKESTATIC,actualClass, "valueOf", "(I)L" + actualClass + ";");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "eval","(Lnebula/data/Entity;)Ljava/lang/Object;", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL,name, "eval", "(Lnebula/data/Entity;)L" + actualClass + ";");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	public static void main(String[] args) {
		new TestDump().compile();
	}

	public void compile() {
		try {
			byte[] b = ASMSampleDump.dump();

			Class<?> expClass = this.defineClass("nebula.lang.ASMSample", b, 0, b.length);
			// instantiates this compiled expression class...
			EntityExpression expr = (EntityExpression) expClass.newInstance();

			System.out.println(expr.eval(null));
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

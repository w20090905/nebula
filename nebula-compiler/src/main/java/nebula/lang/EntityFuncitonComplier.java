package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EntityFuncitonComplier implements Opcodes {
	Log log = LogFactory.getLog(getClass());

	/*
	 * Returns the byte code of an Expression class corresponding to this
	 * expression.
	 */
	<T> byte[] doCompile(final String name, final Code code, Context context) {

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, "Ljava/lang/Object;Lcom/google/common/base/Function<Lnebula/data/Entity;Ljava/lang/Boolean;>;",
				"java/lang/Object", new String[] { "com/google/common/base/Function" });

		// Init method
		{
			// default public constructor
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		// method
		{
			mv = cw.visitMethod(ACC_PUBLIC, "apply", "(Lnebula/data/Entity;)Ljava/lang/Boolean;", null, null);
			code.compile(cw, mv, context);
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
			mv.visitMethodInsn(INVOKEVIRTUAL, name, "apply", "(Lnebula/data/Entity;)Ljava/lang/Boolean;");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public String compile(Code code, Context context) {
		String name = "EntityFunciton" + String.valueOf(count++);
		try {
			byte[] b = this.doCompile(name, code, context);
			if (log.isDebugEnabled()) {
				try {
					FileOutputStream fos = new FileOutputStream("tmp\\" + name + ".class");
					fos.write(b);
					fos.close();
				} catch (FileNotFoundException e) {
					log.error(e);
					throw new RuntimeException(e);
				} catch (IOException e) {
					log.error(e);
					throw new RuntimeException(e);
				}
			}
			NebulaClassLoader.defineClass(name, b);
			return name;
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		}
	}

}

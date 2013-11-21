package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EntityClauseComplier implements Opcodes {
	Log log = LogFactory.getLog(getClass());

	static EntityClauseComplier DEFAULT = new EntityClauseComplier();

	private EntityClauseComplier() {

	}

	/*
	 * Returns the byte code of an Expression class corresponding to this
	 * expression.
	 */
	<T> byte[] doCompile(final String name, final Code code) {

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, "Ljava/lang/Object;Lnebula/lang/Clause<Lnebula/data/Entity;>;", "java/lang/Object",
				new String[] { "nebula/lang/Clause" });

		cw.visitSource(name + ".java", null);

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
			mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "apply",
					"(Lnebula/lang/RuntimeContext;Lnebula/data/DataRepos;Lnebula/data/Entity;[Ljava/lang/Object;)Z", null, null);
			mv.visitCode();
			code.compile(new AsmCompiler(cw, mv));
			mv.visitInsn(IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_VARARGS + ACC_SYNTHETIC, "apply",
					"(Lnebula/lang/RuntimeContext;Lnebula/data/DataRepos;Ljava/lang/Object;[Ljava/lang/Object;)Z", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
			mv.visitVarInsn(ALOAD, 4);
			mv.visitMethodInsn(INVOKEVIRTUAL, name, "apply", "(Lnebula/lang/RuntimeContext;Lnebula/data/DataRepos;Lnebula/data/Entity;[Ljava/lang/Object;)Z");
			mv.visitInsn(IRETURN);
			mv.visitMaxs(5, 5);
			mv.visitEnd();

		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public String compile(Type type, Code code) {
		String name = this.getClass().getSimpleName() + "_" + type.getName() + "_" + "_" + String.valueOf(count++);
		try {
			byte[] b = this.doCompile(name, code);
			if (log.isDebugEnabled()) {
				try {
					FileOutputStream fos = new FileOutputStream("tmp/" + name + ".class");
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

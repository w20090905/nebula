package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import util.NamesEncoding;

public class EntityActionComplier implements Opcodes {
	Log log = LogFactory.getLog(getClass());
	private EntityAction noop;

	static EntityActionComplier DEFAULT = new EntityActionComplier();

	private EntityActionComplier() {
		String name = this.getClass().getSimpleName() + "_nop_" + String.valueOf(count++);
		try {
			byte[] code = doCompile(name, new Compiler.Block(new ArrayList<Statement>()), null);
			Class<?> expClass = NebulaClassLoader.defineClass(name, code);
			// instantiates this compiled expression class...
			this.noop = (EntityAction) expClass.newInstance();
		} catch (InstantiationException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	/*
	 * Returns the byte code of an Expression class corresponding to this
	 * expression.
	 */
	<T> byte[] doCompile(final String name, final Code code, CompilerContext context) {

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", new String[] { "nebula/lang/EntityAction" });

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
			mv = cw.visitMethod(ACC_PUBLIC, "exec", "(Lnebula/lang/RuntimeContext;Lnebula/data/DataRepos;Lnebula/data/Entity;)V", null, null);
			code.compile(new AsmCompiler(cw, mv));
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public EntityAction compile(CompilerContext context, Type type, String actionName, Code code) {
		if (code instanceof Compiler.Block && ((Compiler.Block) code).statements.size() == 0) {
			return this.noop;
		}

		String name = this.getClass().getSimpleName() + "_" + type.getName() + "_" + NamesEncoding.encode(actionName) + "_" + String.valueOf(count++);
		try {
			byte[] b = this.doCompile(name, code, context);
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
			Class<?> expClass = NebulaClassLoader.defineClass(name, b);
			// instantiates this compiled expression class...
			EntityAction expr = (EntityAction) expClass.newInstance();
			return expr;
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}

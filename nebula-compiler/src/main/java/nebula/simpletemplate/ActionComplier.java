package nebula.simpletemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import nebula.lang.NebulaClassLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.asm.Type;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import util.NamesEncoding;

public class ActionComplier implements Opcodes {
	private static final String SUPER_NAME = Action.class.getName().replace('.', '/');
	Log log = LogFactory.getLog(getClass());
	private Action noop;

	static ActionComplier DEFAULT = new ActionComplier();

	private ActionComplier() {
		String name = this.getClass().getSimpleName() + "_nop_" + String.valueOf(count++);
		try {
			byte[] code = doCompile(name, new Compiler.Block(new ArrayList<Statement>()), null);
			Class<?> expClass = NebulaClassLoader.defineClass(name, code);
			// instantiates this compiled expression class...
			this.noop = (Action) expClass.newInstance();
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
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		MethodVisitor mv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", new String[] { SUPER_NAME });

		// String dataClassName =
		// Type.getType(context.getDataClass()).getInternalName();

		// /}}}

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

		{
			mv = cw.visitMethod(ACC_PUBLIC, "exec",
					"(" + Type.getDescriptor(STGroup.class) + "" + Type.getDescriptor(TemplateImpl.class) + "" + Type.getDescriptor(StringBuilder.class)
							+ "[Ljava/lang/Object;)V", null, new String[] { "java/io/IOException" });

			mv.visitCode();

			code.compile(cw, mv, context);
			// mv.visitVarInsn(ALOAD, 1);
			// mv.visitLdcInsn("Hello");
			// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write",
			// "(Ljava/lang/String;)V");
			// mv.visitVarInsn(ALOAD, 1);
			// mv.visitVarInsn(ALOAD, 3);
			// mv.visitMethodInsn(INVOKEVIRTUAL, dataClassName, "getName",
			// "()Ljava/lang/String;");
			// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write",
			// "(Ljava/lang/String;)V");
			// mv.visitVarInsn(ALOAD, 1);
			// mv.visitLdcInsn(";");
			// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/Writer", "write",
			// "(Ljava/lang/String;)V");

			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public Action compileAndGetInstance(CompilerContext context, String actionName, Code code) {
		if (code instanceof Compiler.Block && ((Compiler.Block) code).statements.size() == 0) {
			return this.noop;
		}
		try {
			Class<Action> action = compile(context, actionName, code);
			// instantiates this compiled expression class...
			Action expr = (Action) action.newInstance();
			return expr;
		} catch (InstantiationException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public Class<Action> compile(CompilerContext context, String actionName, Code code) {

		actionName = actionName.replace('.', '_');

		String name = this.getClass().getSimpleName() + "_" + NamesEncoding.encode(actionName, false) + "_" + String.valueOf(count++);
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
			@SuppressWarnings("unchecked")
			Class<Action> action = (Class<Action>) NebulaClassLoader.defineClass(name, b);
			NebulaClassLoader.doResolveClass(action);

			return action;
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		}
	}

}

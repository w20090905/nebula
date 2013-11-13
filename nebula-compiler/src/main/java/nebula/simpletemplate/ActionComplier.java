package nebula.simpletemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import nebula.lang.NebulaClassLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import util.NamesEncoding;

public class ActionComplier implements Opcodes {
	private static final String SUPER_NAME = Action.class.getName().replace('.', '/');
	Log log = LogFactory.getLog(getClass());
	private Action noop;

	static ActionComplier DEFAULT = new ActionComplier();

	private ActionComplier() {
	}

	/*
	 * Returns the byte code of an Expression class corresponding to this
	 * expression.
	 */
	<T> byte[] doCompile(final String clzInternalName, final CompiledST template, CompilerContext context) {

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		MethodVisitor mv;
		FieldVisitor fv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, clzInternalName, null, "java/lang/Object", new String[] { SUPER_NAME });

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

//		if (template.implicitlyDefinedTemplates != null) {
//			List<TemplateImpl> list = template.implicitlyDefinedTemplates;
//			for (int i = 0; i < list.size(); i++) {
//				{
//					String templateClzFieldName = "clz" + i;
//					fv = cw.visitField(ACC_PRIVATE, templateClzFieldName, "Ljava/lang/Class;", "Ljava/lang/Class<*>;", null);
//					fv.visitEnd();
//				}
//				{
//					String templateActionFieldName = "temp" + i;
//					fv = cw.visitField(ACC_PRIVATE, templateActionFieldName, Type.getDescriptor(Action.class), null, null);
//					fv.visitEnd();
//				}
//			}
//		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "exec",
					"(" + Type.getDescriptor(STGroup.class) + "" + Type.getDescriptor(CompiledST.class) + "" + Type.getDescriptor(StringBuilder.class)
							+ "[Ljava/lang/Object;)V", null, new String[] { "java/io/IOException" });

			mv.visitCode();

			template.code.compile(clzInternalName, cw, mv, context);

			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public Action compileAndGetInstance(CompilerContext context, String actionName, final CompiledST template) {
		try {
			if (template.code instanceof Compiler.Block && ((Compiler.Block) template.code).statements.size() == 0) {
				if (this.noop != null) {
					return this.noop;
				} else {
					Class<Action> action = compile(context, actionName, template);
					// instantiates this compiled expression class...
					this.noop = (Action) action.newInstance();
					return this.noop;
				}
			}

			Class<Action> action = compile(context, actionName, template);
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

	public Class<Action> compile(CompilerContext context, String actionName, final CompiledST template) {

		actionName = actionName.replace('.', '_');

		String name = this.getClass().getSimpleName() + "_" + NamesEncoding.encode(actionName, false) + "_" + String.valueOf(count++);
		try {
			byte[] b = this.doCompile(name, template, context);
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

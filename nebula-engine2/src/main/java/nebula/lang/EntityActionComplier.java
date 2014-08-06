package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nebula.lang.Compiler.Action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import util.NamesEncoding;

public class EntityActionComplier implements Opcodes {
	static Log log = LogFactory.getLog(EntityActionComplier.class);
	private static String EntityAction_InternalName = org.objectweb.asm.Type.getInternalName(EntityAction.class);
	private static String EntityAction_Descriptor = org.objectweb.asm.Type.getDescriptor(EntityAction.class);

	static EntityAction DONOTHING;

	static EntityActionComplier DEFAULT = new EntityActionComplier();

	private EntityActionComplier() {
		String name = this.getClass().getSimpleName() + "_nop_";
		try {
			if (DONOTHING == null) {
				byte[] code = doCompile(false, name, name, new Compiler.Block(new ArrayList<Statement>()), null);
				Class<?> expClass = NebulaClassLoader.defineClass(name, code);
				// instantiates this compiled expression class...
				EntityActionComplier.DONOTHING = (EntityAction) expClass.newInstance();
			}
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

	private <T> byte[] doCompile(boolean clzExist, String name, String actualName, final Code code, CompilerContext context) {
		String internalName = name.replace('.', '/');
		String actualInternalName = actualName.replace('.', '/');

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;
		FieldVisitor fv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, actualInternalName, null, "java/lang/Object", new String[] { EntityAction_InternalName });

		// Field
		{
			if (!clzExist) {
				fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "instance", EntityAction_Descriptor, null, null);
				fv.visitEnd();
			}
		}
		// Class Init method
		{
			mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
			mv.visitCode();
			mv.visitTypeInsn(NEW, actualInternalName);
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, actualInternalName, "<init>", "()V");
			mv.visitFieldInsn(PUTSTATIC, internalName, "instance", EntityAction_Descriptor);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

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
			code.compile(new MethodAsmCompiler(cw, mv));
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	static final Map<String, Integer> cached = new HashMap<String, Integer>();

	public EntityAction compile(CompilerContext context, Type type, String actionName, Action action) {
		if (action.st instanceof Compiler.Block && ((Compiler.Block) action.st).statements.size() == 0) {
			return DONOTHING;
		}
		String name = EntityAction.class.getSimpleName() + "_" + NamesEncoding.encode(type.getFullName(), false) + "_"
				+ NamesEncoding.encode(actionName, false);

		String actualName;
		Integer cnt = cached.get(name);
		boolean clzExist = false;
		if (cnt != null) {
			clzExist = true;
			cnt++;
			cached.put(name, cnt);
			actualName = name + "_" + cnt ;
		} else {
			cached.put(name, 1);
			actualName = name;
		}

		try {
			byte[] b = this.doCompile(clzExist, name, actualName, action, context);
			if (log.isDebugEnabled()) {
				FileOutputStream fos = new FileOutputStream("tmp/" + actualName+ ".class");
				fos.write(b);
				fos.close();
			}
			Class<?> expClass = NebulaClassLoader.defineClass(actualName, b);
			// instantiates this compiled expression class...
			EntityAction expr = (EntityAction) expClass.newInstance();
			return expr;
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}

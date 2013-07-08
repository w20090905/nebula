package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.base.Preconditions;

public class EntityExpressionComplier extends ClassLoader implements Opcodes {
	Log log = LogFactory.getLog(getClass());

	/*
	 * Returns the byte code of an Expression class corresponding to this
	 * expression.
	 */
	<T> byte[]  doCompile(final String name, final Expr<T> expr) {
		String actualClass = null;

		Class<?> cls = expr.getClass();
		do {

			if (cls.getGenericSuperclass() != null && cls.getGenericSuperclass() instanceof ParameterizedType) {
				ParameterizedType tp = (ParameterizedType) cls.getGenericSuperclass();
				if (tp.getActualTypeArguments()[0] instanceof Class) {
					Class<?> clz1 = (Class<?>) tp.getActualTypeArguments()[0];
					actualClass = clz1.getName().replace('.', '/');
					break;
				}
			}
			if (cls.getGenericInterfaces().length > 0) {
				for (java.lang.reflect.Type tp : cls.getGenericInterfaces()) {
					ParameterizedType ttp = (ParameterizedType) tp;
					Class<?> clz1 = (Class<?>) ttp.getActualTypeArguments()[0];
					actualClass = clz1.getName().replace('.', '/');
					break;
				}
			}

			cls = cls.getSuperclass();
		} while (!Object.class.equals(cls));

		Preconditions.checkNotNull(actualClass);

		// class header
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodVisitor mv;

		// Class define
		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, "Ljava/lang/Object;Lnebula/lang/EntityExpression<L" + actualClass
				+ ";>;", "java/lang/Object", new String[] { "nebula/lang/EntityExpression" });

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
/**
		// generic method
		{
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(Lnebula/data/Entity;)L" + actualClass + ";", null, null);
			expr.compile(mv);
			if ("java/lang/Integer".equals(actualClass)) {
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
			} else if ("java/lang/Boolean".equals(actualClass)) {
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
			}

			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "eval",
					"(Lnebula/data/Entity;)Ljava/lang/Object;", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL, name, "eval", "(Lnebula/data/Entity;)L" + actualClass + ";");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();
		*/
		

		// method
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "eval",
					"(Lnebula/data/Entity;)Ljava/lang/Object;", null, null);
			
			expr.compile(mv);
			
			switch (expr.getExprType().rawType) {
			case Boolean:
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");	
				break;
			case Long:
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");			
				break;				
			default:
				break;
			}
			
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	static long count = 0;

	public <T> EntityExpression compile(Expr<T> exp, Type type) {
		String name = "EntityExpression" + String.valueOf(count++);
		try {
			byte[] b = this.doCompile(name, exp);
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
			Class<?> expClass = this.defineClass(name, b, 0, b.length);
			// instantiates this compiled expression class...
			EntityExpression expr = (EntityExpression) expClass.newInstance();
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

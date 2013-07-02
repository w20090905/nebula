package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EntityExpressionComplier  extends ClassLoader implements Opcodes {
	Log log = LogFactory.getLog(getClass());
		/*
		 * Returns the byte code of an Expression class corresponding to this
		 * expression.
		 */
		<T> byte[] doCompile(final String name, final Expr<T> expr) {
			// class header
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			cw.visit(V1_1, ACC_PUBLIC, name, null, "java/lang/Object", new String[] { EntityExpression.class.getName()
					.replace('.', '/') });

			// default public constructor
			MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();

			// eval method
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(Lnebula/data/Entity;)I", null, null);
			expr.compile(mv);
			mv.visitInsn(IRETURN);
			// max stack and max locals automatically computed
			mv.visitMaxs(0, 0);
			mv.visitEnd();

			return cw.toByteArray();
		}
		
		static long count = 0;
		
		public  <T> EntityExpression compile(Expr<T> exp,Type type){
			String name = "EntityExpression" + String.valueOf(count++);			
			try {
				byte[] b = this.doCompile(name, exp);
				if(log.isDebugEnabled()){
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

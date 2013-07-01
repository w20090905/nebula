package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NebulaAdvParserExprEntityTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	class Complier extends ClassLoader implements Opcodes {
		/*
		 * Returns the byte code of an Expression class corresponding to this
		 * expression.
		 */
		byte[] compile(final String name, final Expr expr) {
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

		protected int compute(Expr exp, Entity entity) {
			try {
				byte[] b = this.compile("Example", exp);
				FileOutputStream fos = new FileOutputStream("tmp\\Example.class");
				fos.write(b);
				fos.close();
				Class<?> expClass = this.defineClass("Example", b, 0, b.length);
				// instantiates this compiled expression class...
				EntityExpression iexp = (EntityExpression) expClass.newInstance();
				return iexp.eval(entity);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (ClassFormatError e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private int compute(Expr expr,Entity entity) {
		Complier complier = new Complier();
		return complier.compute(expr,entity);
	}

	private void eqValue(Entity entity,String exprText, boolean result) {
		try {
			if (result) {
				assertEquals(1, compute(parse(exprText),entity));
			} else {
				assertEquals(0, compute(parse(exprText),entity));
			}
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private void eqValue(Entity entity,String exprText, int result) {
		try {
			assertEquals(result, compute(parse(exprText),entity));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private void eqExpr(String exprText, String expectedResult) {
		try {
			Expr expr = parse(exprText);
			assertEquals(expectedResult, expr.toString());

		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private Expr parse(String exprText) throws RecognitionException {
		NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(exprText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NebulaParser parser = new NebulaParser(tokens, compiler);

		return parser.expr();
	}

	public void testTypeDefinition() {
		eqExpr("age", "age");
		//
	}

	public void testCompute() {
		Entity data = new EditableEntity();
		data.put("age", 10);
		int age = (Integer)data.get("age");

		eqValue(data,"age", 10);
		eqValue(data,"age + 10",age + 10);
		eqValue(data,"age - 10",age - 10);
		eqValue(data,"age * 10",age * 10);
		eqValue(data,"age / 10",age / 10);
		eqValue(data,"age % 10",age % 10);
		eqValue(data,"age == 10",age == 10);
	}
}

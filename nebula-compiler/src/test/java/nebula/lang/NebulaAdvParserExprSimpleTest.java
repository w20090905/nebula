package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NebulaAdvParserExprSimpleTest extends TestCase {
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
			cw.visit(V1_1, ACC_PUBLIC, name, null, "java/lang/Object", new String[] { ExpressionForSimpleTest.class.getName()
					.replace('.', '/') });

			// default public constructor
			MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();

			// eval method
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(II)I", null, null);
			expr.compile(mv);
			mv.visitInsn(IRETURN);
			// max stack and max locals automatically computed
			mv.visitMaxs(0, 0);
			mv.visitEnd();

			return cw.toByteArray();
		}

		protected int compute(Expr exp) {
			try {
				byte[] b = this.compile("Example", exp);
				FileOutputStream fos = new FileOutputStream("tmp\\Example.class");
				fos.write(b);
				fos.close();
				Class<?> expClass = this.defineClass("Example", b, 0, b.length);
				// instantiates this compiled expression class...
				ExpressionForSimpleTest iexp = (ExpressionForSimpleTest) expClass.newInstance();
				return iexp.eval(0, 0);
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

	private int compute(Expr expr) {
		Complier complier = new Complier();
		return complier.compute(expr);
	}

	private void eqValue(String exprText, boolean result) {
		try {
			if (result) {
				assertEquals(1, compute(parse(exprText)));
			} else {
				assertEquals(0, compute(parse(exprText)));
			}
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private void eqValue(String exprText, int result) {
		try {
			assertEquals(result, compute(parse(exprText)));
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
		eqExpr("a+b", "(a + b)");
		eqExpr("a-b", "(a - b)");
		eqExpr("a*b", "(a * b)");
		eqExpr("a/b", "(a / b)");
		eqExpr("a%b", "(a % b)");

		eqExpr("a +1 * b /10-1", "((a + ((1 * b) / 10)) - 1)");

		eqExpr("a>b", "(a > b)");
		eqExpr("a==b", "(a == b)");
		eqExpr("a<b", "(a < b)");
		eqExpr("a>=b", "(a >= b)");
		eqExpr("a<=b", "(a <= b)");
		eqExpr("a!=b", "(a != b)");

		eqExpr("a&&b", "(a && b)");
		eqExpr("a and b", "(a && b)");
		eqExpr("a||b", "(a || b)");
		eqExpr("a or b", "(a || b)");
		eqExpr("!a", "(!a)");
		eqExpr("not a", "(!a)");
		eqExpr("not(a)", "(!a)");

		eqExpr("not(a==b)", "(!(a == b))");

		eqExpr(" a >=10 && n==1 or a!= !b ", "(((a >= 10) && (n == 1)) || (a != (!b)))");
		//
	}

	public void testCompute() {
		eqValue("3+5", 3 + 5);
		eqValue("3-5", 3 - 5);
		eqValue("3*5", 3 * 5);
		eqValue("3/5", 3 / 5);
		eqValue("3%5", (3 % 5));

		eqValue("3 +1 * 5 /10-1", ((3 + ((1 * 5) / 10)) - 1));

		eqValue("3>5", (3 > 5));
		eqValue("5>3", (5 > 3));
		eqValue("3==5", (3 == 5));
		eqValue("3<5", (3 < 5));
		eqValue("3>=5", (3 >= 5));
		eqValue("3<=5", (3 <= 5));
		eqValue("3!=5", (3 != 5));

		eqValue("3>0 && 5<0", 3 > 0 && 5 < 0);
		eqValue("3>0 and 5<0", 3 > 0 && 5 < 0);
		eqValue("3==1 || 5==1", 3 == 1 || 5 == 1);
		eqValue("3==1 or 5==1", 3 == 1 || 5 == 1);
		eqValue("!(3==1)", !(3 == 1));
		// eq("not 3",(!3));
		eqValue("not(3==1)", !(3 == 1));

		eqValue("not(3==5)", (!(3 == 5)));

		eqValue(" 10 >=10 && 6==1 or 3!= 5 ", (((10 >= 10) && (6 == 1)) || (3 != 5)));
	}
}

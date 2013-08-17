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

public class NebulaParser_Expr_SimpleTest extends TestCase {
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
		byte[] compile(final String name, final Expr<?> expr, CompilerContext context) {
			// class header
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			cw.visit(V1_1, ACC_PUBLIC, name, null, "java/lang/Object", new String[] { ExpressionForSimpleTest.class.getName().replace('.', '/') });

			// default public constructor
			MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();

			// eval method
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(JJ)J", null, null);
			expr.compile(cw, mv, context);
			mv.visitInsn(LRETURN);
			// max stack and max locals automatically computed
			mv.visitMaxs(0, 0);
			mv.visitEnd();

			return cw.toByteArray();
		}

		/*
		 * Returns the byte code of an Expression class corresponding to this
		 * expression.
		 */
		byte[] compileBoolean(final String name, final Expr<?> expr, CompilerContext context) {
			// class header
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			cw.visit(V1_1, ACC_PUBLIC, name, null, "java/lang/Object", new String[] { ExpressionForSimpleTestBoolean.class.getName().replace('.', '/') });

			// default public constructor
			MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();

			// eval method
			mv = cw.visitMethod(ACC_PUBLIC, "eval", "(JJ)I", null, null);
			expr.compile(cw, mv, context);
			mv.visitInsn(IRETURN);
			// max stack and max locals automatically computed
			mv.visitMaxs(0, 0);
			mv.visitEnd();

			return cw.toByteArray();
		}

		protected Long compute(Expr<?> exp, CompilerContext context) {
			try {
				byte[] b = this.compile("Example", exp, context);
				FileOutputStream fos = new FileOutputStream("tmp\\Example.class");
				fos.write(b);
				fos.close();
				Class<?> expClass = this.defineClass("Example", b, 0, b.length);
				// instantiates this compiled expression class...
				ExpressionForSimpleTest iexp = (ExpressionForSimpleTest) expClass.newInstance();
				return iexp.eval(0L, 0L);
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

		protected Integer computeBoolean(Expr<?> exp, CompilerContext context) {
			try {
				byte[] b = this.compileBoolean("Example", exp, context);
				FileOutputStream fos = new FileOutputStream("tmp\\Example.class");
				fos.write(b);
				fos.close();
				Class<?> expClass = this.defineClass("Example", b, 0, b.length);
				// instantiates this compiled expression class...
				ExpressionForSimpleTestBoolean iexp = (ExpressionForSimpleTestBoolean) expClass.newInstance();
				return iexp.eval(0L, 0L);
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

	private long compute(Expr<?> expr) {
		Complier complier = new Complier();
		return complier.compute(expr, new CompilerContext() {

			@Override
			public Type resolveType(String name) {
				return compiler.findType(name);
			}
		});
	}

	private int computeBoolean(Expr<?> expr) {
		Complier complier = new Complier();
		return complier.computeBoolean(expr, new CompilerContext() {

			@Override
			public Type resolveType(String name) {
				return compiler.findType(name);
			}
		});
	}

	private void eqValue(String exprText, boolean result) {
		try {
			if (result) {
				assertEquals(1, computeBoolean(parse(exprText)));
			} else {
				assertEquals(0, computeBoolean(parse(exprText)));
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
			Expr<?> expr = parse(exprText);
			assertEquals(expectedResult, expr.toString());

		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private Expr<?> parse(String exprText) throws RecognitionException {
		NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(exprText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NebulaParser parser = new NebulaParser(tokens, compiler);
		parser.pushLocal("a", (Type) null);
		parser.pushLocal("b", (Type) null);
		parser.pushLocal("n", (Type) null);
		return parser.expression();
	}

	public void testTypeDefinition() {
		eqExpr("a+b", "(a + b)");
		eqExpr("a-b", "(a - b)");
		eqExpr("a*b", "(a * b)");
		eqExpr("a/b", "(a / b)");
		eqExpr("a%b", "(a % b)");
		eqExpr("++a", "(++a)");
		eqExpr("--a", "(--a)");
//		eqExpr("+a", "(+a)");
//		eqExpr("-a", "(-a)");

		eqExpr("a +1 * b /10-1", "((a + ((1 * b) / 10)) - 1)");

		eqExpr("a>b", "(a > b)");
		eqExpr("a==b", "(a == b)");
		eqExpr("a<b", "(a < b)");
		eqExpr("a>=b", "(a >= b)");
		eqExpr("a<=b", "(a <= b)");
		eqExpr("a!=b", "(a != b)");

		eqExpr("a&&b", "(a && b)");
		eqExpr("a<b&&b>=a", "((a < b) && (b >= a))");
		eqExpr("a and b", "(a && b)");
		eqExpr("a||b", "(a || b)");
		eqExpr("a or b", "(a || b)");
		eqExpr("!a", "(!a)");
		eqExpr("not a", "(!a)");
		eqExpr("not(a)", "(!a)");

		eqExpr("not(a==b)", "(!(a == b))");

		eqExpr(" a >=10 && n==1 or a!= b ", "(((a >= 10) && (n == 1)) || (a != b))");

		try {
			parse("$Person[12]");
			parse("$Person[12,34234,33..33,22..]");
			parse("$Person[a==10 && b==2]");
			parse("$Person[a==10 && b==2]");
		} catch (RecognitionException e) {
			throw new RuntimeException(e);
		}

		// eqExpr("$Person[a==10 && b==2]","");
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

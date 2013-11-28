package nebula.lang;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Expr_JsTest extends TestCase {
	TypeLoaderForTest typeLoader;

	@Override
	protected void setUp() throws Exception {
		typeLoader = new TypeLoaderForTest(new SystemTypeLoader());
	}

	private void eqExpr(String exprText, String expectedResult) {
		try {
			Expr<?> expr = parse(exprText);

			JsCompiler compiler = new JsCompiler();
			// expr.scan(new CompilerContext() {
			//
			// @Override
			// public Type resolveType(String name) {
			// return typeLoader.findType(name);
			// }
			// });
			expr.compile(compiler);

			assertEquals(expectedResult, compiler.toString());

		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private Expr<?> parse(String exprText) throws RecognitionException {
		NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(exprText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NebulaParser parser = new NebulaParser(tokens, typeLoader);
		parser.enterMethod(null);
		parser.pushLocal("a", (Type) null);
		parser.pushLocal("b", (Type) null);
		parser.pushLocal("n", (Type) null);
		return parser.expression();
	}

	public void testTypeDefinition() {
		eqExpr("a+b", "(a+b)");
		eqExpr("a-b", "(a-b)");
		eqExpr("a*b", "(a*b)");
		eqExpr("a/b", "(a/b)");
		eqExpr("a%b", "(a%b)");
		eqExpr("++a", "(++a)");
		eqExpr("--a", "(--a)");
		 eqExpr("+a","(+a)");
		 eqExpr("-a","(-a)");

		eqExpr("a+1*b/10-1", "((a+((1*b)/10))-1)");

		eqExpr("a>b", "(a>b)");
		eqExpr("a==b", "(a==b)");
		eqExpr("a<b", "(a<b)");
		eqExpr("a>=b", "(a>=b)");
		eqExpr("a<=b", "(a<=b)");
		eqExpr("a!=b", "(a!=b)");

		eqExpr("a&&b", "(a&&b)");
		eqExpr("a<b&&b>=a", "((a<b)&&(b>=a))");
		eqExpr("a and b", "(a&&b)");
		eqExpr("a||b", "(a||b)");
		eqExpr("a or b", "(a||b)");
		eqExpr("!a", "(!a)");
		eqExpr("not a", "(!a)");
		eqExpr("not(a)", "(!a)");

		eqExpr("not(a==b)", "(!(a==b))");

		eqExpr("a>=10&&n==1 or a!=b", "(((a>=10)&&(n==1))||(a!=b))");

		try {
			parse("$Person[12]");
			parse("$Person[12,34234,33..33,22..]");
			parse("$Person[a==10&&b==2]");
			parse("$Person[a==10&&b==2]");
		} catch (RecognitionException e) {
			throw new RuntimeException(e);
		}

		// eqExpr("$Person[a==10 && b==2]","");
		//
	}
}

package nebula.lang;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Expr_EntityTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}
//
//	private <T> T compute(Expr<T> expr, Entity entity) {
//		EntityExpressionComplier complier = new EntityExpressionComplier();
//		
//		
//		
//		return (T) complier.compile(expr, null).eval(entity);
//	}
//
//	private void eqValue(Entity entity, String exprText, boolean result) {
//		try {
//			assertEquals(result, compute(parse(exprText), entity));
//		} catch (RecognitionException e) {
//			fail(e.toString());
//		}
//	}
//
//	private void eqValue(Entity entity, String exprText, int result) {
//		try {
//			assertEquals(result, compute(parse(exprText), entity));
//		} catch (RecognitionException e) {
//			fail(e.toString());
//		}
//	}

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

		return parser.expr();
	}

	public void testTypeDefinition() {
		eqExpr("age", "age");
		//
	}

//	public void testCompute() {
//		Entity data = new EditableEntity();
//		data.put("age", 10);
//		int age = (Integer) data.get("age");
//
//		eqValue(data, "this.age", 10);
//		eqValue(data, "age + 10", age + 10);
//		eqValue(data, "age - 10", age - 10);
//		eqValue(data, "age * 10", age * 10);
//		eqValue(data, "age / 10", age / 10);
//		eqValue(data, "age % 10", age % 10);
//		eqValue(data, "age == 10", age == 10);
//		eqValue(data, "age >= 10", age >= 10);
//	}
}

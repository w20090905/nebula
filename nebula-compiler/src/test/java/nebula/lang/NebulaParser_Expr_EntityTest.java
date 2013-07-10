package nebula.lang;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Expr_EntityTest extends TestCase {
	TypeLoaderForTest compiler;
	Entity data = new EditableEntity();
	Type type;
	int Age = 10;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	@SuppressWarnings("unchecked")
	private <T> T compute(Expr<T> expr, Entity entity) {
		EntityExpressionComplier complier = new EntityExpressionComplier();
		return (T) complier.compile(expr, null).eval(entity);
	}

	private void eqValue(String exprText, Object result) {
		try {
			assertEquals(result, compute(parse(exprText), data));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private void eqExpr(String exprText, Object expectedResult) {
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
		type = new Type(compiler, "Test");
		Field field;
		field = new Field(type, "Name");
		field.type = parser.resolveType("Name");
		type.fields.add(field);

		field = new Field(type, "Age");
		field.type = parser.resolveType("Age");
		type.fields.add(field);

		field = new Field(type, "Height");
		field.type = parser.resolveType("Age");
		type.fields.add(field);

		parser.typesLoading.put(type.name, type);

		parser.currentType = type;

		return parser.expression();
	}

	public void testTypeDefinition() {
		eqExpr("age", "age");
		//
	}

	public void testCompute() {
		data.put("Age", Age);

		eqValue("this.Age", this.Age);
		eqValue("this.Age + 10", this.Age + 10);
		eqValue("this.Age - 10", this.Age - 10);
		eqValue("this.Age * 10", this.Age * 10);
		eqValue("this.Age / 10", this.Age / 10);
		eqValue("this.Age % 10", this.Age % 10);
		eqValue("this.Age == 10", this.Age == 10);
		eqValue("this.Age >= 10", this.Age >= 10);
	}
}

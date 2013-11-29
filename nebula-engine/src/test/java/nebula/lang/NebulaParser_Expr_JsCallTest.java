package nebula.lang;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Expr_JsCallTest extends TestCase {
	RuntimeContext context = new RuntimeContext() {
	};

	TypeLoaderForTest compiler;
	Entity data = new EditableEntity();
	TypeImp type;
	long Age = 10;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest();
	}

	private String compute(Code expr, Entity entity) {
		JsCallCompiler complier = new JsCallCompiler("this", "$scope.$parent.data");
		expr.scan(new CompilerContext() {

			@Override
			public Type resolveType(String name) {
				return compiler.findType(name);
			}
		});
		return complier.compile( "data.Age", expr);
	}

	private void eqValue(Object result, String exprText) {
		try {
			assertEquals(result, compute(parse(exprText), data));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	private Statement parse(String exprText) throws RecognitionException {
		NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(exprText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NebulaParser parser = new NebulaParser(tokens, compiler);
		type = new TypeImp(compiler, "Test");
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

		parser.enterMethod(type);
		return parser.statement();
	}

	public void testCompute() {
		data.put("Age", Age);
		

		// @formatter:off
		eqValue("" +
				"$scope.$watch('data.Age', function(newValue, oldValue) {\n" +
					"if(newValue){" +
						"$scope.$ajaxCall({" +
							"'$action' : 'data.Age'," +
							"'data.Age':$scope.data.Age}," +
							"function($scope,result){" +
								"$scope.data.AgeDerived=result.AgeDerived;" +
							"});" +
					"}" +
				"\n});",
				"this.AgeDerived = this.Age;");
		// @formatter:on
	}
	// public void testCompute2() {
	// data.put("Age", Age);
	//
	// eqValue(this.Age, "this.Age");
	// eqValue(this.Age + 10, "this.Age + 10");
	// eqValue(this.Age - 10, "this.Age - 10");
	// eqValue(this.Age * 10, "this.Age * 10");
	// eqValue(this.Age / 10, "this.Age / 10");
	// eqValue(this.Age % 10, "this.Age % 10");
	//
	// this.Age = 10L;
	// data.put("Age", Age);
	// eqValue(this.Age == 10L, "this.Age == 10");
	//
	// this.Age = 9L;
	// data.put("Age", Age);
	// eqValue(this.Age == 10L, "this.Age == 10");
	//
	// this.Age = 9L;
	// data.put("Age", Age);
	// eqValue(this.Age >= 10, "this.Age >= 10");
	//
	// this.Age = 10L;
	// data.put("Age", Age);
	// eqValue(this.Age >= 10, "this.Age >= 10");
	//
	// this.Age = 11L;
	// data.put("Age", Age);
	// eqValue(this.Age >= 10, "this.Age >= 10");
	//
	// this.Age = 10;
	// data.put("Age", Age);
	// eqValue(this.Age > 10 && this.Age < 40,
	// "this.Age > 10 && this.Age < 40");
	//
	// this.Age = 20;
	// data.put("Age", Age);
	// eqValue(this.Age > 10 && this.Age < 40,
	// "this.Age > 10 && this.Age < 40");
	//
	// this.Age = 30;
	// data.put("Age", Age);
	// eqValue(this.Age > 10 && this.Age < 40,
	// "this.Age > 10 && this.Age < 40");
	//
	// this.Age = 40;
	// data.put("Age", Age);
	// eqValue(this.Age > 10, "this.Age > 10 ");
	// eqValue(this.Age < 40, " this.Age < 40");
	// eqValue(this.Age > 10 && this.Age < 40,
	// "this.Age > 10 && this.Age < 40");
	//
	// eqValue(this.Age > 20 && this.Age <= 40,
	// "this.Age > 20 && this.Age <= 40");
	// eqValue(this.Age > 40 && this.Age == 30,
	// "this.Age > 40 && this.Age == 30");
	// eqValue(this.Age > 40 || this.Age == 30,
	// "this.Age > 40 || this.Age == 30");
	// }
}

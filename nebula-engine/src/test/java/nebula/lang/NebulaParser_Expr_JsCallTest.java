package nebula.lang;

import java.util.List;

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

	public void test_expr_ComputeBackend__this_Name() throws Exception {
		//@formatter:off
		String text = "" +
				"@MaxLength(120)\n" +
				"type Person { \n" +
				" Name;" +
				" @ComputeBackend  TestName Name = this.Name;" +
				"};";
		//@formatter:on

		TypeImp type = (TypeImp) compiler.load(text);

		assertEquals("Person", type.name);
		assertEquals("Master", type.superType.getName());
		assertEquals(TypeStandalone.Master, type.standalone);

		int index=0;		
		assertEquals("Name", type.fields.get(index).name);
		index++;
		assertEquals("TestName", type.fields.get(index).name);
		index++;
		
		assertEquals(index, type.fields.size());
		
		List<Field> fields = type.getFields();
		StringBuilder sb = new StringBuilder();
		String ctrlName = type.getName() + "_OnChangeCtrl";
		sb.append("<script>function " + ctrlName + "($scope){var doCall = function( $scope ){");
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getOnChangeCode() != null) {
				sb.append(JsCallCompiler.compiler("data." + field.getName(), field.getOnChangeCode(), "this", "data"));
			}
		}
		sb.append("};doCall( $scope.$parent );}</script>");
		type.getAttrs().put("AjaxExpressionName", ctrlName);
		type.getAttrs().put("AjaxExpression", sb.toString());
		
		
		assertEquals("<script>function Person_OnChangeCtrl($scope){var doCall = function( $scope ){$scope.$watch('data.Name', function(newValue, oldValue) {\nif(newValue){$scope.$ajaxCall({'$getaction' : 'data.Name','data.Name':$scope.data.Name},function($scope,result){$scope.data.TestName=result.TestName;});}\n});};doCall( $scope.$parent );}</script>", type.getAttrs().get("AjaxExpression").toString());
	}

	public void test_expr_ComputeBackend__repos_Age_Age2() throws Exception {
		//@formatter:off
		String text = "" +
				"@MaxLength(120)\n" +
				"type Person { \n" +
				" Name;" +
				" Age;" +
				" Age2 Age;" +
				"  AgeDerived Age := this.Age + $Person[0].Age;" +
				"};";
		//@formatter:on

		TypeImp type = (TypeImp) compiler.load(text);

		assertEquals("Person", type.name);
		assertEquals("Master", type.superType.getName());
		assertEquals(TypeStandalone.Master, type.standalone);

		int index=0;		
		assertEquals("Name", type.fields.get(index).name);
		index++;
		assertEquals("Age", type.fields.get(index).name);
		index++;
		assertEquals("Age2", type.fields.get(index).name);
		index++;
		assertEquals("AgeDerived", type.fields.get(index).name);
		index++;
		
		assertEquals(index, type.fields.size());
		
		List<Field> fields = type.getFields();
		StringBuilder sb = new StringBuilder();
		String ctrlName = type.getName() + "_OnChangeCtrl";
		sb.append("<script>function " + ctrlName + "($scope){var doCall = function( $scope ){");
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getOnChangeCode() != null) {
				sb.append(JsCallCompiler.compiler("data." + field.getName(), field.getOnChangeCode(), "this", "data"));
			}
		}
		sb.append("};doCall( $scope.$parent );}</script>");
		type.getAttrs().put("AjaxExpressionName", ctrlName);
		type.getAttrs().put("AjaxExpression", sb.toString());
				
		assertEquals("<script>function Person_OnChangeCtrl($scope){var doCall = function( $scope ){$scope.$watch('data.Age', function(newValue, oldValue) {\n" +
				"if(newValue){$scope.$ajaxCall({'$getaction' : 'data.Age','data.Age':$scope.data.Age},function($scope,result){$scope.data.AgeDerived=result.AgeDerived;});}\n" +
				"});};doCall( $scope.$parent );}</script>", type.getAttrs().get("AjaxExpression").toString());
	}
	

	public void test_expr_ComputeBackend__repos_Name() throws Exception {
		//@formatter:off
		String text = "" +
				"@MaxLength(120)\n" +
				"type Person { \n" +
				" Name;" +
				" @ComputeBackend  TestName Name := $Person[0].Name;" +
				"};";
		//@formatter:on

		TypeImp type = (TypeImp) compiler.load(text);

		assertEquals("Person", type.name);
		assertEquals("Master", type.superType.getName());
		assertEquals(TypeStandalone.Master, type.standalone);

		int index=0;		
		assertEquals("Name", type.fields.get(index).name);
		index++;
		assertEquals("TestName", type.fields.get(index).name);
		index++;
		
		assertEquals(index, type.fields.size());
		
		List<Field> fields = type.getFields();
		StringBuilder sb = new StringBuilder();
		String ctrlName = type.getName() + "_OnChangeCtrl";
		sb.append("<script>function " + ctrlName + "($scope){var doCall = function( $scope ){");
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getOnChangeCode() != null) {
				sb.append(JsCallCompiler.compiler("data." + field.getName(), field.getOnChangeCode(), "this", "data"));
			}
		}
		sb.append("};doCall( $scope.$parent );}</script>");
		type.getAttrs().put("AjaxExpressionName", ctrlName);
		type.getAttrs().put("AjaxExpression", sb.toString());
				
		assertEquals("<script>function Person_OnChangeCtrl($scope){var doCall = function( $scope ){};doCall( $scope.$parent );}</script>", type.getAttrs().get("AjaxExpression"));
	}
	
	public void testCompute() {
		data.put("Age", Age);
		

		// @formatter:off
		eqValue("" +
				"$scope.$watch('data.Age', function(newValue, oldValue) {\n" +
					"if(newValue){" +
						"$scope.$ajaxCall({" +
							"'$getaction' : 'data.Age'," +
							"'data.Age':$scope.data.Age}," +
							"function($scope,result){" +
								"$scope.data.AgeDerived=result.AgeDerived;" +
							"});" +
					"}" +
				"\n});",
				"this.AgeDerived = this.Age;");
		// @formatter:on
	}

}

package nebula.lang;

import junit.framework.TestCase;

public class NebulaParser_TypeTest extends TestCase {
	RuntimeContext context = new RuntimeContext() {
	};
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest();
	}

	final StringBuilder sb = new StringBuilder();

	public void test_type_File_import() throws Exception {
		TypeImp type = (TypeImp) compiler.findType("P2");

		assertEquals("P2", type.name);

		assertEquals(3, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);

		i++;
		assertEquals("Age", type.fields.get(i).name);
		Field f = type.fields.get(i);
		System.out.println(f.onChangeCode);

		i++;
		assertEquals("AgeDerived", type.fields.get(i).name);

		StringBuilder sb = new StringBuilder();
		String ctrlName = type.getName() + "onChangeCtrl";
		sb.append("<script>function " + ctrlName + "($scope){");
		for (int j = 0; j < type.fields.size(); j++) {
			Field field = type.fields.get(j);
			if (field.getOnChangeCode() != null) {
				sb.append(JsCallCompiler.compiler("data." + field.name, field.onChangeCode, "this", "data"));
			}
		}
		sb.append("}</script>");

		System.out.println(sb.toString());
		type.getAttrs().put("AjaxExpressionName", ctrlName);
		type.getAttrs().put("AjaxExpression", sb.toString());

	}

}

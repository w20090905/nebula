package nebula.lang;

import static nebula.lang.Reference.ByVal;
import static nebula.lang.Reference.Cascade;
import static nebula.lang.Reference.Inline;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class NebulaParser_TypeTest extends TestCase {
	RuntimeContext context = new RuntimeContext() {
	};
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	final StringBuilder sb = new StringBuilder();

	public void test_buildin() throws Exception {
		//@formatter:off
		String text = "" +
				"define FullName extends Name { " +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("FullName", type.name);
		assertEquals(TypeStandalone.Basic, type.standalone);

		assertEquals(0, type.fields.size());
	}

	public void test_buildin_extends() throws Exception {
		//@formatter:off
		String text = "" +
				"@MaxLength(120)\n" +
				"define Name extends String { \n" +
				"};";
		//@formatter:on

		Type type = compiler.load(text);

		assertEquals("Name", type.name);
		assertEquals("String", type.superType.name);
		assertEquals(TypeStandalone.Basic, type.standalone);

		assertEquals(0, type.fields.size());
		assertEquals(new Long("120"), type.attrs.get("MaxLength"));
	}

	public void test_Transaction_1() throws Exception {
		//@formatter:off
		String text = "" +
				"tx Order { " +
				"	!ID;" +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Order", type.name);
		assertEquals(TypeStandalone.Transaction, type.standalone);

		assertEquals(1, type.fields.size());
		assertEquals("ID", type.fields.get(0).name);
	}

	public void test_type_1() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	Name;" +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);
		assertEquals(TypeStandalone.Master, type.standalone);

		assertEquals(1, type.fields.size());
		assertEquals("Name", type.fields.get(0).name);
	}

	public void test_type_File_import() throws Exception {
		Type type = compiler.findType("PersonTest");

		assertEquals("PersonTest", type.name);

		assertEquals(9, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);

		i = 7;
		assertEquals("Welcome", type.fields.get(i).name);

		assertEquals("#	this is a <h1> tag\n## this is a <h2> tag\n###### this is a <h6> tag\n",
				((String) type.fields.get(i).expr.eval(context, null, null)).replaceAll("\r\n", "\n"));

		i++;
		assertEquals("Education", type.fields.get(i).name);
		assertEquals("PersonTest$Education", type.fields.get(i).type.name);
		assertEquals(Inline, type.fields.get(i).refer);
		assertEquals(true, type.fields.get(i).array);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
	}

	public void test_type_Person() throws Exception {
		Type type = compiler.findType("Person");

		assertEquals("Person", type.name);

		assertEquals(9, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);

		i = 8;
		assertEquals("Education", type.fields.get(i).name);
		assertEquals("Person$Education", type.fields.get(i).type.name);
		assertEquals(Inline, type.fields.get(i).refer);
		assertEquals(true, type.fields.get(i).array);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
	}

	public void test_type_Relation() throws Exception {
		//@formatter:off
		String text = "" +
				"rt CompanyPersonMemeber extends Member<Company,Person> { " +
				"};";
		//@formatter:on	
		Type type = compiler.load(text);

		assertEquals("CompanyPersonMemeber", type.name);
		assertEquals(2, type.relations.size());// TODO add relation function
		assertEquals(TypeStandalone.Relation, type.standalone);

		assertEquals(2, type.getDeclaredFields().size());
		int i = 0;
		assertEquals("Company", type.getDeclaredFields().get(i++).name);
		assertEquals("Person", type.getDeclaredFields().get(i++).name);
		i = 0;
		assertEquals(true, type.getDeclaredFields().get(i).isCore());
		assertEquals("Attach", type.getDeclaredFields().get(i++).attrs.get("Attach"));
		
		assertEquals(true, type.getDeclaredFields().get(i).isCore());
		assertEquals("Attach", type.getDeclaredFields().get(i++).attrs.get("Attach"));

		assertEquals(5, type.getFields().size());
		i = 0;
		assertEquals("ID", type.getFields().get(i++).name);
		assertEquals("FromDate", type.getFields().get(i++).name);
		assertEquals("ToDate", type.getFields().get(i++).name);
		assertEquals("Company", type.getFields().get(i++).name);
		assertEquals("Person", type.getFields().get(i++).name);
	}

	public void test_type_PersonCircularDependency() throws Exception {
		Type type = compiler.findType("PersonCircularDependency");

		assertEquals("PersonCircularDependency", type.name);

		assertEquals(8, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);

		i = 1;
		assertEquals("CompanyCircularDependency", type.fields.get(i).name);
		assertEquals("CompanyCircularDependency", type.fields.get(i).type.name);
		assertEquals("Manager", type.fields.get(i).type.fields.get(i).name);
		assertEquals("PersonCircularDependency", type.fields.get(i).type.fields.get(i).type.name);
	}

	public void test_type_with_importance() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!Name;\n" +
				"	*Sex;\n" +
				"	#Length;\n" +
				"	?Height;\n" +
				"	Age;\n" +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isUnique());
		assertEquals(true, type.fields.get(i).isKey());

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isCore());

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isRequired());

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isNullable());

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isRequired());

	}

	public void test_type_inline() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!Name Text;\n" +
				"	Sex;\n" +
				"	&Length;\n" +
				"	!%Height;\n" +
				"	Age;\n" +
				"};";
		//@formatter:on
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i = 0;

		assertEquals("Name", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isKey());
		assertEquals(true, type.fields.get(i).isUnique());
		assertEquals("Text", type.fields.get(i).type.name);
		assertEquals(ByVal, type.fields.get(i).refer);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isRequired());
		assertEquals("Sex", type.fields.get(i).type.name);
		assertEquals(ByVal, type.fields.get(i).refer);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isRequired());
		assertEquals(Inline, type.fields.get(i).refer);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isUnique());
		assertEquals(Cascade, type.fields.get(i).refer);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isRequired());
		assertEquals("Age", type.fields.get(i).type.name);
	}

	public void test_type_type() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!Name Text;\n" +
				"	Sex;\n" +
				"	Length;\n" +
				"	Height;\n" +
				"	Age;\n" +
				"};";
		//@formatter:on
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i = 0;

		assertEquals("Name", type.fields.get(i).name);
		assertEquals(true, type.fields.get(i).isKey());
		assertEquals(true, type.fields.get(i).isUnique());
		assertEquals("Text", type.fields.get(i).type.name);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals("Sex", type.fields.get(i).type.name);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals("Length", type.fields.get(i).type.name);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals("Height", type.fields.get(i).type.name);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals("Age", type.fields.get(i).type.name);
	}

	public void test_type_array() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!Name Text;\n" +
				"	Sex[1..] Text;\n" +
				"	&Length[];\n" +
				"	!%Height[1..5];\n" +
				"	Age[..5];\n" +
				"};";

		//@formatter:on
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i = 0;

		assertEquals("Name", type.fields.get(i).name);
		assertEquals("Text", type.fields.get(i).type.name);
		assertEquals(false, type.fields.get(i).array);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(1, type.fields.get(i).rangeFrom);
		assertEquals(Integer.MAX_VALUE, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Inline, type.fields.get(i).refer);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(Integer.MAX_VALUE, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Cascade, type.fields.get(i).refer);
		assertEquals(1, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals("Age", type.fields.get(i).type.name);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);
	}

	public void test_type_attr_with_inheritFieldTypeAttrs() throws Exception {
		//@formatter:off
		String text = "" +
				"@Length(1)" +
				"@Match(\"dd\")" +
				"@Max(3.8)" +
				"type Person { \n" +
				"	Name;" +
				"	@MaxLength(120)WithCustomAttr Name;" +
				"};";
		//@formatter:on

		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(2, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);
		assertEquals(60L, type.fields.get(i).attrs.get("MaxLength"));

		i++;
		assertEquals("WithCustomAttr", type.fields.get(i).name);
		assertEquals(120L, type.fields.get(i).attrs.get("MaxLength"));

		assertEquals(4, type.attrs.size());
		assertEquals(1L, type.attrs.get("Length"));
		assertEquals("dd", type.attrs.get("Match"));
		assertEquals(new BigDecimal("3.8"), type.attrs.get("Max"));
	}

}

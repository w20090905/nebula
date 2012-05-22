package nebula.compiler;

/***
 * Excerpted from "The Definitive ANTLR Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 ***/
import java.math.BigDecimal;

import junit.framework.TestCase;

public class NebulaParserAdvTest extends TestCase {
	Compiler compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new Compiler();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	final StringBuilder sb = new StringBuilder();

	public void test_type_1() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	Name;" +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(1, type.fields.size());
		assertEquals("Name", type.fields.get(0).name);
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
		assertEquals(Field.KEY, type.fields.get(i).importance);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(Field.CORE, type.fields.get(i).importance);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Field.UNIMPORTANT, type.fields.get(i).importance);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);

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
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals("Text", type.fields.get(i).type.name);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Sex", type.fields.get(i).type.name);
		assertEquals(null, type.fields.get(i).refer);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals(Field.INLINE, type.fields.get(i).refer);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals(Field.CASCADE, type.fields.get(i).refer);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
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
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals("Text", type.fields.get(i).type.name);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Sex", type.fields.get(i).type.name);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Length", type.fields.get(i).type.name);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Height", type.fields.get(i).type.name);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
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
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals("Text", type.fields.get(i).type.name);
		assertEquals(false, type.fields.get(i).array);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals(1, type.fields.get(i).rangeFrom);
		assertEquals(Integer.MAX_VALUE, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals(Field.INLINE, type.fields.get(i).refer);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(Integer.MAX_VALUE, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals(Field.CASCADE, type.fields.get(i).refer);
		assertEquals(1, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Age", type.fields.get(i).type.name);
		assertEquals(0, type.fields.get(i).rangeFrom);
		assertEquals(5, type.fields.get(i).rangeTo);
		assertEquals(true, type.fields.get(i).array);
	}

	public void test_type_attr() throws Exception {
		//@formatter:off
		String text = "" +
				"type Name { \n" +
				"	@length=1;" +
				"	@match=\"dd\";" +
				"	@max=3.8;" +
				"	Age;" +
				"};";
		//@formatter:on

		Type type = compiler.load(text);

		assertEquals("Name", type.name);

		assertEquals(1, type.fields.size());
		int i = 0;
		assertEquals("Age", type.fields.get(i).name);

		assertEquals(3, type.attrs.size());
		assertEquals(1, type.attrs.get("length"));
		assertEquals("dd", type.attrs.get("match"));
		assertEquals(new BigDecimal("3.8"), type.attrs.get("max"));
	}

	public void test_buildin_extends() throws Exception {
		//@formatter:off
		String text = "" +
				"type Name : String { \n" +
				"	@MaxLength=120;\n" +
				"};";
		//@formatter:on

		Type type = compiler.load(text);

		assertEquals("Name", type.name);
		assertEquals("String", type.superType.name);

		assertEquals(0, type.fields.size());
		assertEquals(new Integer("120"), type.attrs.get("MaxLength"));
	}
}

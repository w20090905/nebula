package ne;

/***
 * Excerpted from "The Definitive ANTLR Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 ***/
import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class TestNe extends TestCase {
	final StringBuilder sb = new StringBuilder();

	private NeParser loadFromString(String code) throws Exception {
		return parse(new ANTLRStringStream(code));
	}

//	private NeParser loadFromFile(String filename) throws Exception {
//		return parse(new ANTLRFileStream(filename));
//	}

	private NeParser parse(CharStream stream) throws Exception {
		NeLexer assemblerLexer = new NeLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
		sb.setLength(0);
		NeParser parser = new NeParser(tokens);

		return parser;
	}


	public void test_type_1() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	Name;" +
				"};";
		//@formatter:on		
		NeParser parser = loadFromString(text);
		Type type = parser.typeDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Person", type.name);

		assertEquals(1, type.fields.size());
		assertEquals("Name", type.fields.get(0).name);
	}
	public void test_type_with_importance() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!NameKey;\n" +
				"	*NameImportance;\n" +
				"	#NameRequire;\n" +
				"	?NameUnimportance;\n" +
				"	 NameRequire;\n" +
				"};";
		//@formatter:on		
		NeParser parser = loadFromString(text);
		Type type = parser.typeDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		assertEquals("NameKey", type.fields.get(0).name);
		assertEquals("NameImportance", type.fields.get(1).name);
		assertEquals("NameRequire", type.fields.get(2).name);
		assertEquals("NameUnimportance", type.fields.get(3).name);
		assertEquals("NameRequire", type.fields.get(4).name);
	}
	
	public void test_type_with_importance_2() throws Exception {
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
		NeParser parser = loadFromString(text);
		Type type = parser.typeDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i=0;
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
		NeParser parser = loadFromString(text);
		Type type = parser.typeDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i=0;
		
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
	

	public void test_type_inline() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	!Name Text;\n" +
				"	Sex;\n" +
				"	@Length;\n" +
				"	!%Height;\n" +
				"	Age;\n" +
				"};";
		//@formatter:on
		NeParser parser = loadFromString(text);
		Type type = parser.typeDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Person", type.name);

		assertEquals(5, type.fields.size());
		int i=0;
		
		assertEquals("Name", type.fields.get(i).name);
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals("Text", type.fields.get(i).type.name);

		++i;
		assertEquals("Sex", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Sex", type.fields.get(i).type.name);
		assertEquals("", type.fields.get(i).inline);

		++i;
		assertEquals("Length", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals(Field.INLINE, type.fields.get(i).inline);

		++i;
		assertEquals("Height", type.fields.get(i).name);
		assertEquals(Field.KEY, type.fields.get(i).importance);
		assertEquals(Field.CASCADE, type.fields.get(i).inline);

		++i;
		assertEquals("Age", type.fields.get(i).name);
		assertEquals(Field.REQUIRE, type.fields.get(i).importance);
		assertEquals("Age", type.fields.get(i).type.name);
	}
}

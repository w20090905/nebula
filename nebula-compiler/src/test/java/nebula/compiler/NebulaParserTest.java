package nebula.compiler;

import java.math.BigDecimal;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import junit.framework.TestCase;

public class NebulaParserTest extends TestCase {

	public void testTypeDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"type Person { " +
					"	姓名  Name;" +
					"};";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());

			Type type = parser.typeDefinition();
			
			assertEquals("Person", type.name);

			assertEquals(1, type.fields.size());
			assertEquals("姓名", type.fields.get(0).name);	
			assertEquals(Field.REQUIRE, type.fields.get(0).importance);			
			
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	public void testFieldDefinition() {
		try {
			//@formatter:off
			String text = "!Name;";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());

			Type type = new Type("Test", null,null);
			Field v = parser.fieldDefinition(type);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("Name", v.name);
			assertEquals(Field.KEY, v.importance);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	public void testFieldDefinition_subType() {
		try {
			//@formatter:off
			String text = "!Detail{Name;Age Height;};";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());

			Type type = new Type("Test", null,null);
			Field v = parser.fieldDefinition(type);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("Detail", v.name);
			assertEquals("Detail", v.type.name);
			assertEquals("Name", v.type.fields.get(0).name);
			assertEquals("Name", v.type.fields.get(0).type.name);
			assertEquals("Age", v.type.fields.get(1).name);
			assertEquals("Height", v.type.fields.get(1).type.name);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	/*
	 *  '!' {v=Field.KEY;} 
      | '*' {v=Field.CORE;} 
      | '#' {v=Field.REQUIRE;} 
      | '?' {v=Field.UNIMPORTANT;}
      |     {v=Field.REQUIRE;}
	 */
	public void testFieldImportance_KEY() {
		try {
			//@formatter:off
			String text = "!";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.fieldImportance();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.KEY, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testFieldImportance_CORE() {
		try {
			//@formatter:off
			String text = "*";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.fieldImportance();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.CORE, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testFieldImportance_REQUIRE() {
		try {
			//@formatter:off
			String text = "#";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.fieldImportance();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.REQUIRE, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testFieldImportance_UNIMPORTANT() {
		try {
			//@formatter:off
			String text = "?";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.fieldImportance();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.UNIMPORTANT, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	public void testInlineDefinition_Inline() {
		try {
			//@formatter:off
			String text = "&";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.inlineDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.INLINE, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	public void testInlineDefinition_CASCADE() {
		try {
			//@formatter:off
			String text = "%";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			String v = parser.inlineDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(Field.CASCADE, v);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	public void testArrayDefinition() {
		try {
			//@formatter:off
			String text = "[]";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(null, v.from);
			assertEquals(null, v.to);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testArrayDefinition_1() {
		try {
			//@formatter:off
			String text = "[1]";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("1", v.from);
			assertEquals(null, v.to);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testArrayDefinition_1_() {
		try {
			//@formatter:off
			String text = "[1..]";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("1", v.from);
			assertEquals(null, v.to);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	public void testArrayDefinition_1_10() {
		try {
			//@formatter:off
			String text = "[1..10]";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("1", v.from);
			assertEquals("10", v.to);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	public void testArrayDefinition_0_10() {
		try {
			//@formatter:off
			String text = "[..10]";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			
			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(null, v.from);
			assertEquals("10", v.to);
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	public void testAttrDefinition_String_X() {
		try {
			//@formatter:off
			String text = "MaxLength=\"X\" ";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			Type type = new Type("Test", null,null);
			
			parser.attrDefinition(type);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("X", type.attrs.get("MaxLength"));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	public void testAttrDefinition_BigDecimal_10() {
		try {
			//@formatter:off
			String text = "MaxLength = 1.1 ";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, new ClassPathTypeLoader());
			Type type = new Type("Test", null,null);
			
			parser.attrDefinition(type);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(new BigDecimal("1.1"), type.attrs.get("MaxLength"));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
}

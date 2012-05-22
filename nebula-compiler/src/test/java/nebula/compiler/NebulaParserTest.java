package nebula.compiler;

import java.math.BigDecimal;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import junit.framework.TestCase;

public class NebulaParserTest extends TestCase {

	public void testTypeDefinition() {
		fail("Not yet implemented");
	}

	public void testFieldDefinition() {
		fail("Not yet implemented");
	}

	public void testFieldImportance() {
		fail("Not yet implemented");
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

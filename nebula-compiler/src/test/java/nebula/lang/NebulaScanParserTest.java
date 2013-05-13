package nebula.lang;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import util.InheritHashMap;
public class NebulaScanParserTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}


	public void testTypeDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"type Person { " +
					"	姓名  Name;" +
					"};";
			//@formatter:on		
			NebulaScanLexer lexer = new NebulaScanLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaScanParser parser = new NebulaScanParser(tokens, compiler);

			Type type = parser.typeDefinition();
			
			assertEquals("Person", type.name);
			
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}

	

	

	public void testAttrDefinition_String_X() {
		try {
			//@formatter:off
			String text = "MaxLength=\"X\" ";
			//@formatter:on		
			NebulaScanLexer lexer = new NebulaScanLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaScanParser parser = new NebulaScanParser(tokens, compiler);
			
			InheritHashMap attrs = new InheritHashMap();
			parser.attrItemDefinition(attrs);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals("X", attrs.get("MaxLength"));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	public void testAttrDefinition_BigDecimal_10() {
		try {
			//@formatter:off
			String text = "MaxLength = 1.1 ";
			//@formatter:on		
			NebulaScanLexer lexer = new NebulaScanLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaScanParser parser = new NebulaScanParser(tokens, compiler);

			InheritHashMap attrs = new InheritHashMap();
			parser.attrItemDefinition(attrs);

			assertEquals(0, parser.getNumberOfSyntaxErrors());
			assertEquals(new BigDecimal("1.1"), attrs.get("MaxLength"));
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	
	public void testAliasDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"type Person|zh:员工  { " +
					"	Name|zh:姓名;" +
					"};";
			//@formatter:on		
			NebulaScanLexer lexer = new NebulaScanLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaScanParser parser = new NebulaScanParser(tokens, compiler);

			Type type = parser.typeDefinition();
			
			assertEquals("Person", type.name);

			assertEquals("员工", type.nameAlias.get("zh"));	
			
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	
	public void testProgramDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"type Person|zh:员工  { " +
					"	Name|zh:姓名;" +
					"	Detail |zh:明细{" +
					"		Name;" +
					"		Age;" +
					"	};" +
					"};";
			//@formatter:on		
			NebulaScanLexer lexer = new NebulaScanLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaScanParser parser = new NebulaScanParser(tokens, compiler);

			List<Type> types = parser.programDefinition();
			
			Type type = types.get(0);
			
			assertEquals("Person", type.name);
			assertEquals("员工", type.nameAlias.get("zh"));	

			
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
}

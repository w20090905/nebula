package nebula.compiler;

import java.io.IOException;

import junit.framework.TestCase;

import nebula.compiler.Processor;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 * Unit tests for the ANTLR-generated MathParser class.
 * 
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class NebulaParserTest extends TestCase {


	private Processor processor = new Processor();

	/**
	 * Unit test for AST generation.
	 */
	public void testGetAST() throws IOException, RecognitionException {
		String SCRIPT = "type Person{\n name!; \n age[1,4];\n}\n\n";
		CommonTree ast = processor.getAST(SCRIPT);
		String actual = ast.toStringTree();
		String expected = "(PROG (type Person (FIELD name) (FIELD age)))";
		System.out.println("expected = \"" + expected + '"');
		System.out.println("  actual = \"" + actual + '"');
		assertEquals("correct AST", expected, actual);
	}
	/**
	 * Unit test for AST generation.
	 */
	public void testGetAST_ad() throws IOException, RecognitionException {
		String SCRIPT = "type Person{\n name!; \n age;roles*;\n}\n\n";
		CommonTree ast = processor.getAST(SCRIPT);
		String actual = ast.toStringTree();
		String expected = "(PROG (type Person (FIELD name !) (FIELD age) (FIELD roles *)))";
		System.out.println("expected = \"" + expected + '"');
		System.out.println("  actual = \"" + actual + '"');
		assertEquals("correct AST", expected, actual);
	}
}

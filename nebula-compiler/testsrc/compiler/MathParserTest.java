package compiler;

import java.io.IOException;

import junit.framework.TestCase;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 * Unit tests for the ANTLR-generated MathParser class.
 * 
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class MathParserTest extends TestCase {

	private static final String SCRIPT = "type Person{\n name; \n age;\n}\n\n";

	private Processor processor = new Processor();

	/**
	 * Unit test for AST generation.
	 */
	public void testGetAST() throws IOException, RecognitionException {
		CommonTree ast = processor.getAST(SCRIPT);
		String actual = ast.toStringTree();
		String expected = "(PROG (TYPE Person (FIELD name) (FIELD age)))";
		System.out.println("expected = \"" + expected + '"');
		System.out.println("  actual = \"" + actual + '"');
		assertEquals("correct AST", expected, actual);
	}
}

package nebula.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.TestCase;

import nebula.compiler.Processor;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 * Unit tests for the ANTLR-generated MathTree class.
 * 
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class NebulaTreeTest extends TestCase {

	private Processor processor = new Processor();

	/**
	 * Unit test for processAST.
	 */
	public void testProcessAST() throws IOException, RecognitionException {
		String TYPE_DEFINE = "type Person{\n name!; \n age;\n}\n\n";
		CommonTree ast = processor.getAST(TYPE_DEFINE);

		System.out.println("ast = " + ast);

		// Setup to capture stdout.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream newPS = new PrintStream(baos);
		PrintStream oldPS = System.out;
		System.setOut(newPS);

		processor.processAST(ast);

		// Reset stdout.
		System.setOut(oldPS);

		System.out.println(baos.toString());
		String actual = baos.toString().trim();
		String expected = "5.0";
		//assertEquals("correct output", expected, actual);
	}
}

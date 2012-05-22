package nebula.compiler;

import java.io.IOException;

import junit.framework.TestCase;

import org.antlr.runtime.RecognitionException;

/**
 * Unit tests for the ANTLR-generated MathTree class.
 * 
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class NebulaFileTest extends TestCase {

	private Processor processor = new Processor();

	/**
	 * Unit test for processAST.
	 */
	public void testProcessAST() throws IOException, RecognitionException {
		processor.processFile("Person.neb");		
	}
}

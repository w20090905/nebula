package test.systemio.antlr.neb;

import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;

/**
 * Unit tests for the ANTLR-generated MathLexer class.
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class NebLexerTest extends TestCase {

    private static final String SCRIPT =
        "dsfdsfds\nsdfsdf\n";

    /**
     * Unit test for AST generation.
     */
    public void testGetTokenStream()
    throws IOException {
    	

		//CharStream stringStream = new InputCharStream(new StringReader(SCRIPT));
		CharStream stringStream = new InputCharStream(new InputStreamReader(System.in));
		Lexer lexer = new NebLexer(stringStream);		
        CommonTokenStream cts = new CommonTokenStream(lexer);

//    	cts.consume();
//    	cts.consume();
//    	cts.consume();
//    	cts.consume();
//    	cts.consume();
    	

        int i = cts.LA(1);
	    while(i>0){
	    	cts.consume();
	    	System.out.println(i);
	    	i = cts.LA(1);
	    }
    }
}

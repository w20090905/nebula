package test.language.antlr.systemio;

import java.io.InputStreamReader;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;

import test.systemio.antlr.neb.NebLexer;
import test.systemio.antlr.neb.NebParser;

/**
 * Unit tests for the ANTLR-generated MathTree class.
 * 
 * @author R. Mark Volkmann, Object Computing, Inc.
 */
public class NebIOTest  {

	
	public static void main(String[] args) {
		try {
			CharStream stringStream = new InputCharStream(new InputStreamReader(System.in),8);
			Lexer lexer = new NebLexer(stringStream);
			NebParser tokenParser = new NebParser(new CommonTokenStream(lexer));
			//for(;;){
			tokenParser.echo();
			tokenParser.echo();
			tokenParser.echo();
			tokenParser.echo();
			tokenParser.echo();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
}

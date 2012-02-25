package nebula.vm;
/***
 * Excerpted from "The Definitive ANTLR Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 ***/
import java.io.FileInputStream;
import java.io.InputStream;


import org.antlr.runtime.*;

public class Test {
	public static void main(String[] args) throws Exception {

		InputStream input = null;
		if (args.length > 0) input = new FileInputStream(args[0]);
		else input = System.in;

		ExprLexer lexer = new ExprLexer(new ANTLRInputStream(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Compiler parser = new Compiler(tokens);
		parser.prog();
		ClassSymbol cs =  parser.finished();
		DisAssembler da = new DisAssembler();
		da.disassemble(cs);
		
	}
}

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

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

public class Test extends TestCase {

	private void parse(String filename) throws Exception {
		InputStream input = null;
		input = new FileInputStream(filename);

		Interpreter interpreter = new Interpreter(true);
		ClassSymbol clz = load(input);
		interpreter.resolve(clz);
		interpreter.exec(interpreter.resolve(clz.getEntryPoint()));
	}

	public void testClsDefineOnly() throws Exception {
		parse("code/ClsDefineOnly.n");
	}

	public void testClsFieldGet() throws Exception {
		parse("code/ClsFieldGet.n");
	}

	public ClassSymbol load(InputStream input) throws Exception {
		boolean hasErrors = false;
		try {
			NebulaLexer assemblerLexer = new NebulaLexer(new ANTLRInputStream(input));
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			SourceCompiler parser = new SourceCompiler(tokens);
			parser.compilationUnit();
			ClassSymbol clz = parser.finished();
			hasErrors = parser.getNumberOfSyntaxErrors() > 0;
			if (!hasErrors) {
				return clz;
			}
			throw new RuntimeException("ERROR");
		} finally {
			input.close();
		}
	}
}

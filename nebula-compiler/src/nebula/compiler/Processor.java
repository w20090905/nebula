package nebula.compiler;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class Processor {

	public static void main(String[] args) throws IOException, RecognitionException {
		if (args.length == 0) {
			// new Processor().processInteractive();
		} else if (args.length == 1) { // name of file to process passed in
			new Processor().processFile(args[0]);
		} else { // more than one command-line argument
			System.err.println("usage: java com.ociweb.math.Processor [file-name]");
		}
	}

	public void processFile(String filePath) throws IOException, RecognitionException {
		CommonTree ast = getAST(new FileReader(filePath));
		// System.out.println(ast.toStringTree()); // for debugging
		processAST(ast);
	}

	private CommonTree getAST(Reader reader) throws IOException, RecognitionException {
		NebulaParser tokenParser = new NebulaParser(getTokenStream(reader));
		NebulaParser.prog_return parserResult = tokenParser.prog(); // start
																	// rule
																	// method
		reader.close();
		return (CommonTree) parserResult.getTree();
	}

	private CommonTokenStream getTokenStream(Reader reader) throws IOException {
		
		//NebulaLexer lexer = new NebulaLexer(new ANTLRReaderStream(reader));
		NebulaLexer lexer = new NebulaLexer(new InputCharStream(reader));
		return new CommonTokenStream(lexer);
	}

	// This is public so it can be used by unit tests.
	public void processAST(CommonTree ast) throws RecognitionException {
		NebulaTree treeParser = new NebulaTree(new CommonTreeNodeStream(ast));
		treeParser.prog();
	}

	// private void processInteractive()
	// throws IOException, RecognitionException {
	// ExprTree treeParser =
	// new ExprTree(null); // a TreeNodeStream will be assigned later
	// Scanner scanner = new Scanner(System.in);
	//
	// while (true) {
	// System.out.print("math> ");
	// String line = scanner.nextLine().trim();
	// if ("quit".equals(line) || "exit".equals(line)) break;
	// processLine(treeParser, line);
	// }
	// }

	// This is only used by unit tests and that's why it's public.
	public CommonTree getAST(String script) throws IOException, RecognitionException {
		StringReader sr = new StringReader(script);
		CommonTree ast = getAST(sr);
		sr.close();
		return ast;
	}

	// This is only used by unit tests and that's why it's public.
	public CommonTokenStream getTokenStream(String script) throws IOException {
		StringReader sr = new StringReader(script);
		CommonTokenStream ts = getTokenStream(sr);
		sr.close();
		return ts;
	}

} // end of Processor class

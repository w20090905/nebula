package nebula.compiler;

import java.io.InputStreamReader;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class NebulaIOTest  {

	
	public static void main(String[] args) {
		try {
			CharStream stringStream = new InputCharStream(new InputStreamReader(System.in));
			Lexer lexer = new NebulaLexer(stringStream);
			NebulaParser tokenParser = new NebulaParser(new CommonTokenStream(lexer));
			NebulaTree treeParser = new NebulaTree(new CommonTreeNodeStream(tokenParser.prog().getTree()));
			treeParser.prog();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

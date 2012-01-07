package nebula.compiler;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;



public class Test {
	public static void main(String[] args) throws Exception {

		String[] testStr = { 
				"2",
				"a + b + 3",
				"(a - b) + 3",
				"(a - (b * 2)) + 3",			
				"(a - 1) * a"	,				
				"type Person{\n" +
						" name;\n"+
						" age;\n"+
				"}\n\n"		
		};

		for (String s : testStr) {
			System.out.println("Input Nebula: " + s);
			run(s);
			System.out.println();
		}

	}
	
	public static void run(String Nebula) throws Exception {
		ANTLRStringStream in = new ANTLRStringStream(Nebula);
        //词法分析器
		NebulaLexer lexer = new NebulaLexer(in);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器 
		NebulaParser parser = new NebulaParser(tokens);


		NebulaParser.prog_return ret = parser.prog();
		CommonTree t = (CommonTree)ret.getTree();
		
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
		nodes.setTokenStream(tokens);		
        //编译器
		NebulaTree c_walker = new NebulaTree(nodes);
		c_walker.prog();
	}
}

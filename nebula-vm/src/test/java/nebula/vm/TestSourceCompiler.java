package nebula.vm;

/***
 * Excerpted from "The Definitive ANTLR Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 ***/
import junit.framework.TestCase;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class TestSourceCompiler extends TestCase {
	final StringBuilder sb = new StringBuilder();

	private NebulaParser loadFromString(String code) throws Exception {
		return parse(new ANTLRStringStream(code));
	}

	private NebulaParser loadFromFile(String filename) throws Exception {
		return parse(new ANTLRFileStream(filename));
	}

	private NebulaParser parse(CharStream stream) throws Exception {
		NebulaLexer assemblerLexer = new NebulaLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
		sb.setLength(0);
		NebulaParser parser = new SourceCompiler(tokens) {
			protected void info(String str) {
				if (str.charAt(str.length() - 1) == '\n') {
					String txtTemps = "";
					for (TempVar v : tmps) {
						txtTemps += "" + (v.applied ? " " : v.reg) + " ";
					}
					str = String.format("|%1$-10s|\t%2$s", txtTemps, str);
				}
				sb.append(str);
			};
		};

		return parser;
		// parser.compilationUnit();
		// // new DisAssembler().disassemble(clz);
		// boolean hasErrors = parser.getNumberOfSyntaxErrors() > 0;
		// if (hasErrors) {
		// throw new RuntimeException("ERROR");
		// }
		//
		// // ClassSymbol clz = load(input);
		// // interpreter.resolve(clz);
		// // interpreter.exec(interpreter.resolve(clz.getEntryPoint()));
		// return sb.toString();
	}

	public void testClassDefineFile() throws Exception {
		String filename = "ClsDefineOnly.n";
		NebulaParser parser = loadFromFile(filename);
		ClassSymbol v = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("ClsDefineOnly", v.name);
	}

	public void testClsFieldGet() throws Exception {
		NebulaParser result = loadFromFile("ClsFieldGet.n");
		System.out.println(result);
		String filename = "ClsFieldGet.n";
		NebulaParser parser = loadFromFile(filename);
		ClassSymbol v = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("ClsFieldGet", v.name);
	}

	public void test_classDefinition() throws Exception {
		//@formatter:off
		String text = "" +
				"class Test { " +
				"	int i;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		ClassSymbol v = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("Test", v.name);
		String actual = sb.toString();
		//@formatter:off
		String expected = "" + 
				"|          |\tdefine field i\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_define_i() throws Exception {
		//@formatter:off
		String text = "" +
				"void func(){" +
				"	int i = 0;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		MethodSymbol v = parser.methodDeclaration(new ClassSymbol("Test"));
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("func", v.name);
		String actual = sb.toString();
		//@formatter:off
		String expected = "" +
				"|          |\tFUNC func() {\n" + 
				"|1         |\tLOADI: tmp1#I = 0;\n" + 
				"|1         |\tHIDE : i = tmp1#I;\n" + 
				"|          |\t\n" +
				"|          |\t}\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_add_1_2() throws Exception {
		//@formatter:off
		String text = "" +
				"void func(){" +
				"	int i = 1+2;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		MethodSymbol v = parser.methodDeclaration(new ClassSymbol("Test"));
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("func", v.name);
		String actual = sb.toString();
		//@formatter:off
		String expected = "" +
				"|          |\tFUNC func() {\n" +
				"|1         |\tLOADI: tmp1#I = 1;\n" + 
				"|1 2       |\tLOADI: tmp2#I = 2;\n" + 
				"|1         |\tADD  : tmp1#I = tmp1#I + tmp2#I;\n" + 
				"|1         |\tHIDE : i = tmp1#I;\n" + 
				"|          |\t\n" +
				"|          |\t}\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_invoke() throws Exception {
		//@formatter:off
		String text = "" +
				"void func(){" +
				"	int i = this.test();" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		MethodSymbol v = parser.methodDeclaration(new ClassSymbol("Test"));
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("func", v.name);
		String actual = sb.toString();
		//@formatter:off
		String expected = "" +
				"|          |\tFUNC func() {\n" +
				"|1         |\tMOVE : tmp1#Test = this#Test;\n" + 
				"|1         |\tCALL : tmp1#* = tmp1#Test.Test_test(tmp1#Test );\n" + 
				"|1         |\tHIDE : i = tmp1#*;\n" + 
				"|          |\t\n" +
				"|          |\t}\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_params() throws Exception {
		//@formatter:off
		String text = "" +
				"void func(int a, int b){" +
				"	int c = a + b;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		MethodSymbol v = parser.methodDeclaration(new ClassSymbol("Test"));
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("func", v.name);
		String actual = sb.toString();
		//@formatter:off
		String expected = "" +
				"|          |\tFUNC func() {\n" + 
				"|3         |\tADD  : tmp3#I = a#I + b#I;\n" + 
				"|3         |\tHIDE : c = tmp3#I;\n" + 
				"|          |\t\n" +
				"|          |\t}\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

}

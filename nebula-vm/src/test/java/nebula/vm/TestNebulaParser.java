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

public class TestNebulaParser extends TestCase {
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
		NebulaParser parser = new NebulaParser(tokens) {
			protected void info(String str) {
				if (str.charAt(str.length() - 1) == '\n') {
					String txtTemps = "";
					for (TempVar v : tmps) {
						txtTemps += "" + (v.applied ? " " : v.reg) + " ";
					}
					str = String.format("|%1$-10s|  %2$s", txtTemps, str);
				}
				sb.append(str);
			};
		};

		return parser;
	}

	public void testClassDefineFile() throws Exception {
		String filename = "ClsDefineOnly.n";
		NebulaParser parser = loadFromFile(filename);
		ClassSymbol clz = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("ClsDefineOnly", clz.name);
	}

	public void testClsFieldGet() throws Exception {
		NebulaParser result = loadFromFile("ClsFieldGet.n");
		System.out.println(result);
		String filename = "ClsFieldGet.n";
		NebulaParser parser = loadFromFile(filename);
		ClassSymbol clz = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		System.out.println(sb.toString());

		assertEquals("ClsFieldGet", clz.name);
	}

	public void test_classDefinition() throws Exception {
		//@formatter:off
		String text = "" +
				"class Test { " +
				"	int i;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		ClassSymbol clz = parser.classDefinition();
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);
		
		assertEquals("Test", clz.name);
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" + 
				"|          |  FIELD : i\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_define_i() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello(){" +
				"	int i = 9;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("Test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello", method.name);
		assertEquals("V",method.returnType.getName());
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello() {\n" + 
				"|1         |  ICONST: tmp1#I = 9;\n" + 
				"|1         |  HIDE  : i#I = tmp1#I;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_add_1_2() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello(){" +
				"	int i = 1+2;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello", method.name);
		assertEquals("V",method.returnType.getName());
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello() {\n" +
				"|1         |  ICONST: tmp1#I = 1;\n" + 
				"|1 2       |  ICONST: tmp2#I = 2;\n" + 
				"|1         |  IADD  : tmp1#I = tmp1#I + tmp2#I;\n" + 
				"|1         |  HIDE  : i#I = tmp1#I;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_invoke() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello(){" +
				"	int i = this.test();" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("Test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello", method.name);
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello() {\n" +
				"|1         |  CALL  : tmp1#* = this#Test.Test_test();\n" + 
				"|1         |  HIDE  : i#I = tmp1#*;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}
	
	public void test_methodDefinition_invoke_1() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello(){" +
				"	int we = this.test(2);" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("Test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello", method.name);
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello() {\n" +
				"|1         |  ICONST: tmp1#I = 2;\n" + 
				"|          |  CALL  : tmp1#* = this#Test.Test_test(tmp1#I );\n" + 
				"|          |  HIDE  : we#I = tmp1#*;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

	public void test_methodDefinition_invoke_1_a() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello2a(){" +
				"	int a = 10;" +
				"	int i = this.test(2,a+9);" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("Test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello2a", method.name);
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello2a() {\n" +
				"|1         |  ICONST: tmp1#I = 10;\n" + 
				"|1         |  HIDE  : a#I = tmp1#I;\n" + 
				"|2         |  ICONST: tmp2#I = 2;\n" + 
				"|2 3       |  ICONST: tmp3#I = 9;\n" + 
				"|2 3       |  IADD  : tmp3#I = a#I + tmp3#I;\n" + 
				"|          |  CALL  : tmp2#* = this#Test.Test_test(tmp2#I tmp3#I );\n" + 
				"|          |  HIDE  : i#I = tmp2#*;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}
	public void test_methodDefinition_params() throws Exception {
		//@formatter:off
		String text = "" +
				"void funcSayHello(int a, int b){" +
				"	int c = a + b;" +
				"}";
		//@formatter:on		
		NebulaParser parser = loadFromString(text);
		
		ClassSymbol clz = parser.enterClass("test",null);
		MethodSymbol method = parser.methodDeclaration(clz);
		clz = parser.exitClass(clz);		
		assertTrue(parser.getNumberOfSyntaxErrors() == 0);

		assertEquals("funcSayHello", method.name);
		
		String actual = sb.toString();
		System.out.println(actual);
		//@formatter:off
		String expected = "" +
				"|          |  FUNC  : funcSayHello() {\n" + 
				"|3         |  IADD  : tmp3#I = a#I + b#I;\n" + 
				"|3         |  HIDE  : c#I = tmp3#I;\n" + 
				"|          |  }\n";
		//@formatter:on
		assertEquals(expected, actual);
	}

}

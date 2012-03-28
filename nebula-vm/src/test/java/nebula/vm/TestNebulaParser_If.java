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

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class TestNebulaParser_If extends TestCase {
	final StringBuilder sb = new StringBuilder();

	private NebulaParser loadFromString(String code) throws Exception {
		return parse(new ANTLRStringStream(code));
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
	
    public void test_method_complex_compute() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "   void funcSayHello2a(){\n" +
                "       int a = 10;\n" +
                "       if ( a == 10 ) then a = 30;" +
                "       else a = 256;" +
                "       int i =1;" +
                "       if ( i + 10 ) then i = 30;" +
                "       i = 1234;" +
                "       return a;\n" +
                "    }" +
                "}";
        //@formatter:on     
        NebulaParser parser = loadFromString(text);
        
        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello2a", clz.methods[0].name);
        
        String actual = sb.toString();
        System.out.println(actual);
        //@formatter:off
        String expectedMain = "" +
                "|          |  FUNC  : funcSayHello2a() {\n" + 
                "|1         |  ICONST: tmp1#I = 10;\n" + 
                "|1         |  HIDE  : a#I = tmp1#I;\n" + 
                "|2         |  ICONST: tmp2#I = 10;\n" + 
                "|2         |  IEQ  : tmp2#I = a#I + tmp2#I;\n" + 
                "|2 3       |  ICONST: tmp3#I = 30;\n" + 
                "|2 3       |  HIDE  : a#I = tmp3#I;\n" + 
                "|2         |  ICONST: tmp2#I = 256;\n" + 
                "|2         |  HIDE  : a#I = tmp2#I;\n" + 
                "|2         |  ICONST: tmp2#I = 1;\n" + 
                "|2         |  HIDE  : i#I = tmp2#I;\n" + 
                "|3         |  ICONST: tmp3#I = 10;\n" + 
                "|3         |  IADD  : tmp3#I = i#I + tmp3#I;\n" + 
                "|3 4       |  ICONST: tmp4#I = 30;\n" + 
                "|3 4       |  HIDE  : i#I = tmp4#I;\n" + 
                "|3         |  ICONST: tmp3#I = 1234;\n" + 
                "|3         |  HIDE  : i#I = tmp3#I;\n" + 
                "|          |  RET   : ;\n" + 
                "|          |  }\n";
        //@formatter:on
        assertEquals(expectedMain, actual);
    }
}

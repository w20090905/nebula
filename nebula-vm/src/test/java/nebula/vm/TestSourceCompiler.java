package nebula.vm;

/***
 * Excerpted from "The Definitive ANTLR Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 ***/
import java.io.StringWriter;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class TestSourceCompiler extends TestCase {
    VMInterpreter cpu = new VMInterpreter(true, false);

    private NebulaParser loadFromString(String code) throws Exception {
        return parse(new ANTLRStringStream(code));
    }

    private NebulaParser loadFromFile(String filename) throws Exception {
        return parse(new ANTLRFileStream(filename));
    }

    private NebulaParser parse(CharStream stream) throws Exception {
        NebulaLexer assemblerLexer = new NebulaLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
        NebulaParser parser = new SourceCompiler(tokens);

        return parser;
    }

    private String disassemble(ClassSymbol clz) {
        StringWriter out = new StringWriter();
        DisAssembler dis = new DisAssembler(out);
        dis.disassemble(clz);
        return out.toString();
    }

    private String disassemble(MethodSymbol method) {
        StringWriter out = new StringWriter();
        DisAssembler dis = new DisAssembler(out);
        dis.disassemble(method);
        return out.toString();
    }

    public void testClassDefineFile() throws Exception {
        String filename = "ClsDefineOnly.n";
        NebulaParser parser = loadFromFile(filename);
        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        String actual = disassemble(clz);
        System.out.println(actual);

        assertEquals("ClsDefineOnly", clz.name);
    }

    public void testClsFieldGet() throws Exception {
        NebulaParser result = loadFromFile("ClsFieldGet.n");
        System.out.println(result);
        String filename = "ClsFieldGet.n";
        NebulaParser parser = loadFromFile(filename);
        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        String actual = disassemble(clz);
        System.out.println(actual);

        assertEquals("ClsFieldGet", clz.name);
    }

    public void test_classDefinition() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test { " +
                "    int i;" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);
        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("Test", clz.name);
        assertEquals(1, clz.fields.length);
        assertEquals("i", clz.fields[0].name);
        assertEquals("I", clz.fields[0].type.getName());

        String actual = disassemble(clz);
        System.out.println(actual);
        //@formatter:off
        String expected = "" + 
                ".class Test\n" +
                "\n" +
                ".field i\n" +
                "\n";
        //@formatter:on
        assertEquals(expected, actual);
    }

    public void test_methodDefinition_define_i() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(){" +
                "        int i = 9;" +
                "        return i;" +    
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=0, locals=2\n" +
                "0000: ICONST  r1, 9       \n" +
                "0001: RET     r1, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        expected = "" +
                ".def funcSayHello: args=0, locals=2\n" +
                "0000: ICONST  r1, 9       \n" +
                "0001: RET     r1, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

    public void test_methodDefinition_add_1_2() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(){" +
                "    int i = 1+2;" +
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=0, locals=3\n" +
                "0000: ICONST  r1, 1       \n" + 
                "0001: ICONST  r2, 2       \n" + 
                "0002: IADD    r1, r1, r2  \n" +
                "0003: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        expected = "" +
                ".def funcSayHello: args=0, locals=3\n" +
                "0000: ICONST  r1, 1       \n" + 
                "0001: ICONST  r2, 2       \n" + 
                "0002: IADD    r1, r1, r2  \n" +
                "0003: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

    public void test_methodDefinition_add_1_complex() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(){" +
                "        int a = 3;" +
                "        int b = 5;" +
                "        int c = a + b;" +
                "        c = a + b + a * b;" +
                "    }" +
                "}";
        
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=0, locals=6\n" +
                "0000: ICONST  r1, 3       \n" + 
                "0001: ICONST  r2, 5       \n" + 
                "0002: IADD    r3, r1, r2  \n" +
                "0003: IADD    r4, r1, r2  \n" +
                "0004: IMUL    r5, r1, r2  \n" +
                "0005: IADD    r3, r4, r5  \n" +
                "0006: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        expected = "" +
                ".def funcSayHello: args=0, locals=6\n" +
                "0000: ICONST  r1, 3       \n" + 
                "0001: ICONST  r2, 5       \n" + 
                "0002: IADD    r3, r1, r2  \n" +
                "0003: IADD    r4, r1, r2  \n" +
                "0004: IMUL    r5, r1, r2  \n" +
                "0005: IADD    r3, r4, r5  \n" +
                "0006: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

    public void test_methodDefinition_invoke() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(){" +
                "        int i = this.test();" +
                "    }" +
                "    void test(){" +
                "        return 4;" +
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=0, locals=2\n" +
                "0000: CALL    r0, #2:@Test.test(), r1\n" +
                "0001: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);

        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

    public void test_methodDefinition_invoke_1() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(){" +
                "        int i = this.test(2);" +
                "    }" +
                "    int test(){" +
                "        return 4;" +
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=0, locals=2\n" +
                "0000: ICONST  r1, 2       \n" +
                "0001: CALL    r0, #2:@Test.test(), r1\n" +
                "0002: RET     r0, 0       \n" +
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);

        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

    public void test_methodDefinition_invoke_1_a() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello2a(){" +
                "        int a = 10;" +
                "        int i = this.test(2,a+9);" +
                "        return i;" +
                "    }" +
                "    int test(int a,int b ){" +
                "        return a + b + b;" +
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello2a", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expectedMain = "" +
                ".def funcSayHello2a: args=0, locals=4\n" +
                "0000: ICONST  r1, 10      \n" +
                "0001: ICONST  r2, 2       \n" +
                "0002: ICONST  r3, 9       \n" +
                "0003: IADD    r3, r1, r3  \n" +
                "0004: CALL    r0, #2:@Test.test(), r2\n" +
                "0005: RET     r2, 0       \n" +
                "\n" ;
        //@formatter:on
        assertEquals(expectedMain, actual);
        
        actual = disassemble(clz.methods[1]);
        System.out.println(actual);
        //@formatter:off
        String expectedTest = "" +
                ".def test: args=2, locals=2\n" +
                "0000: IADD    r3, r1, r2  \n" +
                "0001: IADD    r3, r3, r2  \n" +
                "0002: RET     r3, 0       \n" +
                "\n" ;
        //@formatter:on
        assertEquals(expectedTest, actual);
        

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);

        assertEquals(expectedMain, actual);

        int result = cpu.exec(clz.methods[0]);
        assertEquals(40,result);
    }
    
    public void test_method_complex_compute() throws Exception {
        
        NebulaParser parser = loadFromFile("Person.n");
        ClassSymbol clzPerson =  parser.classDefinition();
        
        
        //@formatter:off
        String text = "" +
                "class Test {" +
                "   void funcSayHello2a(){" +
                "       int a = 10;" +
                "       Person p = new Person();" +
                "       p.age = 9;" +
                "       int d = this.test(p.age+1,10);" +
                "       return d;" +
                "    }" +
                "    int test(int a,int b ){" +
                "        return a + b + b;" +
                "    }" +
                "}";
        //@formatter:on        
        parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello2a", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expectedMain = "" +
                ".def funcSayHello2a: args=0, locals=5\n" +
                "0000: ICONST  r1, 10      \n" +
                "0001: STRUCT  r2, 1024    \n" +
                "0002: ICONST  r3, 9       \n" +
                "0003: FSTORE  r2, #3:@Person.age, r3\n" +
                "0004: FLOAD   r3, r2, #3:@Person.age\n" +
                "0005: ICONST  r4, 1       \n" +
                "0006: IADD    r3, r3, r4  \n" +
                "0007: ICONST  r4, 10      \n" +
                "0008: CALL    r0, #4:@Test.test(), r3\n" +
                "0009: RET     r3, 0       \n" +
                "\n" ;
        //@formatter:on
        assertEquals(expectedMain, actual);
        
        actual = disassemble(clz.methods[1]);
        System.out.println(actual);
        //@formatter:off
        String expectedTest = "" +
                ".def test: args=2, locals=2\n" +
                "0000: IADD    r3, r1, r2  \n" +
                "0001: IADD    r3, r3, r2  \n" +
                "0002: RET     r3, 0       \n" +
                "\n" ;
        //@formatter:on
        assertEquals(expectedTest, actual);
        
        cpu.resolve(clzPerson);
        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);

        assertEquals(expectedMain, actual);
        cpu.resolve(clz.methods[0]);
        int result = cpu.exec(clz.methods[0]);
        assertEquals(30,result);
    }

    public void test_methodDefinition_params() throws Exception {
        //@formatter:off
        String text = "" +
                "class Test {" +
                "    void funcSayHello(int a, int b){" +
                "        int c = a + b;" +
                "    }" +
                "}";
        //@formatter:on        
        NebulaParser parser = loadFromString(text);

        ClassSymbol clz = parser.classDefinition();
        assertTrue(parser.getNumberOfSyntaxErrors() == 0);

        assertEquals("funcSayHello", clz.methods[0].name);

        String actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        String expected = "" +
                ".def funcSayHello: args=2, locals=2\n" +
                "0000: IADD    r3, r1, r2  \n" +
                "0001: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.resolve(clz);

        actual = disassemble(clz.methods[0]);
        System.out.println(actual);
        //@formatter:off
        expected = "" +
                ".def funcSayHello: args=2, locals=2\n" +
                "0000: IADD    r3, r1, r2  \n" +
                "0001: RET     r0, 0       \n" + 
                "\n";
        //@formatter:on
        assertEquals(expected, actual);

        cpu.exec(clz.methods[0]);
    }

}

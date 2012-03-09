package test.StringTemplate;
import junit.framework.TestCase;

import org.antlr.stringtemplate.StringTemplate;

public class TemplateTest extends TestCase {
    
     public void testHelloWorld() {
        StringTemplate hello = new StringTemplate("Hello, $name$");
        hello.setAttribute("name", "World");
        System.out.println(hello.toString());
    }   
    
     public void testHelloWorld2() {
        StringTemplate hello = new StringTemplate("Hello, $name$");
        hello.setAttribute("name", "World");
        System.out.println(hello.toString());
    }

    
     public void testGroup() {
        StringTemplate hello = new StringTemplate("Hello, $name$");
        hello.setAttribute("name", "World");
        System.out.println(hello.toString());
    }
}

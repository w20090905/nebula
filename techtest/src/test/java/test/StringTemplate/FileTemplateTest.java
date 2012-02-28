package test.StringTemplate;

import java.io.StringReader;

import junit.framework.TestCase;

import org.antlr.stringtemplate.CommonGroupLoader;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.tool.ErrorManager;


public class FileTemplateTest  extends TestCase{
    String templates = "group simple;" + "" + "vardef(type,name) ::= \"<type> <name>;\"" + ""
            + "method(type,name,args) ::= <<" + "<type> <name>(<args; separator=\",\">) {"
            + "  <statements; separator=\"\n\">" + "}" + ">>";

    public void testGroup() {
        // Use the constructor that accepts a Reader
        StringTemplateGroup group = new StringTemplateGroup(new StringReader(templates));
        StringTemplate t = group.getInstanceOf("vardef");
        t.setAttribute("type", "int");
        t.setAttribute("name", "foo");
        System.out.println(t);

    }

    public void testFile() {
        StringTemplateGroup group = new StringTemplateGroup("myGroup");
        StringTemplate query = group.getInstanceOf("tmp/theQuery");
        query.setAttribute("column", "name");
        query.setAttribute("column", "email");
        query.setAttribute("table", "User");
        System.out.println(query);
    }

    public void testGroupFile() {
        StringTemplateGroup.registerGroupLoader(new CommonGroupLoader("tmp", StringTemplateGroup.DEFAULT_ERROR_LISTENER));
        StringTemplateGroup group = StringTemplateGroup.loadGroup("simple");
        StringTemplate t = group.getInstanceOf("vardef");
        t.setAttribute("type", "int");
        t.setAttribute("name", "foo");
        t.setAttribute("name", "fo1");
        System.out.println(t);
        
        
        t = group.getInstanceOf("table");
        t.setAttribute("type", "int");
        t.setAttribute("name", "upper");
        t.setAttribute("name", "down");
        System.out.println(t);
                
    }

};

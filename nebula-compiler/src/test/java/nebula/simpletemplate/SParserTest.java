package nebula.simpletemplate;

import java.io.IOException;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.STLexer;

public class SParserTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void test_SimpleScalar() throws IOException, RecognitionException {

		String text = "${name}${age}${male}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		TemplateImpl template = p.templateAndEOF();
		System.out.println(template.toString());
		String expected = "String tempalte(name,age,male){\n" + "	sb.append(name);\n" + "	sb.append(age);\n" + "	sb.append(male);\n" + "}\n";

		assertEquals(expected, template.toString());
	}

	public void test_Type() throws IOException, RecognitionException {

		String text = "<title>${at.name}${at.name}</html>";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		TemplateImpl template = p.templateAndEOF();
		System.out.println(template.toString());
		String expected = "String tempalte(at){\n" + "	sb.append(\"<title>\");\n" + "	sb.append(at.name);\n" + "	sb.append(at.name);\n"
				+ "	sb.append(\"</html>\");\n" + "}\n";

		assertEquals(expected, template.toString());
	}

	public void test_Include() throws IOException, RecognitionException {

		String text = "${data()}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		TemplateImpl template = p.templateAndEOF();
		System.out.println(template.toString());
		String expected = "String tempalte(at){\n" + "	sb.append(\"<title>\");\n" + "	sb.append(at.name);\n" + "	sb.append(at.name);\n"
				+ "	sb.append(\"</html>\");\n" + "}\n";

		assertEquals(expected, template.toString());
	}

}

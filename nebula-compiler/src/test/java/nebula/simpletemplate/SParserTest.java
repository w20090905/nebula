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

	public void test_OnlyStaticText() throws IOException, RecognitionException {

		String text = "OnlyStaticText";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(){\n" +
				"	sb.append(\"OnlyStaticText\");\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_SimpleScalar() throws IOException, RecognitionException {

		String text = "${name}${age}${male}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name,age,male){\n" +
				"	sb.append(name);\n" +
				"	sb.append(age);\n" +
				"	sb.append(male);\n" +
				"}\n";
		//@formatter:on
		assertEquals(expected, template.toString());
	}

	public void test_Type() throws IOException, RecognitionException {

		String text = "<title>${at.name}${at.name}</html>";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());
		//@formatter:off
		String expected = "String tempalte(at){\n" +
				"	sb.append(\"<title>\");\n" +
				"	sb.append(at.name);\n" +
				"	sb.append(at.name);\n" +
				"	sb.append(\"</html>\");\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_Include() throws IOException, RecognitionException {

		String text = "${data()}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(){\n" +
				"	sb.append('data'());\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeExprName() throws IOException, RecognitionException {

		String text = "${(data)()}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(data){\n" +
				"	sb.append(data());\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeWithParams() throws IOException, RecognitionException {

		String text = "${data(name,age)}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name,age){\n" +
				"	sb.append('data'(name,age));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}
	
	public void test_IncludeWithLeadingParam() throws IOException, RecognitionException {

		String text = "${name:data(age,male)}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name,age,male){\n" +
				"	sb.append('data'(name,age,male));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeWithLeadingParamList() throws IOException, RecognitionException {

		String text = "${name,age,male:data(birthday)}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name,age,male,birthday){\n" +
				"	sb.append('data'(name,age,male,birthday));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeWithTemplateList() throws IOException, RecognitionException {

		String text = "${name,age,male:data(birthday):good()}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name,age,male,birthday){\n" +
				"	sb.append('good'('data'(name,age,male,birthday)));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeSubTempalteNoArgs() throws IOException, RecognitionException {

		String text = "${{ssss}}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(){\n" +
				"	sb.append(template[0]());\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}
	
	public void test_IncludeSubTempalte() throws IOException, RecognitionException {

		String text = "${name:{ssss}}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name){\n" +
				"	sb.append(template[0](name));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

	public void test_IncludeSubTempalteWithArgName() throws IOException, RecognitionException {

		String text = "${name:{x| ${x}}}";
		STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SParser p = new SParser(tokens, nebula.simpletemplate.STGroup.defaultGroup);
		CompiledST template = p.templateAndEOF();
		System.out.println(template.toString());

		//@formatter:off
		String expected = "String tempalte(name){\n" +
				"	sb.append(template[0](name));\n" +
				"}\n";
		//@formatter:on

		assertEquals(expected, template.toString());
	}

}

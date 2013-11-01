package nebula.simpletemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import junit.framework.TestCase;
import nebula.simpletemplate.Action;
import nebula.simpletemplate.ActionComplier;
import nebula.simpletemplate.CompilerContext;
import nebula.simpletemplate.SParser;
import nebula.simpletemplate.Statement;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.STLexer;

import com.google.common.collect.Maps;

public class STParser_BasicTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	private void parseType(String text) throws IOException {
		try {

			text = "<html>\r\n<head>\r\n<title>${name}${name}${name}${name}${name}${name}${name}${name}${name}${name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SParser p = new SParser(tokens);

			Statement r = p.templateAndEOF();
			System.out.println(r.toString());
			String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			{
				int MAX = 1000 * 100;
				String desc = "simple type";
				// setUp
				Person person = new Person();
				person.setName("wangshilian");
				CompilerContext c = new CompilerContext(person.getClass());

				Action action = ActionComplier.DEFAULT.compile(c, "test", r);
				StringWriter out = new StringWriter();
				out = new StringWriter();

				action.exec(out, person);
				assertEquals(expected, out.toString());

				// prepare
				long start, end, nanoAll, nanoEvery;
				start = System.nanoTime();
				for (int i = 0; i < MAX; i++) {
					action.exec(out, person);
				}
				end = System.nanoTime();
				nanoAll = end - start;
				nanoEvery = nanoAll / MAX;

				System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
						1000 * 1000 * 1000 / nanoEvery);
			}

			{
				int MAX = 1000 * 100;
				String desc = "simple map";
				// setUp
				Map<String, String> root = Maps.newHashMap();
				root.put("name", "wangshilian");
				CompilerContext c = new CompilerContext(root.getClass());

				Action action = ActionComplier.DEFAULT.compile(c, "test", r);
				StringWriter out = new StringWriter();
				out = new StringWriter();

				action.exec(out, root);
				assertEquals(expected, out.toString());
				out = new StringWriter();
				// prepare
				long start, end, nanoAll, nanoEvery;
				start = System.nanoTime();
				for (int i = 0; i < MAX; i++) {
					action.exec(out, root);
				}
				end = System.nanoTime();
				nanoAll = end - start;
				nanoEvery = nanoAll / MAX;

				System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
						1000 * 1000 * 1000 / nanoEvery);
			}

			assertEquals(0, p.getNumberOfSyntaxErrors());

			return;
		} catch (RecognitionException e) {
			fail(e.toString());
			return;
		}
	}

	public void testTypeDefinition() throws IOException {
		//@formatter:off
			String text = "" +
					" Hello${name};";
		//@formatter:on	
		parseType(text);
	}
}

package nebula.simpletemplate;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.STLexer;

import com.google.common.collect.Maps;

public class SParser_PerformanceTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void testparseType() throws IOException {
		try {


			{
				String text = "<html>\r\n<head>\r\n<title>${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

				STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

				CommonTokenStream tokens = new CommonTokenStream(lexer);
				SParser p = new SParser(tokens,nebula.simpletemplate.STGroup.defaultGroup);

				TemplateImpl r = p.templateAndEOF();
				System.out.println(r.toString());
				String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

				
				int MAX = 1000 * 100;
				String desc = "simple type";
				// setUp
				Person person = new Person();
				person.setName("wangshilian");
				CompilerContext c = new CompilerContext(new Object[]{person});

				Action action = ActionComplier.DEFAULT.compile(c, "test", r.code);
				StringBuilder out = new StringBuilder();

				action.exec(out, new Object[]{person});
				assertEquals(expected, out.toString());

				out = new StringBuilder();
				// prepare
				long start, end, nanoAll, nanoEvery;
				start = System.nanoTime();
				for (int i = 0; i < MAX; i++) {
					out.setLength(0);
					action.exec(out, new Object[]{person});
					out.toString();
				}
				end = System.nanoTime();
				nanoAll = end - start;
				nanoEvery = nanoAll / MAX;

				System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
						1000 * 1000 * 1000 / nanoEvery);
				assertEquals(0, p.getNumberOfSyntaxErrors());
			}

			{
				String text = "<html>\r\n<head>\r\n<title>${name}${name}${name}${name}${name}${name}${name}${name}${name}${name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

				STLexer lexer = new STLexer(STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(text), null, '$', '}');

				CommonTokenStream tokens = new CommonTokenStream(lexer);
				SParser p = new SParser(tokens,nebula.simpletemplate.STGroup.defaultGroup);

				TemplateImpl r = p.templateAndEOF();
				System.out.println(r.toString());
				String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

				
				int MAX = 1000 * 100;
				String desc = "simple map";
				// setUp
				Map<String, String> root = Maps.newHashMap();
				root.put("name", "wangshilian");
				CompilerContext c = new CompilerContext(new Object[]{"wangshilian"});

				Action action = ActionComplier.DEFAULT.compile(c, "test", r.code);
				StringBuilder out = new StringBuilder();

				action.exec(out, new Object[]{"wangshilian"});
				assertEquals(expected, out.toString());
				out = new StringBuilder();
				// prepare
				long start, end, nanoAll, nanoEvery;
				start = System.nanoTime();
				out = new StringBuilder(1024);
				for (int i = 0; i < MAX; i++) {
					out.setLength(0);
					action.exec(out, new Object[]{"wangshilian"});
					out.toString();
				}
				end = System.nanoTime();
				nanoAll = end - start;
				nanoEvery = nanoAll / MAX;

				System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
						1000 * 1000 * 1000 / nanoEvery);
				assertEquals(0, p.getNumberOfSyntaxErrors());
			}


			return;
		} catch (RecognitionException e) {
			fail(e.toString());
			return;
		}
	}
}

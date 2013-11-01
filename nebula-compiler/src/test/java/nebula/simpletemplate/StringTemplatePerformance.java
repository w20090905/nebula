package nebula.simpletemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import junit.framework.TestCase;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StringTemplatePerformance extends TestCase {
	Log log = LogFactory.getLog(getClass());

	public static class Person {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public void testBean() throws IOException, TemplateException {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		Person person = new Person();
		person.name = "wangshilian";

		int MAX = 1000 * 10;
		{
			String desc = "stringtemplate";
			// setUp

			StringTemplateGroup templates = new StringTemplateGroup("mygroup", "src/test/java/test/language/antlr/StringTemplate");
			// manually ask for an ST instance
			StringTemplate t = templates.getInstanceOf("pageBean");
			t.setAttribute("person", person);
			assertEquals(expected, t.toString());

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				t.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}

		{
			String desc = "stringtemplate";
			// setUp

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("type", new InputStreamReader(this.getClass().getResourceAsStream("pageBean.ftl")), templateConfiguration);

			Map<String, Object> root = Maps.newHashMap();
			root.put("person", person);

			StringWriter sw = new StringWriter();
			ft.process(root, sw);
			assertEquals(expected, sw.toString());

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				ft.process(root, sw);
				// sw.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}
		{
			String desc = "stringtemplate";
			// setUp

			PageManual p = new PageManual();
			
			Map<String, Object> root = Maps.newHashMap();
			root.put("person", person);

			assertEquals(expected, p.compile2(root));

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				p.compile2(root);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}

	}

	public void testStringValue() throws IOException, TemplateException {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000 * 10;
		{
			String desc = "stringtemplate";
			// setUp
			
			StringTemplateGroup templates = new StringTemplateGroup("mygroup", "src/test/java/test/language/antlr/StringTemplate");
			// manually ask for an ST instance
			StringTemplate t = templates.getInstanceOf("pageStringValue");
			t.setAttribute("name", "wangshilian");
			assertEquals(expected, t.toString());

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				t.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}

		{
			String desc = "stringtemplate";
			// setUp

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("type", new InputStreamReader(this.getClass().getResourceAsStream("pageStringValue.ftl")), templateConfiguration);

			Map<String, Object> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			StringWriter sw = new StringWriter();
			ft.process(root, sw);
			assertEquals(expected, sw.toString());

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				ft.process(root, sw);
				// sw.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}

		{
			String desc = "stringtemplate";
			// setUp
			PageManual p = new PageManual();

			Map<String, Object> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			assertEquals(expected, p.compile(root));

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				p.compile(root);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %16s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}
	}
};

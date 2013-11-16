package test.language.antlr.StringTemplate;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;

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

	public void testBean() throws Exception {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		Person person = new Person();
		person.name = "wangshilian";

		int MAX = 1000 * 10;
		{
			String desc = "bean ST";
			// setUp

			StringTemplateGroup templates = new StringTemplateGroup("mygroup", "src/test/java/test/language/antlr/StringTemplate");
			// manually ask for an ST instance
			StringTemplate t = templates.getInstanceOf("stBean");
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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
//		{
//			String desc = "bean MyST";
//			// setUp
//
////			STGroupDir templates = new STGroupDir("src/test/java/test/language/antlr/StringTemplate",'$','$');
//			// manually ask for an ST instance
//			ST t = new ST("<html>\r\n<head>\r\n<title>$person.name$$person.name$$person.name$$person.name$$person.name$$person.name$$person.name$$person.name$$person.name$$person.name$</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>",'$','$');
//			t.add("person", person);
//			assertEquals(expected, t.toString());
//
//			long start, end, nanoAll, nanoEvery;
//
//			start = System.nanoTime();
//			for (int i = 0; i < MAX; i++) {
//				t.render();
//			}
//			end = System.nanoTime();
//			nanoAll = end - start;
//			nanoEvery = nanoAll / MAX;
//
//			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
//					1000 * 1000 * 1000 / nanoEvery);
//		}
		{
			String desc = "bean smarty4j";
			// setUp
			Engine smartyEngine = new Engine();
			org.lilystudio.smarty4j.Template template = smartyEngine.getTemplate("/src/test/java/test/language/antlr/StringTemplate/smarty4jBean.tpl");
			Context context = new Context();
			context.set("person", person);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			template.merge(context, out);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				out = new ByteArrayOutputStream();
				template.merge(context, out);
				// out.toString("utf-8");
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "bean httl";
			// setUp
//			Engine smartyEngine = new Engine();
//			org.lilystudio.smarty4j.Template template = smartyEngine.getTemplate("/src/test/java/test/language/antlr/StringTemplate/smarty4jBean.tpl");
//			Context context = new Context();
//			context.set("person", person);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("person", person);

			httl.Engine engine = httl.Engine.getEngine();
			httl.Template template = engine.getTemplate("httlBean.httl");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			template.merge(context, out);
			template.render(parameters, out);
			
			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				out = new ByteArrayOutputStream();
//				template.merge(context, out);
				template.render(parameters, out);
				// out.toString("utf-8");
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "bean Freemarker";
			// setUp

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("type", new InputStreamReader(this.getClass().getResourceAsStream("freemarkerBean.ftl")), templateConfiguration);

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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "bean javacode";
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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

	}

	public void testStringValue() throws Exception {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000 * 10;
		{
			String desc = "string ST";
			// setUp

			StringTemplateGroup templates = new StringTemplateGroup("mygroup", "src/test/java/test/language/antlr/StringTemplate");
			// manually ask for an ST instance
			StringTemplate t = templates.getInstanceOf("stStringValue");
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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "string smarty4j";
			// setUp
			Engine smartyEngine = new Engine();
			org.lilystudio.smarty4j.Template template = smartyEngine.getTemplate("/src/test/java/test/language/antlr/StringTemplate/smarty4jBean.tpl");
			Context context = new Context();
			context.set("name", "wangshilian");

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				template.merge(context, out);
				// out.toString("utf-8");
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "string freemarker";
			// setUp

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("type", new InputStreamReader(this.getClass().getResourceAsStream("freemarkerStringValue.ftl")), templateConfiguration);

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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "string javacode";
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

			System.out.printf("[ %-20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
	}
};

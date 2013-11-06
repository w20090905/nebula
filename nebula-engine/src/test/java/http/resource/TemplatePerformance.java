package http.resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplatePerformance extends TestCase {
	Log log = LogFactory.getLog(getClass());

	public void testBean() throws IOException, TemplateException {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000 * 10;
		{
			String desc = "bean ST";
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

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}


	}

	public void testStringValue() throws IOException, TemplateException {
		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000 * 10;
		{
			String desc = "string ST";
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

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc,(nanoAll / (1000 * 1000)) , + nanoEvery , 1000 * 1000 * 1000 /nanoEvery );
		}

	}
};

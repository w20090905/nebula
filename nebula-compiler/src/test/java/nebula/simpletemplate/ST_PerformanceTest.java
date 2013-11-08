package nebula.simpletemplate;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import com.google.common.collect.Maps;

public class ST_PerformanceTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void testSimpleVar() throws IOException {

		int MAX = 1000 * 10;
		{
			String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
			String text = "<html>\r\n<head>\r\n<title>${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			ST st = new ST(text, '$', '}');
			String desc = "type";
			// setUp
			Person person = new Person();
			person.setName("wangshilian");

			assertEquals(expected, st.render(person));

			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render(person);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
			String text = "<html>\r\n<head>\r\n<title>${name}${name}${name}${name}${name}${name}${name}${name}${name}${name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			ST st = new ST(text, '$', '}');
			String desc = "map";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			assertEquals(expected, st.renderNamed(root));
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.renderNamed(root);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		MAX = 1000 * 1;
		{
			String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
			String text = "<html>\r\n<head>\r\n<title>${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}${at.name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			org.stringtemplate.v4.ST st = new org.stringtemplate.v4.ST(text, '$', '}');
			String desc = "ST type";
			Person person = new Person();
			person.setName("wangshilian");
			// setUp
			st.add("at", person);

			assertEquals(expected, st.render());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
			String text = "<html>\r\n<head>\r\n<title>${name}${name}${name}${name}${name}${name}${name}${name}${name}${name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

			org.stringtemplate.v4.ST st = new org.stringtemplate.v4.ST(text, '$', '}');
			String desc = "ST map";
			// setUp
			st.add("name", "wangshilian");

			assertEquals(expected, st.render());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "new StringBuilder(1024)";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

			@SuppressWarnings("unused")
			StringBuilder sb = null;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb = new StringBuilder(1024);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		return;
	}

	public void testSubTemplate() throws IOException {
		String expected = "<html>\r\n<head>\r\n<title>print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
		String text = "<html>\r\n<head>\r\n<title>${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000 * 10;
		{

			String desc = "javacode sub temp";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			TestSubTemplate_Normal a = new TestSubTemplate_Normal();
			StringBuilder sb = new StringBuilder();

			Object[] params = new Object[] { "wangshilian" };
			a.exec(null, null, sb, params);

			// assertEquals(expected, sb.toString());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				a.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{

			ST st = new ST(text, '$', '}');
			String desc = "sub temp";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			assertEquals(expected, st.renderNamed(root));
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.renderNamed(root);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			org.stringtemplate.v4.ST st = new org.stringtemplate.v4.ST(text, '$', '}');
			String desc = "ST sub temp";
			// setUp
			st.add("name", "wangshilian");

			assertEquals(expected, st.render());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		return;
	}

	public void testSubTemplate_javacode() throws IOException {
		String expected = "<html>\r\n<head>\r\n<title>print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)print(wangshilian)</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
//		String text = "<html>\r\n<head>\r\n<title>${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}${name:{x| print(${x})}}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		int MAX = 1000*10;

		{

			String desc = "normal";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			TestSubTemplate_Normal a = new TestSubTemplate_Normal();
			StringBuilder sb = new StringBuilder();

			Object[] params = new Object[] { "wangshilian" };
			a.exec(null, null, sb, params);

			assertEquals(expected, sb.toString());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				a.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{

			String desc = "final static";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			StringBuilder sb = new StringBuilder();

			Object[] params = new Object[] { "wangshilian" };
			TestSubTemplate_FinalStatic.exec(null, null, sb, params);

			assertEquals(expected, sb.toString());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				TestSubTemplate_FinalStatic.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{

			String desc = "inline";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			TestSubTemplate_Inline a = new TestSubTemplate_Inline();
			StringBuilder sb = new StringBuilder();

			Object[] params = new Object[] { "wangshilian" };
			a.exec(null, null, sb, params);

			assertEquals(expected, sb.toString());
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				a.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %-20s ]\tAll :%8d ms; \tevery : %8d nano;\tone second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		return;
	}
}

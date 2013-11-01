package nebula.simpletemplate;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import com.google.common.collect.Maps;

public class ST_BasicTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	private void parseType(String text) throws IOException {

		String expected = "<html>\r\n<head>\r\n<title>wangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilianwangshilian</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";
		text = "<html>\r\n<head>\r\n<title>${name}${name}${name}${name}${name}${name}${name}${name}${name}${name}</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>";

		ST st = new ST(text);

		int MAX = 1000 * 100;
		{
			String desc = "simple type";
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
			String desc = "simple map";
			// setUp
			Map<String, String> root = Maps.newHashMap();
			root.put("name", "wangshilian");

			assertEquals(expected, st.render(root));
			// prepare
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render(root);
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

	public void testTypeDefinition() throws IOException {
		//@formatter:off
			String text = "" +
					" Hello${name};";
		//@formatter:on	
		parseType(text);
	}
}

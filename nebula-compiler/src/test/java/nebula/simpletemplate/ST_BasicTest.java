package nebula.simpletemplate;

import java.math.BigDecimal;
import java.util.Map;

import junit.framework.TestCase;

import com.google.common.collect.Maps;

public class ST_BasicTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void testTypes() {
		String text = "${name}\t${age}\t${male}\t${value}";
		String expected = "wangshilian\t10\ttrue\t3.1415";

		ST st = new ST(text);

		// setUp
		Person person = new Person();
		person.setName("wangshilian");
		person.setAge(10);
		person.setMale(true);
		person.setValue(new BigDecimal("3.1415"));

		assertEquals(expected, st.render(person));
	}

	public void testTypesMap() {

		String text = "${name}\t${age}\t${male}\t${value}";
		String expected = "wangshilian\t10\ttrue\t3.1415";
		
		ST st = new ST(text);

		// setUp
		Map<String, Object> person = Maps.newHashMap();
		person.put("name", "wangshilian");
		person.put("age", 10);
		person.put("male", true);
		person.put("value", new BigDecimal("3.1415"));

		int MAX = 1000 * 100;
		{
			String desc = "stringtemplate";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render(person);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		assertEquals(expected, st.render(person));
	}
}

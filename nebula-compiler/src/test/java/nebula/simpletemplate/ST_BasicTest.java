package nebula.simpletemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import junit.framework.TestCase;

public class ST_BasicTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void testMaps() {
		String text = "${name}\t${age}\t${male}\t${value}";
		String expected = "wangshilian\t10\ttrue\t3.1415";

		ST st = new ST(text);

		// setUp
		Map<String, Object> root = Maps.newHashMap();
		root.put("name", "wangshilian");
		root.put("age", 10);
		root.put("male", true);
		root.put("value", new BigDecimal("3.1415"));

		assertEquals(expected, st.renderNamed(root));
	}

	public void testTypes() {
		String text = "${at.name}\t${at.age}\t${at.male}\t${at.value}";
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

	public void testTypesList() {
		String text = "${at.name}\t${at.age}\t${at.male}\t${at.value}";
		String expected = "wangshilian\t10\ttrue\t3.1415wangshilian\t10\ttrue\t3.1415wangshilian\t10\ttrue\t3.1415";

		ST st = new ST(text);

		// setUp
		List<Person> dataList = Lists.newArrayList();

		{
			Person person = new Person();
			person.setName("wangshilian");
			person.setAge(10);
			person.setMale(true);
			person.setValue(new BigDecimal("3.1415"));
			dataList.add(person);
		}
		{
			Person person = new Person();
			person.setName("wangshilian");
			person.setAge(10);
			person.setMale(true);
			person.setValue(new BigDecimal("3.1415"));
			dataList.add(person);
		}
		{
			Person person = new Person();
			person.setName("wangshilian");
			person.setAge(10);
			person.setMale(true);
			person.setValue(new BigDecimal("3.1415"));
			dataList.add(person);
		}

		assertEquals(expected, st.renderList(dataList));
	}

	String newline = "\n";

	public void testInclude() throws Exception {
		String template = "load ${box()};";
		ST st = new ST(template);
		// st.impl.nativeGroup.defineTemplate("box", "kewl" + newline +
		// "daddy");
		String expected = "load kewl" + newline + "daddy;";
		String result = st.render(new Object());
		assertEquals(expected, result);
	}
}

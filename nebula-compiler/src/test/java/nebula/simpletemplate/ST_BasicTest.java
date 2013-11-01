package nebula.simpletemplate;

import java.math.BigDecimal;

import junit.framework.TestCase;

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
	String newline = "\n";
	public void testInclude() throws Exception {
		String template = "load ${box()};";
		ST st = new ST(template);
//		st.impl.nativeGroup.defineTemplate("box", "kewl" + newline + "daddy");
		String expected = "load kewl" + newline + "daddy;";
		String result = st.render(new Object());
		assertEquals(expected, result);
	}
}

package nebula.simpletemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class ST_BasicTest extends BasicTest {
    public static final String tmpdir = "tmp";//System.getProperty("java.io.tmpdir");

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
		String template = "load ${box(xx)};";
		String expected = "load kewl" + newline + "daddy;";
		
		ST st = new ST(template);
		
		 st.impl.group.defineTemplate("box", "kewl" + newline + "daddy");

		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		
		String result = st.renderNamed(root);
		assertEquals(expected, result);
	}
	

	public void testIncludeSubTempalte() throws Exception {
		String template = "load ${xx: {kewl\ndaddy}};";
		String expected = "load kewl\ndaddy;";
		
		ST st = new ST(template);
		
		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		
		String result = st.renderNamed(root);
		assertEquals(expected, result);
	}


	public void testIncludeSubTempalteQueue() throws Exception {
		String template = "load ${xx: {x1 | 111 ${x1}kewl\ndaddy}:{xx | 222 ${xx}}};";
		String expected = "load 222 111 wangshiliankewl\ndaddy;";
		
		ST st = new ST(template);
		
		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		
		String result = st.renderNamed(root);
		assertEquals(expected, result);
	}
	
	
	public void testSubTempalte() throws Exception {
		String template = "${{kewl\ndaddy}};";
		String expected = "kewl\ndaddy;";
		
		ST st = new ST(template);
		
		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		
		String result = st.renderNamed(root);
		assertEquals(expected, result);
	}
	
	public void testGroupFile() throws Exception {		
		String template = "t(xx) ::= <<load ${data(xx)}>>\n" +
				"data(name) ::= <<kewl\ndaddy;>>";
		String expected = "load kewl\ndaddy;";
		writeFile(tmpdir, "t.stg", template);

		STGroup group =STGroup.fromGroupFile(tmpdir + "/" + "t.stg");
		TemplateImpl tmp = group.getTemplate("t");
		
		
		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		String result = tmp.exec(root);
		assertEquals(expected, result);
	}
	
	
	public void testGroupPath() throws Exception {		
		String templateT = "load ${data(xx)}";
		String templateData	=	"kewl\ndaddy;";
		
		String expected = "load kewl\ndaddy;";
		writeFile(tmpdir, "t.st", templateT);
		writeFile(tmpdir, "data.st", templateData);

		STGroup group = new STGroupPath("tmp");
		TemplateImpl tmp = group.getTemplate("t");
		
		
		Map<String, Object> root = Maps.newHashMap();
		root.put("xx", "wangshilian");
		String result = tmp.exec(root);
		assertEquals(expected, result);
	}
	

}

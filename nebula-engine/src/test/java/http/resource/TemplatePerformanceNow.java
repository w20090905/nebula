package http.resource;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.TypeDatastore;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.simpletemplate.ST;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplatePerformanceNow extends TestCase {
	Log log = LogFactory.getLog(this.getClass());

	// Configuration templateConfig;
	DataStore<Entity> attributes;
	TypeLoader typeLoader;
	// DataRepos dataWareHouse;
	TypeDatastore typeBrokers;

	static final String PATH_OF_ROOT = "htdocs2";

	protected void setUp() throws Exception {
		Broker.clear();

		// ROOT Folder
		File root = null;
		URL url = this.getClass().getResource("/" + PATH_OF_ROOT + "/WEB-INF/web.xml");
		if (url != null) {
			root = new File(url.getPath()).getParentFile().getParentFile();
		}

		if (root == null) {
			root = new File(PATH_OF_ROOT);
		}

		if (!root.exists()) {
			throw new RuntimeException("cannot find " + PATH_OF_ROOT);
		}

		// Type Define locator
		EditableTypeLoader typeLoader = new EditableTypeLoader(new SystemTypeLoader(), new File("apps/system"));
		typeLoader.registerPath(new File("apps/ids"));

		this.typeBrokers = new TypeDatastore(typeLoader);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPerfomance() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "${type.fields : {f| { ${f.name} , ${f.type.name} \\} }}";
		String freemarkerTemplate = "<#list type.fields as f>{ ${f.name} , ${f.type.name} } </#list>";
		String expected = "{ Name , Name } { Birthday , Birthday } { Height , Height } { Age , Age } { Sex , Sex } { Detail , Person$Detail } { Company , Company } { Roles1 , Text } { Roles2 , Long } { Roles3 , Date } { Roles4 , Time } { Education , Person$Education } ";

		type = Broker.brokerOf(type).get();

		ST st = new ST(template,'$','}');

		int MAX = 1000 * 1;
		{
			String desc = "type simpletempl";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

			assertEquals(expected, st.render(type));

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				st.render(type);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
//		{
//			String desc = "type ST";
//			// setUp
//			// template =
//			// "<type.fields : { f | \\{ <f.name> , <f.type.name> \\} }>";
//			// template = "<type.name>";
//
//			org.stringtemplate.v4.ST t = new org.stringtemplate.v4.ST(template, '$', '}');
//			// manually ask for an ST instance
//			t.add("type", type);
//			assertEquals(expected, t.render());
//
//			// prepare
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
//			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
//					1000 * 1000 * 1000 / nanoEvery);
//		}
		{
			String desc = "type freemarker";
			// setUp
			// template = "${type.name}";

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("test", new StringReader(freemarkerTemplate), templateConfiguration);

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("type", type);

			StringWriter sw = new StringWriter();
			ft.process(root, sw);

			assertEquals(expected, sw.toString());

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sw = new StringWriter();
				ft.process(root, sw);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "type javacode";
			// setUp
			ActionComplier_test_TEST_0 p = new ActionComplier_test_TEST_0();
			StringBuilder sb = new StringBuilder();
			Object[] params = new Object[] { type };
			p.exec(null, null, sb, params);			
			assertEquals(expected, sb.toString());
			

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				p.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

	}
	

	public final void testPerfomance_JavaCode() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String expected = "{ Name , Name } { Birthday , Birthday } { Height , Height } { Age , Age } { Sex , Sex } { Detail , Person$Detail } { Company , Company } { Roles1 , Text } { Roles2 , Long } { Roles3 , Date } { Roles4 , Time } { Education , Person$Education } ";

		type = Broker.brokerOf(type).get();

		int MAX = 1000 *100;
	
		{
			String desc = "type javacode";
			// setUp
			ActionComplier_test_TEST_0 p = new ActionComplier_test_TEST_0();
			StringBuilder sb = new StringBuilder();
			Object[] params = new Object[] { type };
			p.exec(null, null, sb, params);			
			assertEquals(expected, sb.toString());
			

			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				sb.setLength(0);
				p.exec(null, null, sb, params);
				sb.toString();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

	}
}

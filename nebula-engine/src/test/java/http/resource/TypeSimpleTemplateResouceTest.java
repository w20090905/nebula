package http.resource;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.TypeDatastore;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.simpletemplate.ST;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TypeSimpleTemplateResouceTest extends TestCase {
	Log log = LogFactory.getLog(this.getClass());

	// Configuration templateConfig;
	DataStore<Entity> attributes;
	TypeLoader typeLoader;
	// DataRepos dataWareHouse;
	TypeDatastore typeBrokers;

	static final String PATH_OF_ROOT = "htdocs2";

	// static final String APP_DEFINE_PATH = "app_define_path";
	// static final String DB_DRIVERCLASS = "db_driverclass";
	// static final String DB_URL = "db_url";
	// static final String DB_USERNAME = "db_username";
	// static final String DB_PASSWORD = "db_password";

	protected void setUp() throws Exception {

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String dburl = "jdbc:derby:memory:eh;create = true";
		String username = "user";
		String password = "password";

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

		DbConfiguration dbConfiguration = DbConfiguration.getEngine(driverclass, dburl, username, password);

		// dataWareHouse = new DbDataRepos(this.typeBrokers, dbConfiguration);

		// // Freemarker
		// templateConfig = new Configuration();
		// templateConfig.setDefaultEncoding("utf-8");
		// templateConfig.setEncoding(Locale.getDefault(), "utf-8");
		// templateConfig.setTemplateUpdateDelay(1);
		// templateConfig.setNumberFormat("0.####");
		// templateConfig.setDirectoryForTemplateLoading(new File(root,
		// "template"));

		// this.attributes = dataWareHouse.define(String.class, Entity.class,
		// "Attribute");

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testTypeName() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "name=${type.name},standalone=${type.standalone}";
		String expected = "name=Person,standalone=Master";

		type = Broker.valueOf(type);

		ST st = new ST(template);
		assertEquals(expected, st.render(type));
		//
		// int MAX = 1000;
		// {
		// String desc = "stringtemplate";
		// // setUp
		//
		// // prepare
		// long start, end, nanoAll, nanoEvery;
		//
		// start = System.nanoTime();
		// for (int i = 0; i < MAX; i++) {
		// st.render(type);
		// }
		// end = System.nanoTime();
		// nanoAll = end - start;
		// nanoEvery = nanoAll / MAX;
		//
		// System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n",
		// desc, (nanoAll / (1000 * 1000)), +nanoEvery,
		// 1000 * 1000 * 1000 / nanoEvery);
		// }
	}

	public final void testLoadIssueType() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "${type.fields : {f| { ${f.name} , ${f.type.name} ${f.key} ,${f.core} \\} }}";
		String expected = "{ Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		type = Broker.brokerOf(type).get();

		ST st = new ST(template);
		assertEquals(expected, st.render(type));
	}

	public final void testLoadIssueTypePer() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "${type.fields : {f| { ${f.name} , ${f.type.name} ${f.key} ,${f.core} \\} }}";
		String expected = "{ Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		type = Broker.brokerOf(type).get();

		ST st = new ST(template);
		
		int MAX = 1000 * 10;
		{
			String desc = "stringtemplate";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

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
	}
}

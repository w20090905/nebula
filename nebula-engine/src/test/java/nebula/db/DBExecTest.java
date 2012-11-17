package nebula.db;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class DBExecTest extends TestCase {
	TypeLoader loader;
	Type t;
	DBExec dbExec;
	DbConfiguration config;

	Persistence<Entity> p;
	Store<Entity> store;

	protected void setUp() throws Exception {
		loader = new SystemTypeLoader();

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db/test-DBExecTest;create = true";
		String username = "user";
		String password = "password";

		config = DbConfiguration.getEngine(driverclass, url, username, password);

	}

	protected void tearDown() throws Exception {
		dbExec.drop();
	}

	public final void testInlineType() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"	Age;" +
				"};";
		//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);
		Map<String, Object> data ;
		dbExec = config.getPersister(t);
		dbExec.init();

		try {
			data = dbExec.get("wangshilian");
			fail("should error");
		} catch (RuntimeException e) {
		}
				
		data = new HashMap<String, Object>();
		data.put("Name", "wangshilian");
		data.put("Age", 10L);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");
		
		assertNotNull(data);
		
		 text = "" +
					"type Person { " +
					"	!Name;" +
					"	Age;" +
					"	Height;" +
					"	Date;" +
					"};";
			//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);
		dbExec.init();
		data = dbExec.get("wangshilian");
		assertNotNull(data);
		
		System.out.println(data);
		//
		// assertEquals("NPerson", dbExec.getTableName());
		//
		// assertEquals(5, dbExec.columns.length);
		// int i = 0;
		// assertEquals("Name", dbExec.columns[i].fieldName);
		// assertEquals(true, dbExec.columns[i].key);
		// i++;
		// assertEquals("TestKey", dbExec.columns[i].fieldName);
		// assertEquals(true, dbExec.columns[i].key);
		// i++;
		// assertEquals("TestCore", dbExec.columns[i].fieldName);
		// assertEquals(false, dbExec.columns[i].key);
		// i++;
		// assertEquals("TestRequire", dbExec.columns[i].fieldName);
		// assertEquals(false, dbExec.columns[i].key);
		// i++;
		// assertEquals("TestIgnore", dbExec.columns[i].fieldName);
		// assertEquals(false, dbExec.columns[i].key);
		//
		// assertEquals("SELECT count(1) FROM NPerson ", dbExec.builderCount());
		//
		// assertEquals("CREATE TABLE NPerson(NAME varchar(60)," +
		// "TEST_KEY varchar(60)," +
		// "TEST_CORE bigint," +
		// "TEST_REQUIRE bigint," +
		// "TEST_IGNORE bigint," +
		// "PRIMARY KEY ( NAME,TEST_KEY)," +
		// "TIMESTAMP_ TIMESTAMP)", dbExec.builderCreate());

	}
	

	public final void testRemoveColumn() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"	Age;" +
				"};";
		//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);
		Map<String, Object> data ;
		dbExec = config.getPersister(t);
		dbExec.init();

		try {
			data = dbExec.get("wangshilian");
			fail("should error");
		} catch (RuntimeException e) {
		}
				
		data = new HashMap<String, Object>();
		data.put("Name", "wangshilian");
		data.put("Age", 10L);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");
		
		assertNotNull(data);
		
		 text = "" +
					"type Person { " +
					"	!Name;" +
					"};";
			//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);
		dbExec.init();
		data = dbExec.get("wangshilian");
		assertNotNull(data);
		
		System.out.println(data);

	}
}

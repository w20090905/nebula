package nebula.data.db;

import java.io.StringReader;

import junit.framework.TestCase;
import nebula.data.db.DbConfiguration;
import nebula.data.db.SqlHelper;
import nebula.data.db.derby.DerbyConfiguration;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class SqlHelperTest extends TestCase {
	TypeLoader loader;
	Type t;
	SqlHelper h;
	DbConfiguration config;

	protected void setUp() throws Exception {
		loader = new SystemTypeLoader();
		config = new DerbyConfiguration("","","","");

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testInlineType() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"   !Test{" +
				"		!Key Name;" +
				"		*Core Age;" +
				"		#Require Age;" +
				"		?Ignore Age;" +
				"	 };" +
				"};";
		//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);
		h = new  SqlHelper(config,t);
		assertEquals("NPerson", h.getTableName());	

		assertEquals(5, h.columns.length);
		int i = 0;
		assertEquals("Name", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("TestKey", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("TestCore", h.columns[i].fieldName);	
		assertEquals(false, h.columns[i].key);			
		i++;
		assertEquals("TestRequire", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("TestIgnore", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		
		assertEquals("SELECT count(1) FROM NPerson ", h.builderCount());
		
		assertEquals("CREATE TABLE NPerson(NAME varchar(60) NOT NULL," +
				"TEST_KEY varchar(60) NOT NULL," +
				"TEST_CORE bigint," +
				"TEST_REQUIRE bigint," +
				"TEST_IGNORE bigint," +
				"PRIMARY KEY ( NAME,TEST_KEY)," +
				"TIMESTAMP_ TIMESTAMP)", h.builderCreate());
		
	}	

	public final void testRefType() {
		//@formatter:off
		String text = "" +
				"type TestPerson { " +
				"	!Name;" +
				"   Test{" +
				"		!Key Name;" +
				"		*Core Age;" +
				"		#Require Age;" +
				"		?Ignore Age;" +
				"	 };" +
				"	TestRef;" +
				"};";
		//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);
		h = new  SqlHelper(config,t);
		assertEquals("NTestPerson", h.getTableName());	

		int i = 0;
		assertEquals("Name", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("TestKey", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);		
		i++;
		assertEquals("TestCore", h.columns[i].fieldName);	
		assertEquals(false, h.columns[i].key);			
		i++;
		assertEquals("TestRequire", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("TestIgnore", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);	
		
		i++;
		assertEquals("TestRefKey", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);	
		
		i++;
		assertEquals("TestRefCore", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				


		assertEquals(i+1, h.columns.length);
		
		assertEquals("SELECT count(1) FROM NTestPerson ", h.builderCount());

		assertEquals("CREATE TABLE NTestPerson(" +
				"NAME varchar(60) NOT NULL," +
				"TEST_KEY varchar(60)," +
				"TEST_CORE bigint," +
				"TEST_REQUIRE bigint," +
				"TEST_IGNORE bigint," +
				"TESTREF_KEY varchar(60)," +
				"TESTREF_CORE varchar(60)," +
				"PRIMARY KEY ( NAME)," +
				"TIMESTAMP_ TIMESTAMP)", 
				h.builderCreate());
	}
	


	public final void testTypse() {
		//@formatter:off
		String text = "" +
				"type TestPerson { " +
				"	!Name;" +
				"	Date;" +
				"	Time;" +
				"	Datetime;" +
				"	Timestamp;" +
				"	Quantity;" +
				"	Amount;" +
				"};";
		//@formatter:on		

		t = loader.defineNebula(new StringReader(text)).get(0);
		h = new  SqlHelper(config,t);
		assertEquals("NTestPerson", h.getTableName());	

		int i = 0;
		assertEquals("Name", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("Date", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);		
		i++;
		assertEquals("Time", h.columns[i].fieldName);	
		assertEquals(false, h.columns[i].key);			
		i++;
		assertEquals("Datetime", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("Timestamp", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("Quantity", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("Amount", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);	
				


		assertEquals(i+1, h.columns.length);
		
		assertEquals("SELECT count(1) FROM NTestPerson ", h.builderCount());

		assertEquals("CREATE TABLE NTestPerson(" +
				"NAME varchar(60) NOT NULL," +
				"DATE date," +
				"TIME time," +
				"DATETIME timestamp," +
				"TIMESTAMP timestamp," +
				"QUANTITY bigint," +
				"AMOUNT numeric(10,2)," +
				"PRIMARY KEY ( NAME)," +
				"TIMESTAMP_ TIMESTAMP)", 
				h.builderCreate());
	}
}

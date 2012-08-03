package nebula.db;

import java.io.StringReader;

import junit.framework.TestCase;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class SqlHelperTest extends TestCase {
	TypeLoader loader;
	Type t;
	SqlHelper h;

	protected void setUp() throws Exception {
		loader = new SystemTypeLoader();

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
		h = new  SqlHelper(t);
		assertEquals("NPerson", h.getTableName());	

		assertEquals(5, h.columns.length);
		int i = 0;
		assertEquals("Name", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("Test_Key", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("Test_Core", h.columns[i].fieldName);	
		assertEquals(false, h.columns[i].key);			
		i++;
		assertEquals("Test_Require", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("Test_Ignore", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		
		assertEquals("SELECT count(1) FROM NPerson ", h.builderCount());
		
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
		h = new  SqlHelper(t);
		assertEquals("NTestPerson", h.getTableName());	

		int i = 0;
		assertEquals("Name", h.columns[i].fieldName);
		assertEquals(true, h.columns[i].key);		
		i++;
		assertEquals("Test_Key", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);		
		i++;
		assertEquals("Test_Core", h.columns[i].fieldName);	
		assertEquals(false, h.columns[i].key);			
		i++;
		assertEquals("Test_Require", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				
		i++;
		assertEquals("Test_Ignore", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);	
		
		i++;
		assertEquals("TestRef_Key", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);	
		
		i++;
		assertEquals("TestRef_Core", h.columns[i].fieldName);
		assertEquals(false, h.columns[i].key);				


		assertEquals(i+1, h.columns.length);
		
		assertEquals("SELECT count(1) FROM NTestPerson ", h.builderCount());
		
	}
}

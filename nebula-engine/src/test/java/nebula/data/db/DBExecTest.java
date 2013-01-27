package nebula.data.db;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import junit.framework.TestCase;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

public class DBExecTest extends TestCase {
	TypeLoaderForTest loader;
	Type t;
	DBExec dbExec;
	DbConfiguration config;

	DataPersister<Entity> p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest();

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db/test-DBExecTest;create = true";
		String username = "user";
		String password = "password";

		config = DbConfiguration.getEngine(driverclass, url, username, password);
	}

	protected void tearDown() throws Exception {
	}

	public final void testInlineType() throws Exception {
		Statement statement;
		ResultSet rs;
		ResultSetMetaData metaData;
		int i = 0;

		/*********************************************************/
		/***** test init *****/
		/*********************************************************/
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		t = loader.testDefineNebula(new StringReader(text)).get(0);
		EditableEntity data;
		dbExec = config.getPersister(t);
		dbExec.drop();
		dbExec = null;

		dbExec = config.getPersister(t);

		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(3, metaData.getColumnCount());

		i = 1;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));

		rs.close();
		// ************ Check Database table Layout *************/

		try {
			data = dbExec.get("wangshilian");
			fail("should error");
		} catch (RuntimeException e) {
		}

		data = new EditableEntity();
		data.put("PersonName", "wangshilian");
		data.put("Age", 10L);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");

		assertNotNull(data);

		dbExec.close();
		dbExec = null;

		/*********************************************************/
		/***** test add column *****/
		/*********************************************************/
		//@formatter:off
		text = "" + 
				"type Person { " + 
				"	!PersonName Name;" + 
				"	Age;" + 
				"	Active;" +
				"	Height;" +
				"	Price;" + 
				"	Name;" + 
				"	Comment;" + 
				"	Date;" + 
				"	Time;" + 
				"	Datetime;" + 
				"	Timestamp;" + 
				"};";
		// @formatter:on

		t = loader.testDefineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);

		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(12, metaData.getColumnCount());

		i = 1;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Active".toUpperCase(), metaData.getColumnName(i));
		assertEquals("SmallInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Height".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Price".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Numeric".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Comment".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(1200, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Date".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Time".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Time".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Datetime".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));

		rs.close();
		// ************ Check Database table Layout *************/

		data = dbExec.get("wangshilian");
		assertNotNull(data);

		System.out.println(data);

		dbExec.close();
		dbExec = null;

		/*********************************************************/
		/***** test modify column *****/
		/*********************************************************/
		//@formatter:off
		text = "" + 
				"type Person { " + 
				"	@maxLength=250;!PersonName Name;" + 
				"	Age Name;" + 
				"	Height;" + 
				"	Date;" + 
				"};";
		// @formatter:on

		t = loader.testDefineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);

		data = dbExec.get("wangshilian");
		assertNotNull(data);

		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(5, metaData.getColumnCount());

		i = 1;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(250, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Height".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Date".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));

		rs.close();
		// ************ Check Database table Layout *************/

		dbExec.close();
		dbExec = null;

		/*********************************************************/
		/***** test remove column *****/
		/*********************************************************/
		//@formatter:off
		text = "" + 
				"type Person { " + 
				"	!PersonName Name;" + 
				"	Height;" + 
				"	Date;" + 
				"};";
		// @formatter:on

		t = loader.testDefineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);
		data = dbExec.get("wangshilian");
		assertNotNull(data);

		
		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(4, metaData.getColumnCount());

		i = 1;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(250, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Height".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Date".toUpperCase(), metaData.getColumnTypeName(i));

		rs.close();
		// ************ Check Database table Layout *************/

		/*********************************************************/
		/***** test change key *****/
		/*********************************************************/
		//@formatter:off
		text = "" + 
				"type Person { " + 
				"	!Name;" + 
				"	PersonName Name;" + 
				"	Height;" + 
				"	Date;" + 
				"};";
		// @formatter:on

		assertEquals(1, dbExec.getAll().size());
		
		t = loader.testDefineNebula(new StringReader(text)).get(0);
		dbExec = config.getPersister(t);

		assertEquals(0, dbExec.getAll().size());

		data = new EditableEntity();
		data.put("Name", "wangshilian");
		data.put("Age", 10L);

		dbExec.insert(data);
		
		assertEquals(1, dbExec.getAll().size());

		data = dbExec.get("wangshilian");
		assertNotNull(data);

		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(5, metaData.getColumnCount());

		i = 1;
		assertEquals("Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Height".toUpperCase(), metaData.getColumnName(i));
		assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Date".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));

		rs.close();
		// ************ Check Database table Layout *************/

		dbExec.drop();

		dbExec.close();
		dbExec = null;
	}

	public final void testRemoveColumn() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"	Age;" +
				"};";
		//@formatter:on		

		t = loader.testDefineNebula(new StringReader(text)).get(0);
		EditableEntity data;
		dbExec = config.getPersister(t);
		//dbExec.init();

		try {
			data = dbExec.get("wangshilian");
			fail("should error");
		} catch (RuntimeException e) {
		}

		data = new EditableEntity();
		data.put("Name", "wangshilian");
		data.put("Age", 10L);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");

		assertNotNull(data);

		text = "" + "type Person { " + "	!Name;" + "};";
		// @formatter:on

		t = loader.testDefineNebula(new StringReader(text)).get(0);

		dbExec = config.getPersister(t);
//		dbExec.init();
		data = dbExec.get("wangshilian");
		assertNotNull(data);

		System.out.println(data);

	}
}

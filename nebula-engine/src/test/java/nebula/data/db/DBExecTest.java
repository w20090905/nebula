package nebula.data.db;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		config.shutdown();
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


	@SuppressWarnings("unchecked")
	public final void testListTypeData() throws Exception {
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
				"	Age[1..10];" +
				"	Alies[1..1000] Name;" + 
				"	Comment[1..1000];" + 
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

		assertEquals(5, metaData.getColumnCount());

		i = 1;
		assertEquals("PersonName".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Alies".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Comment".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
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
		List<Long> ages = new ArrayList<>();
		ages.add(10L);
		ages.add(200L);
		ages.add(30000L);
		data.put("Age", ages);

		List<String> alies = new ArrayList<>();
		alies.add("10L");
		alies.add("200L");
		alies.add("30[]~^000L");
		data.put("Alies", alies);


		List<String> comments = new ArrayList<>();
		comments.add("C10L");
		comments.add("C200L");
		comments.add("C30[]~^000L");
		data.put("Comment", comments);
		
		dbExec.insert(data);

		data = null;
		
		data = dbExec.get("wangshilian");

		ages = (List<Long>) data.get("Age");
		assertEquals(3, ages.size());
		assertEquals((Long)10L, ages.get(0));
		assertEquals((Long)200L, ages.get(1));
		assertEquals((Long)30000L, ages.get(2));

		alies = (List<String>) data.get("Alies");
		assertEquals(3, alies.size());
		assertEquals("10L", alies.get(0));
		assertEquals("200L", alies.get(1));
		assertEquals("30[]~^000L", alies.get(2));
		
		alies = (List<String>) data.get("Comment");
		assertEquals(3, alies.size());
		assertEquals("C10L", alies.get(0));
		assertEquals("C200L", alies.get(1));
		assertEquals("C30[]~^000L", alies.get(2));
		
		assertNotNull(data);

		dbExec.close();
		dbExec = null;
		
	}
	@SuppressWarnings("unchecked")
	public final void testListType() throws Exception {
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
				"	Age[1..10];" + 
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
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
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
		List<Long> ages = new ArrayList<>();
		ages.add(10L);
		ages.add(200L);
		ages.add(30000L);
		data.put("Age", ages);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");

		ages = (List<Long>) data.get("Age");

		assertEquals(3, ages.size());
		assertEquals((Long)10L, ages.get(0));
		assertEquals((Long)200L, ages.get(1));
		assertEquals((Long)30000L, ages.get(2));
		
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
				"	Age[1..10];" + 
				"	Active[1..10];" +
				"	Height[1..10];" +
				"	Price[1..10];" + 
				"	Name[1..10];" + 
				"	Comment[1..10];" + 
				"	Date[1..10];" + 
				"	Time[1..10];" + 
				"	Datetime[1..10];" + 
				"	Timestamp[1..10];" + 
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
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Active".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Height".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Price".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(4000, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Comment".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(4000, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Time".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Datetime".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Timestamp".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));

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
				"	Age;" + 
				"	Height[1..10];" + 
				"	Date[1..10];" + 
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
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Date".toUpperCase(), metaData.getColumnName(i));
		assertEquals("VARCHAR".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Bigint".toUpperCase(), metaData.getColumnTypeName(i));

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
		data.put("Height", 10L);

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
		// dbExec.init();

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
		// dbExec.init();
		data = dbExec.get("wangshilian");
		assertNotNull(data);

		System.out.println(data);

	}
}

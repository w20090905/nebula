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

public class DBExecDataTest extends TestCase {
	TypeLoaderForTest loader;
	Type t;
	DBExec dbExec;
	DbConfiguration config;

	DataPersister<Entity> p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest();

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db-test/test-DBExecTest;create = true";
		String username = "user";
		String password = "password";

		config = DbConfiguration.getEngine(driverclass, url, username, password);
	}

	protected void tearDown() throws Exception {
		try {
			config.shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
//		String txtIcon = "" +
//				"type Icon {" +
//				"	!Name;" + 
//				"};";
		String txtType = "" +
				"type MenuBar {" +
				"	!Name;" + 
				"	Menu[]{" +
				"		!Name;" +
				"		Icon Name;" +
				"		URL;" +
				"	};" + 
				"};";
		//@formatter:on		

//		loader.testDefineNebula(new StringReader(txtIcon)).get(0);
		t = loader.testDefineNebula(new StringReader(txtType)).get(0);
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
		assertEquals("Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Menu_Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Menu_Icon".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Menu_Url".toUpperCase(), metaData.getColumnName(i));
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
		data.put("Name", "wangshilian");
		List<EditableEntity> items =new ArrayList<EditableEntity>(); 
		EditableEntity item = new EditableEntity();
		item.put("Name", "First");
		item.put("Icon", "First");
		item.put("URL", "First");
		items.add(item);

		 item = new EditableEntity();
		item.put("Name", "Second");
		item.put("Icon", "Second");
		item.put("URL", "Second");
		items.add(item);
		
		 item = new EditableEntity();
		item.put("Name", "Third");
		item.put("Icon", "Third");
		item.put("URL", "Third");
		items.add(item);
		
		data.put("Menu", items);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");
//
//		ages = (List<Long>) data.get("Age");
//
//		assertEquals(3, ages.size());
//		assertEquals((Long) 10L, ages.get(0));
//		assertEquals((Long) 200L, ages.get(1));
//		assertEquals((Long) 30000L, ages.get(2));

		assertNotNull(data);

		dbExec.drop();

		dbExec.close();
		dbExec = null;
	}
	

	@SuppressWarnings("unchecked")
	public final void testNestedListType() throws Exception {
		Statement statement;
		ResultSet rs;
		ResultSetMetaData metaData;
		int i = 0;

		/*********************************************************/
		/***** test init *****/
		/*********************************************************/
		//@formatter:off
//		String txtIcon = "" +
//				"type Icon {" +
//				"	!Name;" + 
//				"};";
		String txtType = "" +
				"type MenuBar {" +
				"	!Name;" + 
				"	Menu[]{" +
				"		!Name;" +
				"		Icon Name;" +
				"		URL;" +
				"	};" + 
				"};";
		//@formatter:on		

//		loader.testDefineNebula(new StringReader(txtIcon)).get(0);
		t = loader.testDefineNebula(new StringReader(txtType)).get(0);
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
		assertEquals("Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		assertEquals(60, metaData.getColumnDisplaySize(i));
		i++;
		assertEquals("Menu_Name".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Menu_Icon".toUpperCase(), metaData.getColumnName(i));
		assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		i++;
		assertEquals("Menu_Url".toUpperCase(), metaData.getColumnName(i));
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
		data.put("Name", "wangshilian");
		List<EditableEntity> items =new ArrayList<EditableEntity>(); 
		EditableEntity item = new EditableEntity();
		item.put("Name", "First");
		item.put("Icon", "First");
		item.put("URL", "First");
		items.add(item);

		 item = new EditableEntity();
		item.put("Name", "Second");
		item.put("Icon", "Second");
		item.put("URL", "Second");
		items.add(item);
		
		 item = new EditableEntity();
		item.put("Name", "Third");
		item.put("Icon", "Third");
		item.put("URL", "Third");
		items.add(item);
		
		data.put("Menu", items);

		dbExec.insert(data);

		data = dbExec.get("wangshilian");
//
//		ages = (List<Long>) data.get("Age");
//
//		assertEquals(3, ages.size());
//		assertEquals((Long) 10L, ages.get(0));
//		assertEquals((Long) 200L, ages.get(1));
//		assertEquals((Long) 30000L, ages.get(2));

		assertNotNull(data);

		dbExec.drop();

		dbExec.close();
		dbExec = null;
	}
}

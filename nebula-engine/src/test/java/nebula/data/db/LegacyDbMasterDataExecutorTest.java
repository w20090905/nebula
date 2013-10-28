package nebula.data.db;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import junit.framework.TestCase;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LegacyDbMasterDataExecutorTest extends TestCase {
	TypeLoader loader;
	Type t;
	DbMasterDataExecutor dbExec;
	DbConfiguration config;

	DataRepos p;
	DataStore<Entity> store;

	Log log = LogFactory.getLog(this.getClass());

	static final String PATH_OF_ROOT = "htdocs2";

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	protected void setUp() throws Exception {

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
		EditableTypeLoader editableLoader = new EditableTypeLoader(new SystemTypeLoader(), new File("apps/system"));
		editableLoader.registerPath(new File("apps/adempiere"));
		editableLoader.loadAllImmediately();
		this.loader = editableLoader;

		String driverClass = "org.postgresql.Driver";
		String dburl = "jdbc:postgresql://127.0.0.1/adempiere";
		String userName = "adempiere";
		String userPassword = "adempiere";

		config = DbConfiguration.getEngine(driverClass, dburl, userName, userPassword);
	}

	protected void tearDown() throws Exception {
		try {
			config.shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final void testInlineType() throws Exception {
		Statement statement;
		ResultSet rs;
		ResultSetMetaData metaData;
		int i = 0;

		/*********************************************************/
		/***** test init *****/
		/*********************************************************/
		// EditableEntity data;
		t = loader.findType("AdClient");
		dbExec = (DbMasterDataExecutor) (DbMasterDataExecutor) config.getPersister(t);

		// ************ Check Database table Layout *************/
		statement = config.conn.createStatement();
		rs = statement.executeQuery(dbExec.builder.builderGetMeta());
		metaData = rs.getMetaData();

		assertEquals(36, metaData.getColumnCount());

		i = 1;
		assertEquals("AD_CLIENT_ID".toUpperCase(), metaData.getColumnName(i).toUpperCase());
		// assertEquals("Varchar".toUpperCase(), metaData.getColumnTypeName(i));
		// assertEquals(60, metaData.getColumnDisplaySize(i));
		// i++;
		// assertEquals("Age".toUpperCase(), metaData.getColumnName(i));
		// assertEquals("BigInt".toUpperCase(), metaData.getColumnTypeName(i));
		// i++;
		// assertEquals("Timestamp_".toUpperCase(), metaData.getColumnName(i));
		// assertEquals("Timestamp".toUpperCase(),
		// metaData.getColumnTypeName(i));

		rs.close();
		// ************ Check Database table Layout *************/
		//
		// try {
		// data = dbExec.get("wangshilian");
		// fail("should error");
		// } catch (RuntimeException e) {
		// }
		//
		// data = new EditableEntity();
		// data.put("PersonName", "wangshilian");
		// data.put("Age", 10L);
		//
		// dbExec.insert(data);
		//
		// data = dbExec.get("wangshilian");
		//
		// assertNotNull(data);
		//
		// dbExec.close();
		// dbExec = null;
		//
		// // ************ Check Database table Layout *************/
		//
		// List<EditableEntity> dataList= dbExec.getAll();
		// assertEquals(3,dataList.size());
		//
		// dbExec.close();
		// dbExec = null;
	}

}

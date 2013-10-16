package http.resource;

import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.DbDataRepos;
import nebula.data.impl.TypeDatastore;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;

public class TypeTemplateResouceTest extends TestCase {
	Log log = LogFactory.getLog(this.getClass());

	Configuration templateConfig;
	Broker<DataStore<Entity>> attributes;
	TypeLoader typeLoader;
	DataRepos dataWareHouse;
	TypeDatastore typeBrokers;

	static final String PATH_OF_ROOT = "htdocs2";

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

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

		dataWareHouse = new DbDataRepos(this.typeBrokers, dbConfiguration);

		// Freemarker
		templateConfig = new Configuration();
		templateConfig.setDefaultEncoding("utf-8");
		templateConfig.setEncoding(Locale.getDefault(), "utf-8");
		templateConfig.setTemplateUpdateDelay(1);
		templateConfig.setNumberFormat("0.####");
		templateConfig.setDirectoryForTemplateLoading(new File(root, "template"));

		this.attributes = dataWareHouse.define(String.class, Entity.class, "Attribute");

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testLoadIssueType() throws Exception {
		String path = "/theme/angularjs/unicorn/Project-Issue-basic-home.html";
		String theme = "angularjs";
		String skin = "unicorn";
		Broker<Type> type = this.typeBrokers.getBroker("Person");
		String specName = null;
		String layoutName = "basic";
		String actionName = "detail.html";

		TypeTemplateResouce resource = new TypeTemplateResouce(templateConfig, dataWareHouse, attributes, path, theme, skin, type, specName, layoutName,
				actionName);

		HttpServletRequest req = mock(HttpServletRequest.class);
		resource.get(req);

		byte[] cache = (byte[]) getPrivateField(resource, AbstractResouce.class, "cache");
		BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cache)));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		long start = System.currentTimeMillis();
		int Max = 100;
		for (int i = 0; i < Max; i++) {
			resource.get(req);
		}
		long end = System.currentTimeMillis();

		System.out.println(" $$$$$$$$ " + (end - start) / Max);

	}

	public final void testLoadPerson() throws Exception {

		String path = "/theme/angularjs/unicorn/Project-Issue-basic-home.html";
		String theme = "angularjs";
		String skin = "unicorn";
		Broker<Type> type = this.typeBrokers.getBroker("Person");
		String specName = null;
		String layoutName = "basic";
		String actionName = "detail.html";

		TypeTemplateResouce resource = new TypeTemplateResouce(templateConfig, dataWareHouse, attributes, path, theme, skin, type, specName, layoutName,
				actionName);

		HttpServletRequest req = mock(HttpServletRequest.class);
		resource.get(req);

		byte[] cache = (byte[]) getPrivateField(resource, AbstractResouce.class, "cache");
		BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cache)));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		long start = System.currentTimeMillis();
		int Max = 100;
		for (int i = 0; i < Max; i++) {
			resource.get(req);
		}
		long end = System.currentTimeMillis();

		System.out.println(" $$$$$$$$ " + (end - start) / Max);

	}

	public final void testLoadOrganization() throws Exception {

		String path = "/theme/angularjs/unicorn/Organization-basic-detail.html";
		String theme = "angularjs";
		String skin = "unicorn";
		Broker<Type> type = this.typeBrokers.getBroker("Organization");
		String specName = null;
		String layoutName = "basic";
		String actionName = "detail.html";

		TypeTemplateResouce resource = new TypeTemplateResouce(templateConfig, dataWareHouse, attributes, path, theme, skin, type, specName, layoutName,
				actionName);

		HttpServletRequest req = mock(HttpServletRequest.class);
		resource.get(req);

		byte[] cache = (byte[]) getPrivateField(resource, AbstractResouce.class, "cache");
		BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cache)));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		long start = System.currentTimeMillis();
		int Max = 10;
		for (int i = 0; i < Max; i++) {
			resource.get(req);
		}
		long end = System.currentTimeMillis();

		System.out.println(" $$$$$$$$ " + (end - start) / Max);

	}

	<T> Object getPrivateField(T o, Class<T> clz, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = clz.getDeclaredField(name);
		field.setAccessible(true);
		return field.get(o);
	}

}

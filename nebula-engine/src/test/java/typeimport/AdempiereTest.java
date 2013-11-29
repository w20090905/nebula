package typeimport;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;
import nebula.lang.EditableTypeLoader;
import nebula.lang.NebulaClassLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdempiereTest extends TestCase {
	Log log = LogFactory.getLog(this.getClass());
	TypeLoader typeLoader;

	static final String PATH_OF_ROOT = "htdocs2";

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	protected void setUp() throws Exception {
		NebulaClassLoader.clear();

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

	}

	public void testLoad() {

		// Type Define locator
		EditableTypeLoader typeLoader = new EditableTypeLoader(new SystemTypeLoader(), new File("apps/system"));
		typeLoader.registerPath(new File("apps/adempiere"));
		typeLoader.loadAllImmediately();
	}

}

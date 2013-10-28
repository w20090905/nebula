package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.DbDataRepos;
import nebula.lang.SystemTypeLoader;

public class DbMasterEntityDataStoreTest extends TestCase {

	DataRepos p;
	DataStore<Entity> store;
	DbConfiguration dbconfig;

	protected void setUp() throws Exception {

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:memory:eh;create = true";
		String username = "user";
		String password = "password";

		dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new DbDataRepos(new TypeDatastore(new SystemTypeLoader()), dbconfig);
		store = p.define(String.class,Entity.class, "Person");
	}

	protected void tearDown() throws Exception {
		dbconfig.shutdown();
		super.tearDown();
	}

	public final void testCreateNew() {
		EditableEntity entity = new EditableEntity();
		assertNull(entity.source);
		assertNull(entity.data);
		assertNotNull(entity.newData);
		assertEquals(0, entity.newData.size());
	}
}

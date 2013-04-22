package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.EntityDbDataPersister;
import nebula.lang.SystemTypeLoader;

public class EntityStoreTest extends TestCase {

	DataPersister<Entity> p;
	DataStore<Entity> store;
	DbConfiguration dbconfig;

	protected void setUp() throws Exception {

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db-test/test-EntityStoreTest;create = true";
		String username = "user";
		String password = "password";

		dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new EntityDbDataPersister(new SystemTypeLoader(), dbconfig);
		store = p.define(Entity.class, "Person").get();
	}

	protected void tearDown() throws Exception {
		p.unload();
		dbconfig.shutdown();
		super.tearDown();
	}

	public final void testCreateNew() {
		EditableEntity entity = (EditableEntity) store.createNew();
		assertNull(entity.source);
		assertNull(entity.data);
		assertNotNull(entity.newData);
		assertEquals(0, entity.newData.size());
	}
}

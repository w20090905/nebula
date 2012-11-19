package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.PersistenceDB;
import nebula.lang.SystemTypeLoader;

public class EntityStoreTest extends TestCase {

	Persistence<Entity> p;
	Store<Entity> store;

	protected void setUp() throws Exception {

		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db/test-entityStore2;create = true";
		String username = "user";
		String password = "password";

		DbConfiguration dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new PersistenceDB(new SystemTypeLoader(), dbconfig);
		store = p.define(Entity.class, "Person");
	}

	protected void tearDown() throws Exception {
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

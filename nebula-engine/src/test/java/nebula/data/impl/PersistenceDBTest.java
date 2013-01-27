package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.lang.SystemTypeLoader;

public class PersistenceDBTest extends TestCase {

	EntityDbDataPersister p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db/PersistenceDBTest;create = true";
		String username = "user";
		String password = "password";

		DbConfiguration dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new EntityDbDataPersister(new SystemTypeLoader(), dbconfig);

		store = p.define(Entity.class, "Person").get();
		store.clear();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDefine() {
		assertNotNull(store);
		assertEquals("Person", store.getID());

		Entity v = store.createNew();
		assertNotNull(v);

		v.put("Name", "wangshilian");

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		p.flush();

		assertEquals(true, v.isDirty());
		// assertEquals("wangshilian", v.get("ID"));
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals("wangshilian", store.get("wangshilian").get("ID"));

		v.put("Height", 120L);

		assertEquals(true, v.isDirty());
		assertEquals(120L, v.get("Height"));
		assertEquals(null, store.get("wangshilian").get("Height"));

		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals(120L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		v.put("Height", 180L);

		assertEquals(true, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		p.clearChanges();
		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(180L, store.get("wangshilian").get("Height"));
	}

	// public final void testRemove() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// public final void testTransaction() {
	// fail("Not yet implemented"); // TODO
	// }
}

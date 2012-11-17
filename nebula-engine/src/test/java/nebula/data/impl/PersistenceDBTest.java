package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.EntityStoreDB;
import nebula.data.impl.PersistenceDB;
import nebula.db.DbConfiguration;
import nebula.lang.SystemTypeLoader;

public class PersistenceDBTest extends TestCase {

	PersistenceDB p;
	EntityStoreDB store;

	protected void setUp() throws Exception {
		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:db/PersistenceDBTest;create = true";
		String username = "user";
		String password = "password";

		DbConfiguration dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new PersistenceDB(new SystemTypeLoader(), dbconfig);

		store = (EntityStoreDB) p.define(Entity.class, "Person");
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
		assertEquals("wangshilian", store.load("wangshilian").get("ID"));

		v.put("length", 120);

		assertEquals(true, v.isDirty());
		assertEquals(120, v.get("length"));
		assertEquals(null, store.load("wangshilian").get("length"));

		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals(120, v.get("length"));
		assertEquals(120, store.load("wangshilian").get("length"));

		v.put("length", 180);

		assertEquals(true, v.isDirty());
		assertEquals(180, v.get("length"));
		assertEquals(120, store.load("wangshilian").get("length"));

		p.clearChanges();
		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals(180, v.get("length"));
		assertEquals(120, store.load("wangshilian").get("length"));

		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals(180, v.get("length"));
		assertEquals(180, store.load("wangshilian").get("length"));
	}

	// public final void testRemove() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// public final void testTransaction() {
	// fail("Not yet implemented"); // TODO
	// }
}

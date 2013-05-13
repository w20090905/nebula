package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.InMemoryDataPersister;
import nebula.lang.SystemTypeLoader;

public class InMemoryPersisterTest extends TestCase {

	DataPersister<Entity> p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		p = new InMemoryDataPersister(new SystemTypeLoader());
		store = p.define(String.class, Entity.class, "Person").get();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDefine() {
		store = p.define(String.class, Entity.class, "Person").get();
		assertNotNull(store);
		// assertEquals("Person", store.getID());

		Entity v = new EditableEntity();
		assertNotNull(v);

		v.put("Name", "wangshilian");

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		// assertEquals(null, ((EditableEntity)v).source);

		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		// assertEquals(null, ((EditableEntity)v).source);

		store.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get("ID"));
		assertEquals("wangshilian", store.get("wangshilian").get("ID"));

		v.put("length", "120");

		assertEquals(true, v.isDirty());
		assertEquals("120", v.get("length"));
		assertEquals(null, store.get("wangshilian").get("length"));

		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals("120", v.get("length"));
		assertEquals("120", store.get("wangshilian").get("length"));

		v.put("length", "180");

		assertEquals(true, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("120", store.get("wangshilian").get("length"));

		p.clearChanges();
		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("120", store.get("wangshilian").get("length"));

		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("180", store.get("wangshilian").get("length"));
	}

	// public final void testRemove() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// public final void testTransaction() {
	// fail("Not yet implemented"); // TODO
	// }
}

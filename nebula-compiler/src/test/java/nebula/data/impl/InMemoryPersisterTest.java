package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.DefaultDataRepos;
import nebula.lang.SystemTypeLoader;

public class InMemoryPersisterTest extends TestCase {

	DataRepos p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		p = new DefaultDataRepos(new TypeDatastore(new SystemTypeLoader()));
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

		store.flush();

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		// assertEquals(null, ((EditableEntity)v).source);

		store.add(v);
		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get(Entity.PRIMARY_KEY));
		assertEquals("wangshilian", store.get("wangshilian").get(Entity.PRIMARY_KEY));

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

		store.clearChanges();
		store.flush();

		assertEquals(true, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("120", store.get("wangshilian").get("length"));

		store.add(v);
		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("180", store.get("wangshilian").get("length"));
	}

}

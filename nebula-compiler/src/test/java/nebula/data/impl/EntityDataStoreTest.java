package nebula.data.impl;

import java.util.List;

import junit.framework.TestCase;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.DefaultDataRepos;
import nebula.lang.SystemTypeLoader;

public class EntityDataStoreTest extends TestCase {

	DataRepos p;
	DataStore<Entity> store;

	protected void setUp() throws Exception {
		p = new DefaultDataRepos(new TypeDatastore(new SystemTypeLoader()));
		store = p.define(String.class,Entity.class, "Person").get();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// public final void testCreateNew() {
	// Entity entity = store.createNew();
	// assertNotNull(entity);
	// }

	public final void testAdd() {
		Entity v = new EditableEntity();
		v.put("Name", "wangshilian");
		store.add(v);
		store.flush();
		assertEquals(1, store.listAll().size());
	}

	public final void testLoad() {
		Entity v = new EditableEntity();
		v.put("Name", "wangshilian");
		store.add(v);

		Entity v1 = store.get("wangshilian");
		assertNull(v1);

		store.flush();

		v = store.get("wangshilian");
		assertNotNull(v);
		assertEquals(1, store.listAll().size());
		assertEquals("wangshilian", v.getID());

		try {
			v.put("length", "180");
		} catch (RuntimeException e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}

		v = v.editable();

		v.put("length", "180");

		List<Entity> list = store.listAll();
		assertEquals(1, list.size());
		Entity e1 = list.get(0);
		assertEquals("wangshilian", e1.getID());

		v = new EditableEntity();
		v.put("Name", "test");
		store.add(v);
		store.flush();

		list = store.listAll();
		assertEquals(2, list.size());
		Entity e2 = list.get(1);
		assertEquals("test", e2.getID());
	}
}

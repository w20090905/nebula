package nebula.data;

import java.util.List;

import junit.framework.TestCase;
import nebula.data.impl.PersistenceMem;
import nebula.lang.SystemTypeLoader;

public class EntityStoreTest extends TestCase {

	Persistence<Entity> p;
	Store<Entity> store;

	protected void setUp() throws Exception {
		p = new PersistenceMem(new SystemTypeLoader());
		store = p.define(Entity.class, "Person");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testCreateNew() {
		Entity entity = store.createNew();
		assertNotNull(entity);
	}

	public final void testAdd() {
		Entity v = store.createNew();
		v.put("ID", "wangshilian");
		store.add(v);
		store.flush();
		assertEquals(1, store.all().size());
	}

	public final void testLoad() {
		Entity v = store.createNew();
		v.put("Name", "wangshilian");
		store.add(v);

		Entity v1 = store.load("wangshilian");
		assertNull(v1);

		store.flush();

		v = store.load("wangshilian");
		assertNotNull(v);
		assertEquals(1, store.all().size());
		assertEquals("wangshilian", v.getID());


		try{
			v.put("length", "180");
		}catch (RuntimeException e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}

		v = v.editable();
		
		v.put("length", "180");		
		
		List<Entity> list = store.all();
		assertEquals(1, list.size());
		Entity e1 = list.get(0);
		assertEquals("wangshilian", e1.getID());

		v = store.createNew();
		v.put("Name", "test");
		store.add(v);
		store.flush();

		list = store.all();
		assertEquals(2, list.size());
		Entity e2 = list.get(1);
		assertEquals("test", e2.getID());
	}

	// public final void testFindBy() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// public final void testRemove() {
	// fail("Not yet implemented"); // TODO
	// }
}

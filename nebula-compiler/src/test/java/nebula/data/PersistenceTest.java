package nebula.data;

import junit.framework.TestCase;
import nebula.data.mem.PersistenceMem;
import nebula.lang.SystemTypeLoader;

public class PersistenceTest extends TestCase {

	Persistence<Entity> p;
	Store<Entity> store;

	protected void setUp() throws Exception {
		p = new PersistenceMem(new SystemTypeLoader());
		store = p.define(Entity.class, "Person");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDefine() {
		store = p.define(Entity.class, "Person");
		assertNotNull(store);
		assertEquals("Person", store.getID());

		Entity v =  store.createNew();
		assertNotNull(v);

		v.put("Name", "wangshilian");

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
//		assertEquals(null, ((EditableEntity)v).source);

		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
//		assertEquals(null, ((EditableEntity)v).source);

		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get("ID"));
		assertEquals("wangshilian", store.load("wangshilian").get("ID"));

		v.put("length", "120");

		assertEquals(true, v.isDirty());
		assertEquals("120", v.get("length"));
		assertEquals(null, store.load("wangshilian").get("length"));

		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals("120", v.get("length"));
		assertEquals("120", store.load("wangshilian").get("length"));

		v.put("length", "180");

		assertEquals(true, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("120", store.load("wangshilian").get("length"));

		p.clearChanges();
		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("120", store.load("wangshilian").get("length"));
		
		p.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("180", v.get("length"));
		assertEquals("180", store.load("wangshilian").get("length"));		
	}

//	public final void testRemove() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testTransaction() {
//		fail("Not yet implemented"); // TODO
//	}
}

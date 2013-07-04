package nebula.data2;

import junit.framework.TestCase;

public class DatatstoreTest extends TestCase {

	Datatstore datastore;

	protected void setUp() throws Exception {
		datastore = new DatatstoreExImp();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testGet() {
		Kind<Entity> k = datastore.get("Type", Entity.class);

		EditableEntity edit = new EditableEntity(k);
		edit.put("name", "wangshilian");
		edit.put("age", 10);

		k.put(edit);

		Entity person = k.get("wangshilian");
		assertNotNull(person);

		person = k.get("wangshilian");
		assertNotNull(person);

		assertEquals(10, person.get("age"));

		edit.put("name", "wangshilian");
		k.put(edit);
	}

	public final void testFlush() {
		fail("Not yet implemented"); // TODO
	}

}

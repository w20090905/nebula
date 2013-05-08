package nebula.data.impl;

import nebula.data.Entity;
import junit.framework.TestCase;

public class EntityFilterTest extends TestCase {
	EntityFilter filter;
	protected void setUp() throws Exception {
		super.setUp();
		filter = new EntityFilter();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testMatch() {
		filter.C("wangshilian").eqF("name").and(filter.C(19).eqF("age")).finish();
		
		Entity person = new EditableEntity(null);
		person.put("name", "wangshilian");
		person.put("age", 19);
		
		boolean result = filter.apply(person);
		assertTrue(result);
		
		person = new EditableEntity(null);
		person.put("name", "wangshilian");
		person.put("age", 13);
		
		result = filter.apply(person);
		assertFalse(result);
	}

}

package nebula.data.impl;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.lang.SystemTypeLoader;

public class EntityStoreTest extends TestCase {

	Persistence<Entity> p;
	Store<Entity> store;

	protected void setUp() throws Exception {
		p = new PersistenceMem(new SystemTypeLoader());
		store = p.define(Entity.class, "test");
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

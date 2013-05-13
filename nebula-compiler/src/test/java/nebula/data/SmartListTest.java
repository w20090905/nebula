package nebula.data;

import junit.framework.TestCase;
import nebula.data.Classificator;
import nebula.data.Entity;
import nebula.data.Filter;
import nebula.data.SmartList;
import nebula.data.impl.EditableEntity;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;

public class SmartListTest extends TestCase {
	SmartList<String,Entity> list;

	protected void setUp() throws Exception {
		super.setUp();
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String)from.get("name");
			}
		});
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDelegate() {
		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		assertEquals(1, list.size());
		list.remove(entity);
		assertEquals(0, list.size());
	}

	public final void testSmartList() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});
	}

	public void testGetString() throws Exception {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		entity.put("v", "1");
		list.add(entity);
		
		Entity get = list.get("wangshilian");
		assertEquals("1", get.get("v"));

	}
	public final void testIndexBy() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String)from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		Classificator<Integer, Entity> classificator = list.liveClassify(new Function<Entity, Integer>() {
			@Override
			public Integer apply(Entity from) {
				return ((String) from.get("name")).length();
			}
		});

		assertEquals("11", Joiner.on(" , ").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		assertEquals("4,11", Joiner.on(",").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		assertEquals(1, classificator.getData(4).size());
		assertEquals("1234", classificator.getData(4).get(0).get("name"));
	}

	public final void testFilterBy() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		Filter<Entity> filter = list.liveFilter(new Predicate<Entity>() {
			@Override
			public boolean apply(Entity input) {
				return ((String) input.get("name")).length() > 5;
			}
		});

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "123456");
		list.add(entity);

		assertEquals(2, filter.get().size());

		list.remove(entity);

		assertEquals(1, filter.get().size());
	}

	public final void testAddIntV() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(0, entity);

		Classificator<Integer, Entity> classificator = list.liveClassify(new Function<Entity, Integer>() {
			@Override
			public Integer apply(Entity from) {
				return ((String) from.get("name")).length();
			}
		});

		assertEquals("11", Joiner.on(" , ").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(0, entity);

		assertEquals("4,11", Joiner.on(",").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		assertEquals(1, classificator.getData(4).size());
		assertEquals("1234", classificator.getData(4).get(0).get("name"));
	}

	public final void testAddV() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		Classificator<Integer, Entity> classificator = list.liveClassify(new Function<Entity, Integer>() {
			@Override
			public Integer apply(Entity from) {
				return ((String) from.get("name")).length();
			}
		});

		assertEquals("11", Joiner.on(" , ").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		assertEquals("4,11", Joiner.on(",").join(classificator.getClassifications()));

		assertEquals(1, classificator.getData(11).size());
		assertEquals("wangshilian", classificator.getData(11).get(0).get("name"));

		assertEquals(1, classificator.getData(4).size());
		assertEquals("1234", classificator.getData(4).get(0).get("name"));
	}

	public final void testRemoveInt() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		Filter<Entity> filter = list.liveFilter(new Predicate<Entity>() {
			@Override
			public boolean apply(Entity input) {
				return ((String) input.get("name")).length() > 5;
			}
		});

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "123456");
		list.add(entity);

		assertEquals(2, filter.get().size());

		list.remove(0);

		assertEquals(1, filter.get().size());
	}

	public final void testRemoveAllCollectionOfQ() {
		try {
			list = new SmartList<String,Entity>(new Function<Entity, String>() {
				@Override
				public String apply(Entity from) {
					return (String) from.get("name");
				}
			});

			list.removeAll(list);
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	public final void testRemoveObject() {
		list = new SmartList<String,Entity>(new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return (String) from.get("name");
			}
		});

		Entity entity = new EditableEntity();
		entity.put("name", "wangshilian");
		list.add(entity);

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		Filter<Entity> filter = list.liveFilter(new Predicate<Entity>() {
			@Override
			public boolean apply(Entity input) {
				return ((String) input.get("name")).length() > 5;
			}
		});

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "1234");
		list.add(entity);

		assertEquals(1, filter.get().size());

		entity = new EditableEntity();
		entity.put("name", "123456");
		list.add(entity);

		assertEquals(2, filter.get().size());

		list.remove(entity);

		assertEquals(1, filter.get().size());
	}

}

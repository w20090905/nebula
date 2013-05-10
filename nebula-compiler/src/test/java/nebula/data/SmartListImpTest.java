package nebula.data;

import junit.framework.TestCase;
import nebula.data.Filter;
import nebula.data.SmartList;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class SmartListImpTest extends TestCase {
	SmartList<Person> list = null;
	String name = "test";

	class Person implements Timable {
		String name;
		int age;
		int height;
		@Override
		public long getLastModified() {
			return 0;
		}
		
	}

	Person p = null;

	protected void setUp() throws Exception {
		list = new SmartList<Person>(new Function<Person, String>() {
			@Override
			public String apply(Person data) {
				return data.name;
			}
		});
		p = new Person();
		p.name = "wangshilian";
		p.age = 10;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testClear() {
		list.add(p);
		assertEquals(1, list.size());
		list.clear();
		assertEquals(0, list.size());

	}

	public final void testGetString() {
		assertEquals(0, list.size());
		list.add(p);
		assertEquals(1, list.size());
		Person w = list.get("wangshilian");
		assertEquals(p.name, w.name);
	}

	public final void testAddE() {
		assertEquals(0, list.size());
		list.add(p);
		assertEquals(1, list.size());
	}

	public final void testRemoveInt() {
		assertEquals(0, list.size());
		list.add(p);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}

	public final void testQuery() {
		assertEquals(0, list.size());
		list.add(p);
		p = new Person();
		p.name = "wang12";
		p.age = 10;
		list.add(p);

		p = new Person();
		p.name = "wang13";
		p.age = 12;
		list.add(p);

		p = new Person();
		p.name = "wang14";
		p.age = 12;
		list.add(p);

		p = new Person();
		p.name = "wang15";
		p.age = 13;
		list.add(p);

		assertEquals(5, list.size());
		Filter<Person> result = list.liveFilter(new Predicate<Person>() {
			@Override
			public boolean apply(Person v) {
				return v.age == 12;
			}
		});
		assertEquals(2, result.get().size());
		list.remove(1);
		list.remove(0);

		assertEquals(2, result.get().size());

		assertEquals(3, list.size());

	}

	public final void testSave() {
		p.age = 1000;
		assertEquals(0, list.size());

		list.add(p);
		assertEquals(1, list.size());
		Person w = list.get("wangshilian");
		assertEquals(1000, w.age);

		p.age = 4321;

		list.add(p);
		w = list.get("wangshilian");
		assertEquals(4321, w.age);
	}

}

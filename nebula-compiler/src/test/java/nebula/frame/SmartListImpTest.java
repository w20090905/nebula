package nebula.frame;

import nebula.Filter;
import nebula.IDAdapter;
import nebula.SmartList;
import junit.framework.TestCase;

public class SmartListImpTest extends TestCase {
	SmartListImp<Person> list = null;
	String name = "test";

	class Person {
		String name;
		int age;
	}

	Person p = null;

	protected void setUp() throws Exception {
		list = new SmartListImp<Person>(name, new IDAdapter<Person>() {
			@Override
			public String getID(Person data) {
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

	public final void testGetName() {
		assertEquals(name, list.getName());
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

	public final void testIndexOfByKey() {
		assertEquals(0, list.size());
		list.add(p);
		assertEquals(1, list.size());
		int i= list.indexOfByKey(p);
		assertEquals(0, i);
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
		SmartList<Person> result =  list.query(new Filter<SmartListImpTest.Person>() {			
			@Override
			public boolean match(Person v) {
				return v.age == 12;
			}
		});
		assertEquals(2, result.size());
		result.remove(1);
		result.remove(0);
		
		assertEquals(0, result.size());
		
		assertEquals(5, list.size());
		
		
	}

	public final void testSave() {
		p.age = 1000;		
		assertEquals(0, list.size());
		
		list.add(p);
		assertEquals(1, list.size());
		Person w = list.get("wangshilian");
		assertEquals(1000, w.age);
		
		p.age = 4321;
		list.save(p);
		w = list.get("wangshilian");
		assertEquals(4321, w.age);		
	}

}

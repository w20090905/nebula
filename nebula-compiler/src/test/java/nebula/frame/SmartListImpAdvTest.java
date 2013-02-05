package nebula.frame;

import nebula.Filter;
import nebula.IDAdapter;
import nebula.SmartList;
import junit.framework.TestCase;

public class SmartListImpAdvTest extends TestCase {
	SmartListImp<Person> list = null;
	String name = "test";

	class Person {
		String name;
		int age;
		int height;
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
		SmartList<Person> result =  list.query(new Filter<SmartListImpAdvTest.Person>() {			
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
}

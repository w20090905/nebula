package util;

import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

public class InheritHashMapTest extends TestCase {
	
	InheritHashMap maps =null;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testSize() {
		InheritHashMap parent = new InheritHashMap();
		parent.put("a", "a");
		maps = new InheritHashMap(parent);
		assertEquals(1,maps.size());
		maps.put("a", "8");
		assertEquals(1,maps.size());
		maps.put("b", "b");
		assertEquals(2,maps.size());
		maps.put("c", "c");
		assertEquals(3,maps.size());	
	}

	public final void testIsEmpty() {
		InheritHashMap parent = new InheritHashMap();
		maps = new InheritHashMap(parent);
		assertTrue(maps.isEmpty());		
	}

	public final void testGetObject() {
		InheritHashMap parent = new InheritHashMap();
		parent.put("a", "a");
		maps = new InheritHashMap(parent);
		maps.put("b", "b");
		maps.put("c", "c");
		assertEquals("b", maps.get("b"));
		assertFalse(maps.isEmpty());
	}

	public final void testGetNames() {
		InheritHashMap parent = new InheritHashMap();
		parent.put("a", "a");
		maps = new InheritHashMap(parent);
		maps.put("b", "b");
		maps.put("c", "c");
		
		Set<String> names = maps.getNames();
		Iterator<String> i =   names.iterator();
		assertEquals(3, names.size());
		assertEquals("b", i.next());
		assertEquals("c", i.next());
		assertEquals("a", i.next());
	}
	

	public final void testGetNameDup() {
		InheritHashMap parent = new InheritHashMap();
		parent.put("a", "a");
		maps = new InheritHashMap(parent);
		maps.put("a", "a1");
		maps.put("b", "b");
		maps.put("c", "c");
		
		Set<String> names = maps.getNames();
		Iterator<String> i =   names.iterator();
		assertEquals(3, names.size());
		assertEquals("b", i.next());
		assertEquals("c", i.next());
		assertEquals("a", i.next());
		
		assertEquals("a1",maps.get("a"));
	}


}

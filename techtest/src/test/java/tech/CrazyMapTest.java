package tech;

import junit.framework.TestCase;

public class CrazyMapTest extends TestCase {

	public void testCrazyMap() {
	}

	public void testAddKey() {
		CrazyMap map = CrazyMap.newMap();
		map = map.addKey("name");
		map = map.addKey("age");
		map = map.addKey("height");
		map = map.addKey("name");
		map = map.addKey("name");
	}

	public void testNewMap() {
	}

	public void testNewMapStringArray() {
//	    CrazyMap map = CrazyMap.newMap("name","sex","age");
	}

	public void testCode() {
	}

	public void testUpgrade() {
	}

	public void testPut() {
	}

	public void testGet() {
	}

	public void testShadow() {
	}

}

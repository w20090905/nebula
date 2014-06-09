package nebula.data.db;

import junit.framework.TestCase;

public class ATest extends TestCase {
	A a;

	protected void setUp() throws Exception {
		a = new A();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSay() throws Exception {
		a.say();
	}

}

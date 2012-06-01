package nebula.lang;

import nebula.lang.SystemTypeLoader;
import junit.framework.TestCase;

public class SystemBootstrapTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoad() {
		SystemTypeLoader bot = new SystemTypeLoader();
		assertEquals("Age",  bot.findType("Age").getName());
		assertEquals("Length", bot.findType("Length").getName());
		assertEquals("Person", bot.findType("Person").getName());
		assertEquals("Company", bot.findType("Company").getName());
		assertEquals("String", bot.findType("String").getName());
	}

}

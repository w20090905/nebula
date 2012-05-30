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
		assertEquals("Age", bot.types.get("Age").getName());
		assertEquals("Length", bot.types.get("Length").getName());
		assertEquals("Person", bot.types.get("Person").getName());
		assertEquals("Company", bot.types.get("Company").getName());
		assertEquals(null, bot.types.get("String"));
		assertEquals("String", bot.findType("String").getName());
	}

}

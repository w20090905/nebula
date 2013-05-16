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

		Type type = bot.findType("Type");
		assertNotNull(type);
		
		type = bot.findType("Master");
		assertNotNull(type);
//		assertEquals("master", type.getStandalone().name().toLowerCase());
		assertEquals("basic", type.attrs.get("Style"));
		
		type = bot.findType("Transaction");
		assertNotNull(type);
//		assertEquals("transaction",  type.getStandalone().name().toLowerCase());
		assertEquals("basic", type.attrs.get("Style"));
		
		type = bot.findType("Attribute");
		assertNotNull(type);
//		assertEquals("master",  type.getStandalone().name().toLowerCase());
		assertEquals("compact", type.attrs.get("Style"));				
	}

}

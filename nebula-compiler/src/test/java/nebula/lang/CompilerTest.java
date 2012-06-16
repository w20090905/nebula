package nebula.lang;

import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import junit.framework.TestCase;

public class CompilerTest extends TestCase {

	Compiler compiler;
	protected void setUp() throws Exception {
		super.setUp();
		compiler = new Compiler(new SystemTypeLoader());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoad() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	Name;" +
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(1, type.fields.size());
		assertEquals("Name", type.fields.get(0).name);
	}

//	public void testLoadFromFile() {
//		//@formatter:off
//		String text = "" +
//				"type Person { " +
//				"	Name;" +
//				"};";
//		//@formatter:on		
//		
//		Type type = compiler.loadFromFile("target\\Simple.nebula");
//
//		assertEquals("Person", type.name);
//
//		assertEquals(1, type.fields.size());
//		assertEquals("Name", type.fields.get(0).name);
//	}

}

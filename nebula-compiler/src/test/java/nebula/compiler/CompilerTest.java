package nebula.compiler;

import junit.framework.TestCase;

public class CompilerTest extends TestCase {

	Compiler compiler;
	protected void setUp() throws Exception {
		super.setUp();
		compiler = new Compiler();
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

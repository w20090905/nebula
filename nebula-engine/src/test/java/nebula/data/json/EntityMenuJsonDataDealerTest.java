package nebula.data.json;

import java.io.StringReader;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;

public class EntityMenuJsonDataDealerTest extends TestCase {
	TypeLoaderForTest loader;
	Type type;
	JsonFactory factory;
	EntitySerializer entityMerger;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new JsonFactory();
		loader = new TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void test_SimpleType_readJsonParserString() throws Exception {			

		// Type For Test
		//@formatter:off
		String textRef = "" +
				"type Icon {" +
				"	!Name;" +
				"	IconGroup Attr;" + 
				"};";
		String text = "" +
				"type MenuBar {" +
				"	!Name;" + 
				"	Menu[]{" +
				"		!Name;" +
				"		Icon;" +
				"		URL;" +
				"	};" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(textRef)).get(0);
		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"Name\":\"TestMenuBar_Name\",\"Menus_new\":{\"Name\":\"SubMenu001Name001\",\"IconName\":\"icon-angle-left\",\"URL\":\"ddsfs\"}}";

		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		Entity data = entityMerger.readFrom(null, jsonParser);
		assertNotNull(data);

		assertEquals("TestMenuBar_Name", data.get("Name"));
	}
}

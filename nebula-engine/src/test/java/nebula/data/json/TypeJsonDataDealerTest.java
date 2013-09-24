package nebula.data.json;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class TypeJsonDataDealerTest extends TestCase {
	TypeLoaderForTest loader;
	Type type;
	JsonFactory factory;
	TypeSerializer dataDealer;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new JsonFactory();
		loader = new TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testTypeJsonDataDealer() throws Exception {
		dataDealer = new TypeSerializer();

	}

	public final void testReadFromJsonParserString() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new TypeSerializer();
		StringReader in = new StringReader("");
		JsonParser jsonParser = factory.createJsonParser(in);
		try {
			dataDealer.readFrom(null, jsonParser);
			fail("Should throw new exception");
		} catch (RuntimeException e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}

	}

	public final void testWriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new TypeSerializer();
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		dataDealer.stringifyTo(type, gen);
		gen.flush();

		assertEquals(
				"{\"Name\":\"Person\",\"SuperType\":\"Master\",\"Standalone\":\"Master\",\"Fields\":[{\"Name\":\"PersonName\",\"Key\":true,\"Unique\":true,\"Core\":false,\"Array\":false,\"TypeName\":\"Name\",\"Refer\":\"ByVal\",\"NameAlias\":{},\"Attrs\":{\"FormatType\":\"text\",\"MaxLength\":\"60\",\"ShouldBeLeader\":\"ShouldBeLeader\"}},{\"Name\":\"Age\",\"Key\":false,\"Unique\":false,\"Core\":false,\"Array\":false,\"TypeName\":\"Age\",\"Refer\":\"ByVal\",\"NameAlias\":{},\"Attrs\":{\"FormatType\":\"numeric\",\"InputSize\":\"small\",\"Max\":\"160\",\"MaxLength\":\"3\",\"Min\":\"0\",\"Precision\":\"10\",\"Scale\":\"2\"}}],\"NameAlias\":{},\"Attrs\":{\"Layout\":\"Basic\"},\"Mutable\":false,\"Code\":\"type Person {\\t!PersonName Name;\\tAge;};\\n\"}",
				out.toString());
		System.out.println(out.toString());
	}

}

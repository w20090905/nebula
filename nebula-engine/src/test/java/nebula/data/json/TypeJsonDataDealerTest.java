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
	TypeJsonDataDealer dataDealer;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new JsonFactory();
		loader = new TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testTypeJsonDataDealer() throws Exception {
		dataDealer = new TypeJsonDataDealer();

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

		dataDealer = new TypeJsonDataDealer();
		StringReader in = new StringReader("");
		JsonParser jsonParser = factory.createJsonParser(in);
		try {
			dataDealer.readFrom(null, jsonParser);
			fail("Should throw new exception"); // TODO
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

		dataDealer = new TypeJsonDataDealer();
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		dataDealer.stringifyTo(type, gen);
		gen.flush();

		assertEquals(
				"{\"name\":\"Person\",\"parent\":\"Master\",\"standalone\":\"Master\","
						+ "\"fields\":["
						+ "{\"name\":\"PersonName\",\"isKey\":true,\"isCore\":false,\"isArray\":false,\"typeName\":\"Name\","
						+ "\"attrs\":{\"formatType\":\"text\",\"maxLength\":\"60\"}},"
						+ "{\"name\":\"Age\",\"isKey\":false,\"isCore\":false,\"isArray\":false,\"typeName\":\"Age\","
						+ "\"attrs\":{\"formatType\":\"numeric\",\"inputSize\":\"small\",\"max\":\"160\",\"maxLength\":\"3\",\"min\":\"0\",\"precision\":\"10\",\"scale\":\"2\"}}],"
						+ "\"attrs\":{},\"mutable\":false,"
						+ "\"code\":\"type Person {\\t!PersonName Name;\\tAge;};\\n\"}", out.toString());
		System.out.println(out.toString());
	}

}

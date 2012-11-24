package nebula.data.json;

import java.io.StringReader;

import junit.framework.TestCase;

import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class JsonEntityHelperProviderTest extends TestCase {

	TypeLoaderForTest loader;
	Type t;
	JsonFactory factory;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new JsonFactory();
		loader = new TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRead() throws Exception {
		
		// @for
		String json = "" +
				"{" + 
				"	\"name\":\"wangshilian\"," + 
				"	\"age\":[13,14]," + 
				"	\"values\":[" + 
				"		{\"name\":\"wangshilian\"}," + 
				"		{\"name\":\"wangshilian\"}" + 
				"	]" + 
				"}";

		JsonToken t = null;

		JsonParser parser = factory.createJsonParser(new StringReader(json));
		t = parser.nextToken();
		assertEquals(JsonToken.START_OBJECT, t);

		t = parser.nextToken();
		assertEquals(JsonToken.FIELD_NAME, t);
		assertEquals("name", parser.getCurrentName());
		t = parser.nextToken();
		assertEquals(JsonToken.VALUE_STRING, t);
		assertEquals("wangshilian", parser.getText());

		t = parser.nextToken();
		assertEquals(JsonToken.FIELD_NAME, t);
		assertEquals("age", parser.getCurrentName());

		t = parser.nextToken();
		assertEquals(JsonToken.START_ARRAY, t);
		
		t = parser.nextToken();
		assertEquals(JsonToken.VALUE_NUMBER_INT, t);
		assertEquals(13, parser.getIntValue());
		
		t = parser.nextToken();
		assertEquals(JsonToken.VALUE_NUMBER_INT, t);
		assertEquals(14, parser.getIntValue());
		
		t = parser.nextToken();
		assertEquals(JsonToken.END_ARRAY, t);

		t = parser.nextToken();
		assertEquals(JsonToken.FIELD_NAME, t);
		assertEquals("values", parser.getCurrentName());

		t = parser.nextToken();
		assertEquals(JsonToken.START_ARRAY, t);

		t = parser.nextToken();
		assertEquals(JsonToken.START_OBJECT, t);

		t = parser.nextToken();
		assertEquals(JsonToken.FIELD_NAME, t);
		assertEquals("name", parser.getCurrentName());
		t = parser.nextToken();
		assertEquals(JsonToken.VALUE_STRING, t);
		assertEquals("wangshilian", parser.getText());

		t = parser.nextToken();
		assertEquals(JsonToken.END_OBJECT, t);

		t = parser.nextToken();
		assertEquals(JsonToken.START_OBJECT, t);

		t = parser.nextToken();
		assertEquals(JsonToken.FIELD_NAME, t);
		assertEquals("name", parser.getCurrentName());
		t = parser.nextToken();
		assertEquals(JsonToken.VALUE_STRING, t);
		assertEquals("wangshilian", parser.getText());

		t = parser.nextToken();
		assertEquals(JsonToken.END_OBJECT, t);
		
		t = parser.nextToken();
		assertEquals(JsonToken.END_ARRAY, t);

		parser.close();

	}
}

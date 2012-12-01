package nebula.data.json;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import junit.framework.TestCase;

public class EntityJsonDataDealerTest extends TestCase {
	TypeLoaderForTest loader;
	Type type;
	JsonFactory factory;
	EntityJsonDataDealer dataDealer;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new JsonFactory();
		loader = new TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testTypeJsonDataDealer() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);
		dataDealer = new EntityJsonDataDealer(type);
	}

	public final void test_SimpleType_ReadFromJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		dataDealer = new EntityJsonDataDealer(type);

		// Type For Test
		//@formatter:off
		String txtData = "" +
				"{" +
				"\"PersonName\":\"wangshilian\"," +
				"\"Age\":12" +
				"}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);
		
		JsonToken token =  jsonParser.nextToken();
		assertEquals(JsonToken.START_OBJECT, token);
		
		Entity entity = dataDealer.readFrom(jsonParser, "root");
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));

	}

	public final void test_SimpleType_WriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new EntityJsonDataDealer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();
		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);

		dataDealer.writeTo("root", entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":12}", out.toString());
		System.out.println(out.toString());
	}
	

	public final void test_SimpleTypeWith_LongArray_ReadFromJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		dataDealer = new EntityJsonDataDealer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":[10,20]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);
		
		JsonToken token =  jsonParser.nextToken();
		assertEquals(JsonToken.START_OBJECT, token);
		
		Entity entity = dataDealer.readFrom(jsonParser, "root");
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		
		@SuppressWarnings("unchecked")
		List<Long> ages = (List<Long>)entity.get("Age");
		
		assertEquals(2, ages.size());
		assertEquals(10L, (long)ages.get(0));
		assertEquals(20L, (long)ages.get(1));
	}

	public final void test_SimpleTypeWith_LongArray_WriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new EntityJsonDataDealer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();
		entity.put("PersonName", "wangshilian");

		List<Long> ages = new ArrayList<>();
		ages.add(10L);
		ages.add(20L);
		entity.put("Age", ages);

		dataDealer.writeTo("root", entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":[10,20]}", out.toString());
		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_StringArray_ReadFromJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName[1..30] Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		dataDealer = new EntityJsonDataDealer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":[\"wangshilian\",\"houyihong\"],\"Age\":[10,20]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		JsonToken token =  jsonParser.nextToken();
		assertEquals(JsonToken.START_OBJECT, token);
		
		Entity entity = dataDealer.readFrom(jsonParser, "root");
		assertNotNull(entity);

		@SuppressWarnings("unchecked")
		List<String> personName = (List<String>)entity.get("PersonName");
		
		assertEquals(2, personName.size());
		assertEquals("wangshilian", (String)personName.get(0));
		assertEquals("houyihong", (String)personName.get(1));
		
		
		@SuppressWarnings("unchecked")
		List<Long> ages = (List<Long>)entity.get("Age");
		
		assertEquals(2, ages.size());
		assertEquals(10L, (long)ages.get(0));
		assertEquals(20L, (long)ages.get(1));
	}
	
	public final void test_SimpleTypeWith_StringArray_WriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName[1..30] Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new EntityJsonDataDealer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		List<String> names = new ArrayList<>();
		names.add("wangshilian");
		names.add("houyihong");

		entity.put("PersonName", names);

		List<Long> ages = new ArrayList<>();
		ages.add(10L);
		ages.add(20L);
		entity.put("Age", ages);

		dataDealer.writeTo("root", entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":[\"wangshilian\",\"houyihong\"],\"Age\":[10,20]}", out.toString());
		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_Entity_ReadFromJsonParserString() throws Exception {

		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" +
				"	Educations{" +
				"		School Name;" +
				"		Year Long;" +
				"	};" + 
				"};";
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		dataDealer = new EntityJsonDataDealer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":12,\"EducationsSchool\":\"Kunming\",\"EducationsYear\":2012}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		JsonToken token =  jsonParser.nextToken();
		assertEquals(JsonToken.START_OBJECT, token);
		
		Entity entity = dataDealer.readFrom(jsonParser, "root");
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));
		assertEquals("Kunming",entity.get("EducationsSchool"));
		assertEquals(2012L,entity.get("EducationsYear"));
	}
	
	
	public final void test_SimpleTypeWith_Entity_WriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" +
				"	Educations{" +
				"		School Name;" +
				"		Year Long;" +
				"	};" + 
				"};";
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new EntityJsonDataDealer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);
		
		
		entity.put("EducationsSchool", "Kunming");
		entity.put("EducationsYear", 2012L);

		dataDealer.writeTo("root", entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":12,\"EducationsSchool\":\"Kunming\",\"EducationsYear\":2012}", out.toString());
		System.out.println(out.toString());
	}
	

	public final void test_SimpleTypeWith_EntityArray_ReadFromJsonParserString() throws Exception {

		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" +
				"	Educations[1..5]{" +
				"		School Name;" +
				"		Year Long;" +
				"	};" + 
				"};";
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		dataDealer = new EntityJsonDataDealer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":[{\"School\":\"Kunming\",\"Year\":1996},{\"School\":\"Hongqi\",\"Year\":1993}]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		JsonToken token =  jsonParser.nextToken();
		assertEquals(JsonToken.START_OBJECT, token);
		
		Entity entity = dataDealer.readFrom(jsonParser, "root");
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));
		
		
		@SuppressWarnings("unchecked")
		List<Entity> educations = (List<Entity>)entity.get("Educations");
		
		assertEquals(2, educations.size());
//		assertEquals(10L, (Entity)educations.get(0));
//		assertEquals(20L, (Entity)educations.get(1));
	}
	

	public final void test_SimpleTypeWith_EntityArray_WriteToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" +
				"	Educations[1..5]{" +
				"		School Name;" +
				"		Year Long;" +
				"	};" + 
				"};";
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		dataDealer = new EntityJsonDataDealer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);

		List<Entity> educations = new ArrayList<>();
		Entity education = new EditableEntity();
		education.put("School", "Kunming");
		education.put("Year", 1996L);
		
		educations.add(education);
		
		education = new EditableEntity();
		education.put("School", "Hongqi");
		education.put("Year", 1993L);
		
		educations.add(education);
		
		entity.put("Educations",educations);

		dataDealer.writeTo("root", entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":[{\"School\":\"Kunming\",\"Year\":1996},{\"School\":\"Hongqi\",\"Year\":1993}]}", out.toString());
		System.out.println(out.toString());
	}
}
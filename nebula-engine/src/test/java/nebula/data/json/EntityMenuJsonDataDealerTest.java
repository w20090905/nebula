package nebula.data.json;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
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
//
//		// Type For Test
//		//@formatter:off
//		String txtIcon = "" +
//				"type Icon {" +
//				"	!Name;" + 
//				"};";
//		String txtType = "" +
//				"type MenuBar {" +
//				"	!Name;" + 
//				"	Menu[]{" +
//				"		!Name;" +
//				"		Icon;" +
//				"		URL;" +
//				"	};" + 
//				"};";
//		//@formatter:on		
//
//		loader.testDefineNebula(new StringReader(txtIcon)).get(0);
//		type = loader.testDefineNebula(new StringReader(txtType)).get(0);
//
//		entityMerger = new EntitySerializer(type);
//
//		// Type For Test
//		//@formatter:off
//		String txtData = "" +
//				"{" +
//				"\"Name\":\"wangshilian\"," +
//				"\"Menu\":[ ]" +
//				"}";
//		//@formatter:on	
//
//		StringReader in = new StringReader(txtData);
//		JsonParser jsonParser = factory.createJsonParser(in);
//
//		Entity entity = entityMerger.readFrom(new EditableEntity(), jsonParser);
//		assertNotNull(entity);
//
//		assertEquals("wangshilian", entity.get("Name"));
//		assertEquals(12L, entity.get("Age"));
	}

	public final void test_SimpleTypeWith_Entity_readJsonParserString() throws Exception {

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

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":{\"School\":\"Kunming\",\"Year\":2012}}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		Entity entity = entityMerger.readFrom(new EditableEntity(), jsonParser);
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));
		assertEquals("Kunming", entity.getEntity("Educations").get("School"));
		assertEquals(2012L,  entity.getEntity("Educations").get("Year"));
	}

	public final void test_SimpleTypeWith_Entity_stringifyToStringObjectJsonGenerator() throws Exception {

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

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);
		
		Entity educations = new EditableEntity();		
		educations.put("School", "Kunming");
		educations.put("Year", 2012L);		
		entity.put("Educations", educations);

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals(
				"{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":{\"School\":\"Kunming\",\"Year\":2012}}",
				out.toString());
		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_EntityArray_readJsonParserString() throws Exception {

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

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":[{\"_idx\":\"0\",\"School\":\"Kunming\",\"Year\":1996},{\"_idx\":\"1\",\"School\":\"Hongqi\",\"Year\":1993}]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		Entity entity = entityMerger.readFrom(null, jsonParser);
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));

		@SuppressWarnings("unchecked")
		List<Entity> educations = (List<Entity>) entity.get("Educations");

		assertEquals(2, educations.size());
		// assertEquals(10L, (Entity)educations.get(0));
		// assertEquals(20L, (Entity)educations.get(1));
	}

	public final void test_SimpleTypeWith_EntityArray_stringifyToStringObjectJsonGenerator() throws Exception {

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

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);

		List<Entity> educations = new ArrayList<Entity>();
		Entity education = new EditableEntity();
		education.put("School", "Kunming");
		education.put("Year", 1996L);

		educations.add(education);

		education = new EditableEntity();
		education.put("School", "Hongqi");
		education.put("Year", 1993L);

		educations.add(education);

		entity.put("Educations", educations);

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals(
				"{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":[{\"School\":\"Kunming\",\"Year\":1996},{\"School\":\"Hongqi\",\"Year\":1993}]}",
				out.toString());
		System.out.println(out.toString());
	}
}

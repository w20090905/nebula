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

public class EntityJsonDataDealerTest extends TestCase {
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

	public final void testTypeJsonDataDealer() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);
		entityMerger = new EntitySerializer(type);
	}

	public final void test_SimpleType_readJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		entityMerger = new EntitySerializer(type);

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

		Entity entity = entityMerger.readFrom(new EditableEntity(), jsonParser);
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));
		assertEquals(12L, entity.get("Age"));

	}

	public final void test_SimpleType_stringifyToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age;" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();
		entity.put("PersonName", "wangshilian");
		entity.put("Age", 12L);

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":12}", out.toString());
		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_LongArray_readJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":\"wangshilian\",\"Age\":[10,20]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		Entity entity = entityMerger.readFrom(new EditableEntity(), jsonParser);
		assertNotNull(entity);

		assertEquals("wangshilian", entity.get("PersonName"));

		@SuppressWarnings("unchecked")
		List<Long> ages = (List<Long>) entity.get("Age");

		assertEquals(2, ages.size());
		assertEquals(10L, (long) ages.get(0));
		assertEquals(20L, (long) ages.get(1));
	}

	public final void test_SimpleTypeWith_LongArray_stringifyToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();
		entity.put("PersonName", "wangshilian");

		List<Long> ages = new ArrayList<Long>();
		ages.add(10L);
		ages.add(20L);
		entity.put("Age", ages);

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":\"wangshilian\",\"Age\":[10,20]}", out.toString());
		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_StringArray_readJsonParserString() throws Exception {

		// Type For Test
		//@formatter:off
		String txtType = "" +
				"type Person {" +
				"	!PersonName[1..30] Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(txtType)).get(0);

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"PersonName\":[\"wangshilian\",\"houyihong\"],\"Age\":[10,20]}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);


		Entity entity = entityMerger.readFrom(new EditableEntity(), jsonParser);
		assertNotNull(entity);

		@SuppressWarnings("unchecked")
		List<String> personName = (List<String>) entity.get("PersonName");

		assertEquals(2, personName.size());
		assertEquals("wangshilian", (String) personName.get(0));
		assertEquals("houyihong", (String) personName.get(1));

		@SuppressWarnings("unchecked")
		List<Long> ages = (List<Long>) entity.get("Age");

		assertEquals(2, ages.size());
		assertEquals(10L, (long) ages.get(0));
		assertEquals(20L, (long) ages.get(1));
	}

	public final void test_SimpleTypeWith_StringArray_stringifyToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		String text = "" +
				"type Person {" +
				"	!PersonName[1..30] Name;" + 
				"	Age[1..10];" + 
				"};";
		//@formatter:on		

		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();

		List<String> names = new ArrayList<String>();
		names.add("wangshilian");
		names.add("houyihong");

		entity.put("PersonName", names);

		List<Long> ages = new ArrayList<Long>();
		ages.add(10L);
		ages.add(20L);
		entity.put("Age", ages);

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals("{\"PersonName\":[\"wangshilian\",\"houyihong\"],\"Age\":[10,20]}", out.toString());
		System.out.println(out.toString());
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
		assertEquals(2012L, entity.getEntity("Educations").get("Year"));
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
		Entity Educations = new EditableEntity();
		
		Educations.put("School", "Kunming");
		Educations.put("Year", 2012L);
		entity.put("Educations", Educations);

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
				"{\"PersonName\":\"wangshilian\",\"Age\":12,\"Educations\":[{\"_IDX\":0,\"_STS\":\"R\",\"School\":\"Kunming\",\"Year\":1996},{\"_IDX\":1,\"_STS\":\"R\",\"School\":\"Hongqi\",\"Year\":1993}]}",
				out.toString());
		System.out.println(out.toString());
	}
	


	@SuppressWarnings("unchecked")
	public final void test_SimpleTypeWith_FullEntityArray__readJsonParserString() throws Exception {

		//@formatter:off
		String textRef = "" +
				"type Company { " +
				"	!Rb1 Name;" +
				"};";
		String text = "" +
				"type TestPerson { " +
				"	!A1 Name;" +
				"   A2{" +
				"		!B1 Name;" +
				"		*B2{" +
				"				C1 Name;" +
				"		};" +
				"		#B3 Company;" +
				"		%B4 Company;" +
				"		B5[] Name;" +
				"		B6[]{" +
				"			D1 Name;" +
				"		};" +
				"	 };" +
				"	A3 Company;" +
				"	%A4 Company;" +
				"	A5[] Name;" +
				"	A6[]{" +
				"		E1 Name;" +
				"		E2{" +
				"			F1 Name;" +
				"		};" +
				"		E3 Company;" +
				"		%E4 Company;" +
				"	};" +
				"};";
		//@formatter:on		
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(textRef)).get(0);
		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);

		// Type For Test
		//@formatter:off
		String txtData = "{\"A1\":\"A1\"," +
								"\"A2\":{" +
										"\"B1\":\"B1Data\"," +
										"\"B2C1\":\"B2C1Data\"," +
										"\"B3Rb1\":\"B3Rb1Data\"," +
										"\"B4Rb1\":\"B4Rb1Data\"," +
										"\"B5\":[\"B5001\",\"B5002\",\"B5003\"]," +
										"\"B6\":[{\"D1\":\"B6D1001\"},{\"D1\":\"B6D1002\"}]" +
								"}," +
								"\"A3Rb1\":\"A3Rb1Data\"," +
								"\"A4Rb1\":\"A4Rb1Data\"," +
								"\"A5\":[\"A5001\",\"A5002\",\"A5003\"]," +
								"\"A6\":[" +
									"{" +
										"\"E1\":\"E1001\"," +
										"\"E2F1\":\"E2F1001\"," +
										"\"E3Rb1\":\"E3Rb1001\"," +
										"\"E4Rb1\":\"E4Rb1001\"" +
									"}," +
									"{\"E1\":\"E1002\",\"E2F1\":\"E2F1002\"}," +
									"{\"E1\":\"E1003\"}," +
									"{\"E1\":\"E1004\"}]" +
								"}";
		//@formatter:on	

		StringReader in = new StringReader(txtData);
		JsonParser jsonParser = factory.createJsonParser(in);

		Entity data = entityMerger.readFrom(null, jsonParser);
		assertNotNull(data);

		assertEquals("A1", data.get("A1"));

		assertEquals("B1Data", data.getEntity("A2").get("B1"));
		assertEquals("B2C1Data", data.getEntity("A2").get("B2C1"));
		assertEquals("B3Rb1Data", data.getEntity("A2").get("B3Rb1"));
		assertEquals("B4Rb1Data", data.getEntity("A2").get("B4Rb1"));

		List<String> list = (List<String>) data.getEntity("A2").get("B5");
		List<String> B5 = list;
		assertEquals(3, B5.size());
		assertEquals("B5001", B5.get(0));
		assertEquals("B5002", B5.get(1));
		assertEquals("B5003", B5.get(2));

		List<EditableEntity> B6 = (List<EditableEntity>) data.getEntity("A2").get("B6");
		assertEquals(2, B6.size());
		assertEquals("B6D1001", B6.get(0).get("D1"));
		assertEquals("B6D1002", B6.get(1).get("D1"));

		assertEquals("A3Rb1Data", data.get("A3Rb1"));
		assertEquals("A4Rb1Data", data.get("A4Rb1"));

		List<String> A5 = (List<String>) data.get("A5");
		assertEquals(3, A5.size());
		assertEquals("A5001", A5.get(0));
		assertEquals("A5002", A5.get(1));
		assertEquals("A5003", A5.get(2));

		List<EditableEntity> A6 = (List<EditableEntity>) data.get("A6");
		assertEquals(4, A6.size());
		assertEquals("E1001", A6.get(0).get("E1"));
		assertEquals("E2F1001", A6.get(0).get("E2F1"));
		assertEquals("E3Rb1001", A6.get(0).get("E3Rb1"));
		assertEquals("E4Rb1001", A6.get(0).get("E4Rb1"));

		assertEquals("E1002", A6.get(1).get("E1"));
		assertEquals("E2F1002", A6.get(1).get("E2F1"));
		assertEquals("E1003", A6.get(2).get("E1"));
		assertEquals("E1004", A6.get(3).get("E1"));
		
		//@formatter:off
		/*********************************************************/
		/***** test init *****/
		/*********************************************************/


//
//		entityMerger = new EntitySerializer(type);
//		StringWriter out = new StringWriter();
//		JsonGenerator gen = factory.createJsonGenerator(out);
//
//		Entity entity = new EditableEntity();
//		entity.put("A1", "A1");
//
//		EditableEntity A2 = new EditableEntity();
//		A2.put("B1", "B1Data");
//		A2.put("B3Rb1", "B3Rb1Data");
//		A2.put("B4Rb1", "B4Rb1Data");
//
//		A2.put("B2C1", "B2C1Data");
//
//		List<String> B5 = new ArrayList<String>();
//		B5.add("B5001");
//		B5.add("B5002");
//		B5.add("B5003");
//		A2.put("B5", B5);
//
//		List<EditableEntity> B6 = new ArrayList<EditableEntity>();
//		EditableEntity B61 = new EditableEntity();
//		B61.put("D1", "B6D1001");
//		B6.add(B61);
//		B61 = new EditableEntity();
//		B61.put("D1", "B6D1002");
//		B6.add(B61);
//
//		A2.put("B6", B6);
//		entity.put("A2", A2);
//
//		entity.put("A3Rb1", "A3Rb1Data");
//		entity.put("A4Rb1", "A4Rb1Data");
//
//		List<String> A5 = new ArrayList<String>();
//		A5.add("A5001");
//		A5.add("A5002");
//		A5.add("A5003");
//		entity.put("A5", A5);
//
//		List<EditableEntity> A6 = new ArrayList<EditableEntity>();
//		EditableEntity A61 = new EditableEntity();
//		A61.put("E1", "E1001");
//		A61.put("E2F1", "E2F1001");
//		A61.put("E3Rb1", "E3Rb1001");
//		A61.put("E4Rb1", "E4Rb1001");
//		A6.add(A61);
//
//		A61 = new EditableEntity();
//		A61.put("E1", "E1002");
//		A61.put("E2F1", "E2F1002");
//		A6.add(A61);
//
//		A61 = new EditableEntity();
//		A61.put("E1", "E1003");
//		A6.add(A61);
//
//		A61 = new EditableEntity();
//		A61.put("E1", "E1004");
//		A6.add(A61);
//
//		entity.put("A6", A6);
//
//		
//
//		entityMerger.stringifyTo(entity, gen);
//
//		gen.flush();
//
//		assertEquals(
//				"{\"A1\":\"A1\",\"A2\":{\"B1\":\"B1Data\",\"B2C1\":\"B2C1Data\",\"B3Rb1\":\"B3Rb1Data\",\"B4Rb1\":\"B4Rb1Data\",\"B5\":[\"B5001\",\"B5002\",\"B5003\"],\"B6\":[{\"D1\":\"B6D1001\"},{\"D1\":\"B6D1002\"}]},\"A3Rb1\":\"A3Rb1Data\",\"A4Rb1\":\"A4Rb1Data\",\"A5\":[\"A5001\",\"A5002\",\"A5003\"],\"A6\":[{\"E1\":\"E1001\",\"E2F1\":\"E2F1001\",\"E3Rb1\":\"E3Rb1001\",\"E4Rb1\":\"E4Rb1001\"},{\"E1\":\"E1002\",\"E2F1\":\"E2F1002\"},{\"E1\":\"E1003\"},{\"E1\":\"E1004\"}]}",
//				out.toString());
//		System.out.println(out.toString());
	}

	public final void test_SimpleTypeWith_FullEntityArray_stringifyToStringObjectJsonGenerator() throws Exception {

		//@formatter:off
		/*********************************************************/
		/***** test init *****/
		/*********************************************************/

		//@formatter:off
		String textRef = "" +
				"type Company { " +
				"	!Rb1 Name;" +
				"};";
		String text = "" +
				"type TestPerson { " +
				"	!A1 Name;" +
				"   A2{" +
				"		!B1 Name;" +
				"		*B2{" +
				"				C1 Name;" +
				"		};" +
				"		#B3 Company;" +
				"		%B4 Company;" +
				"		B5[] Name;" +
				"		B6[]{" +
				"			D1 Name;" +
				"		};" +
				"	 };" +
				"	A3 Company;" +
				"	%A4 Company;" +
				"	A5[] Name;" +
				"	A6[]{" +
				"		E1 Name;" +
				"		E2{" +
				"			F1 Name;" +
				"		};" +
				"		E3 Company;" +
				"		%E4 Company;" +
				"	};" +
				"};";
		//@formatter:on		
		//@formatter:on

		type = loader.testDefineNebula(new StringReader(textRef)).get(0);
		type = loader.testDefineNebula(new StringReader(text)).get(0);

		entityMerger = new EntitySerializer(type);
		StringWriter out = new StringWriter();
		JsonGenerator gen = factory.createJsonGenerator(out);

		Entity entity = new EditableEntity();
		entity.put("A1", "A1");

		EditableEntity A2 = new EditableEntity();
		A2.put("B1", "B1Data");
		A2.put("B3Rb1", "B3Rb1Data");
		A2.put("B4Rb1", "B4Rb1Data");

		A2.put("B2C1", "B2C1Data");

		List<String> B5 = new ArrayList<String>();
		B5.add("B5001");
		B5.add("B5002");
		B5.add("B5003");
		A2.put("B5", B5);

		List<EditableEntity> B6 = new ArrayList<EditableEntity>();
		EditableEntity B61 = new EditableEntity();
		B61.put("D1", "B6D1001");
		B6.add(B61);
		B61 = new EditableEntity();
		B61.put("D1", "B6D1002");
		B6.add(B61);

		A2.put("B6", B6);
		entity.put("A2", A2);

		entity.put("A3Rb1", "A3Rb1Data");
		entity.put("A4Rb1", "A4Rb1Data");

		List<String> A5 = new ArrayList<String>();
		A5.add("A5001");
		A5.add("A5002");
		A5.add("A5003");
		entity.put("A5", A5);

		List<EditableEntity> A6 = new ArrayList<EditableEntity>();
		EditableEntity A61 = new EditableEntity();
		A61.put("E1", "E1001");
		A61.put("E2F1", "E2F1001");
		A61.put("E3Rb1", "E3Rb1001");
		A61.put("E4Rb1", "E4Rb1001");
		A6.add(A61);

		A61 = new EditableEntity();
		A61.put("E1", "E1002");
		A61.put("E2F1", "E2F1002");
		A6.add(A61);

		A61 = new EditableEntity();
		A61.put("E1", "E1003");
		A6.add(A61);

		A61 = new EditableEntity();
		A61.put("E1", "E1004");
		A6.add(A61);

		entity.put("A6", A6);

		

		entityMerger.stringifyTo(entity, gen);

		gen.flush();

		assertEquals(
				"{\"A1\":\"A1\",\"A2\":{\"B1\":\"B1Data\",\"B2C1\":\"B2C1Data\",\"B3Rb1\":\"B3Rb1Data\",\"B4Rb1\":\"B4Rb1Data\",\"B5\":[\"B5001\",\"B5002\",\"B5003\"],\"B6\":[{\"_IDX\":0,\"_STS\":\"R\",\"D1\":\"B6D1001\"},{\"_IDX\":1,\"_STS\":\"R\",\"D1\":\"B6D1002\"}]},\"A3Rb1\":\"A3Rb1Data\",\"A4Rb1\":\"A4Rb1Data\",\"A5\":[\"A5001\",\"A5002\",\"A5003\"],\"A6\":[" +
				"{\"_IDX\":0,\"_STS\":\"R\",\"E1\":\"E1001\",\"E2F1\":\"E2F1001\",\"E3Rb1\":\"E3Rb1001\",\"E4Rb1\":\"E4Rb1001\"}," +
				"{\"_IDX\":1,\"_STS\":\"R\",\"E1\":\"E1002\",\"E2F1\":\"E2F1002\"}," +
				"{\"_IDX\":2,\"_STS\":\"R\",\"E1\":\"E1003\"}," +
				"{\"_IDX\":3,\"_STS\":\"R\",\"E1\":\"E1004\"}" +
				"]}",
				out.toString());
		System.out.println(out.toString());
	}
}

package nebula.lang;

import junit.framework.TestCase;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.DefaultDataRepos;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.TypeDatastore;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import util.NamesEncoding;

public class NebulaParser_Action_CompileTest extends TestCase {
	RuntimeContext context = new RuntimeContext() {
	};
	TypeLoaderForTest typeLoader;
	TypeImp type;
	Entity data;

	DataRepos repos;
	DataStore<Entity> store;

	@Override
	protected void setUp() throws Exception {
		typeLoader = new TypeLoaderForTest(new SystemTypeLoader());
		repos = new DefaultDataRepos(new TypeDatastore(typeLoader));
		store = repos.define(String.class, Entity.class, "Person");
	}

	private void eqValue(String fieldname, Object expectedResult, String exprText) {
		Field fieldTest = parseField(exprText);

		fieldTest.code.exec(context, repos, data);
		assertEquals(expectedResult, data.get(fieldname));
	}

	private Field parseField(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, typeLoader);
			type = new TypeImp(typeLoader, NamesEncoding.encode(this.getClass().getName(),false));
			Field field;
			field = new Field(type, "Name");
			field.type = parser.resolveType("Name");
			type.fields.add(field);

			field = new Field(type, "Age");
			field.type = parser.resolveType("Age");
			type.fields.add(field);

			field = new Field(type, "Height");
			field.type = parser.resolveType("Age");
			type.fields.add(field);

			parser.typesLoading.put(type.name, type);

			parser.currentType = type;

			parser.enterMethod(type, text);
			field = parser.fieldDefinition(type);
			parser.exitMethod();
			parser.exitTopType();

			return field;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	private TypeImp parseType(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, typeLoader);
			TypeImp type = parser.typeDefinition();

			return type;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testBasic() {
		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		long Age = 10;
		data.put("Age", Age);
		long Height = 120;
		data.put("Height", Height);

		eqValue("Age", 30L, "Test(){this.Age=30;};");
		eqValue("Age", 30L * 10L, "Test(){this.Age = this.Age * 10;};");
		eqValue("Height", Height + Height / 10 - 1, "Test(){this.Height=this.Height +  this.Height / 10 - 1;};");
	}

	public void testTypeDefinition_Repos_getByIndex() {
		EditableEntity person = new EditableEntity();
		person.put("Name", "wangshilian10");
		person.put("Age", 10L);
		store.add(person);

		store.flush();

		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		long Age = 10;
		data.put("Age", Age);
		long Height = 120;
		data.put("Height", Height);

		eqValue("Height", (Long) person.get("Age") + 10, "Test(){this.Height = $Person[0].Age + 10;};");
	}

	public void testTypeDefinition_Repos_getByRange() {
		EditableEntity person0 = new EditableEntity();
		person0.put("Name", "wangshilian01");
		person0.put("Age", 1L);
		store.add(person0);

		EditableEntity person1 = new EditableEntity();
		person1.put("Name", "wangshilian10");
		person1.put("Age", 10L);
		store.add(person1);

		EditableEntity person2 = new EditableEntity();
		person2.put("Name", "wangshilian20");
		person2.put("Age", 20L);
		store.add(person2);

		EditableEntity person3 = new EditableEntity();
		person3.put("Name", "wangshilian30");
		person3.put("Age", 30L);
		store.add(person3);

		EditableEntity person4 = new EditableEntity();
		person4.put("Name", "wangshilian40");
		person4.put("Age", 40L);
		store.add(person4);

		EditableEntity person5 = new EditableEntity();
		person5.put("Name", "wangshilian50");
		person5.put("Age", 50L);
		store.add(person5);

		store.flush();

		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		long Age = 10;
		data.put("Age", Age);
		long Height = 120;
		data.put("Height", Height);

		// eqValue("Height", (Long)person2.get("Age") + 10,
		// "Test(){this.Height = $Person[2].Age + 10;};");
		eqValue("Height", (Long) person2.get("Age") + 10, "Test(){this.Height = $Person[2,1,0][0].Age + 10;};");
		eqValue("Height", (Long) person1.get("Age") + 10, "Test(){this.Height = $Person[2,1,0][1].Age + 10;};");
		eqValue("Height", (Long) person0.get("Age") + 10, "Test(){this.Height = $Person[2,1,0][2].Age + 10;};");

		eqValue("Height", (Long) person1.get("Age") + 10, "Test(){this.Height = $Person[1..3][0].Age + 10;};");
		eqValue("Height", (Long) person2.get("Age") + 10, "Test(){this.Height = $Person[1..3][1].Age + 10;};");
		eqValue("Height", (Long) person3.get("Age") + 10, "Test(){this.Height = $Person[1..3][2].Age + 10;};");

		eqValue("Height", (Long) person0.get("Age") + 10, "Test(){this.Height = $Person[..3][0].Age + 10;};");
		eqValue("Height", (Long) person1.get("Age") + 10, "Test(){this.Height = $Person[..3][1].Age + 10;};");
		eqValue("Height", (Long) person2.get("Age") + 10, "Test(){this.Height = $Person[..3][2].Age + 10;};");
		eqValue("Height", (Long) person3.get("Age") + 10, "Test(){this.Height = $Person[..3][3].Age + 10;};");

		eqValue("Height", (Long) person2.get("Age") + 10, "Test(){this.Height = $Person[2..][0].Age + 10;};");
		eqValue("Height", (Long) person3.get("Age") + 10, "Test(){this.Height = $Person[2..][1].Age + 10;};");
		eqValue("Height", (Long) person4.get("Age") + 10, "Test(){this.Height = $Person[2..][2].Age + 10;};");
		eqValue("Height", (Long) person5.get("Age") + 10, "Test(){this.Height = $Person[2..][3].Age + 10;};");

		eqValue("Height", (Long) person0.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][0].Age + 10;};");
		eqValue("Height", (Long) person1.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][1].Age + 10;};");
		eqValue("Height", (Long) person3.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][2].Age + 10;};");
		eqValue("Height", (Long) person2.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][3].Age + 10;};");
		eqValue("Height", (Long) person4.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][4].Age + 10;};");
		eqValue("Height", (Long) person5.get("Age") + 10, "Test(){this.Height = $Person[..1,3,2,4..][5].Age + 10;};");
	}

	public void testTypeDefinition_Repos_getByClause() {
		EditableEntity person = null;
		person = new EditableEntity();
		person.put("Name", "wangshilian10");
		person.put("Age", 10L);
		store.add(person);

		person = new EditableEntity();
		person.put("Name", "wangshilian20");
		person.put("Age", 20L);
		store.add(person);

		person = new EditableEntity();
		person.put("Name", "wangshilian30");
		person.put("Age", 30L);
		store.add(person);

		store.flush();

		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		long Age = 10;
		data.put("Age", Age);
		long Height = 120;
		data.put("Height", Height);

		//TODO not test
		eqValue("Height", 10L + 10, "Test(){this.Height = $Person[Age<30][0].Age + 10;};");
		eqValue("Height", 20L + 10, "Test(){this.Height = $Person[Age>10][0].Age + 10;};");
		eqValue("Height", 30L + 10, "Test(){this.Height = $Person[Age>20][0].Age + 10;};");
		eqValue("Height", 10L + 10, "Test(){this.Height = $Person[Age<40][0].Age + 10;};");
		eqValue("Height", 20L + 10, "Test(){this.Height = $Person[Age > 10 && Age < 40][0].Age + 10;};");

		eqValue("Height", 10L + 10, "Test(){this.Height = $Person[Age == 10 || Age == 30][0].Age + 10;};");
		eqValue("Height", 30L + 10, "Test(){this.Height = $Person[Age == 10 || Age == 30][1].Age + 10;};");
	}

	public void testTypeDefinition() {
		//@formatter:off
			String text = "" +
					"type Order { " +
					"	!ID;" +
					"	Person\n" +
					"   Age;" +
					"   Complete(){" +
					"		this.Age=1;" +
					"	};" +
					"};";
			//@formatter:on	

		TypeImp type = parseType(text);

		assertEquals("Order", type.name);

		assertEquals(3, type.fields.size());

		assertEquals(4, type.actions.size());

		int i = 0;
		assertEquals("Complete", type.actions.get(i).name);
		assertNotNull(type.actions.get(i).code);
	}

}

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

public class NebulaParser_Action_CompileTest extends TestCase {
	TypeLoaderForTest typeLoader;
	Type type;
	Entity data;

	DataRepos repos;
	DataStore<Entity> store;

	@Override
	protected void setUp() throws Exception {
		typeLoader = new TypeLoaderForTest(new SystemTypeLoader());
		repos = new DefaultDataRepos(new TypeDatastore(typeLoader));
		store = repos.define(String.class, Entity.class, "Person").get();
	}

	private void eqValue(String fieldname, Object expectedResult, String exprText) {
		Field fieldTest = parseField(exprText);

		fieldTest.code.exec(data, repos);
		assertEquals(expectedResult, data.get(fieldname));
	}

	private Field parseField(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, typeLoader);
			type = new Type(typeLoader, "Test");
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

	public void testTypeDefinition() {
		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		int Age = 10;
		data.put("Age", Age);
		int Height = 120;
		data.put("Height", Height);

		eqValue("Age", 30, "Test(){this.Age=30;};");
		eqValue("Age", 30 * 10, "Test(){this.Age = this.Age * 10;};");
		eqValue("Height", Height + Height / 10 - 1, "Test(){this.Height=this.Height +  this.Height / 10 - 1;};");
	}
	public void testTypeDefinition_Repos_1() {
		EditableEntity person = new EditableEntity();
		person.put("Name", "wangshilian");
		person.put("Age", 10);
		store.add(person);
		store.flush();
		
		
		data = new EditableEntity();
		String Name = "wangshilian";
		data.put("Name", Name);

		int Age = 10;
		data.put("Age", Age);
		int Height = 120;
		data.put("Height", Height);

		eqValue("Height", (Integer)person.get("Age") + 10, "Test(){this.Height = $Person[0].Age + 10;};");
	}
}

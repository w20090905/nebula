package nebula.lang;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Action_CompileTest extends TestCase {
	TypeLoaderForTest compiler;
	Type type;
	Entity data;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	private void eqValue(String fieldname, Object expectedResult, String exprText) {
		Field fieldTest = parseField(exprText);

		fieldTest.code.exec(data,null);
		assertEquals(expectedResult, data.get(fieldname));
	}

	private Field parseField(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			type = new Type(compiler, "Test");
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
		eqValue("Age",30 * 10, "Test(){this.Age = this.Age * 10;};");
		eqValue("Height", Height + Height / 10 - 1, "Test(){this.Height=this.Height +  this.Height / 10 - 1;};");
	}
}

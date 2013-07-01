package nebula.lang;

import static nebula.lang.Importance.Core;
import static nebula.lang.Importance.Key;
import static nebula.lang.Importance.Require;
import static nebula.lang.Importance.Unimportant;
import static nebula.lang.Reference.ByVal;
import static nebula.lang.Reference.Inline;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import util.InheritHashMap;

public class NebulaParserTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	private Type parseType(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			Type type = parser.typeDefinition();

			return type;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testTypeDefinition() {
		//@formatter:off
			String text = "" +
					"type Person { " +
					"	姓名  Name;" +
					"};";
			//@formatter:on	

		Type type = parseType(text);

		assertEquals("Person", type.name);

		assertEquals(1, type.fields.size());

		Field f = type.fields.get(0);

		assertEquals("姓名", f.name);
		assertEquals(ByVal, f.refer);
		assertEquals(Require, f.importance);
	}

	private Field parseField(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			Type type = new Type(compiler, "Test");
			;
			Field field = parser.fieldDefinition(type);

			return field;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testFieldDefinition() {
		String text = "!Name;";

		Field v = parseField(text);

		assertEquals("Name", v.name);
		assertEquals(Key, v.importance);
	}

	public void testFieldDefinition_subType() {
		String text = "!Detail{Name;Age Height;};";

		Field f = parseField(text);

		assertEquals("Detail", f.name);
		assertEquals("Test$Detail", f.type.name);
		assertEquals(Inline, f.refer);
		assertEquals("Name", f.type.fields.get(0).name);
		assertEquals("Name", f.type.fields.get(0).type.name);
		assertEquals("Age", f.type.fields.get(1).name);
		assertEquals("Height", f.type.fields.get(1).type.name);
	}

	public void testFieldDefinition_default() {
		String text = "!Age := 10;";

		Field f = parseField(text);
		assertEquals("Age", f.name);
	}

	public void testFieldDefinition_derived() {
		Field f = parseField("!Age = 10;");
		assertEquals("Age", f.name);
		assertEquals(true, f.derived);
	}

	public void testFieldDefinition_quicktype() {
		Field f = parseField("	Max-Age;");
		assertEquals("MaxAge", f.name);
	}

	public void testFieldImportance_Importance() {
		assertEquals(Key, parseField("!Name;").importance);
		assertEquals(Core, parseField("*Name;").importance);
		assertEquals(Require, parseField("#Name;").importance);
		assertEquals(Unimportant, parseField("?Name;").importance);
		assertEquals(Key, parseField("!Name;").importance);
	}

	private NebulaParser.arrayDefinition_return parseArray(String text) {
		try {

			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);

			NebulaParser.arrayDefinition_return v = parser.arrayDefinition();
			assertEquals(0, parser.getNumberOfSyntaxErrors());

			return v;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testArrayDefinition() {

		NebulaParser.arrayDefinition_return v = parseArray("[]");
		assertEquals(null, v.from);
		assertEquals(null, v.to);

		v = parseArray("[1]");
		assertEquals("1", v.from);
		assertEquals(null, v.to);

		v = parseArray("[1..]");
		assertEquals("1", v.from);
		assertEquals(null, v.to);

		v = parseArray("[1..10]");
		assertEquals("1", v.from);
		assertEquals("10", v.to);

		v = parseArray("[..10]");
		assertEquals(null, v.from);
		assertEquals("10", v.to);
	}

	private InheritHashMap parseAttr(String text) {
		try {
			InheritHashMap attrs = new InheritHashMap();

			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);

			parser.attrItemDefinition(attrs);
			assertEquals(0, parser.getNumberOfSyntaxErrors());

			return attrs;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testAttr() {
		InheritHashMap attrs;

		attrs = parseAttr("MaxLength");
		assertEquals("MaxLength", attrs.get("MaxLength"));

		attrs = parseAttr("MaxLength(\"X\")");
		assertEquals("X", attrs.get("MaxLength"));

		attrs = parseAttr("MaxLength(1.1) ");
		assertEquals(new BigDecimal("1.1"), attrs.get("MaxLength"));
	}

	public void testAliasDefinition() {
		//@formatter:off
			String text = "" +
					"type Person|zh:员工  { " +
					"	Name|zh:姓名;" +
					"};";
			//@formatter:on		

		Type type = parseType(text);

		assertEquals("Person", type.name);

		assertEquals(1, type.fields.size());
		assertEquals("Name", type.fields.get(0).name);
		assertEquals("员工", type.nameAlias.get("zh"));
		assertEquals(Require, type.fields.get(0).importance);
		assertEquals("姓名", type.fields.get(0).nameAlias.get("zh"));
	}

	public void testNestTypeAliasDefinition() {
		//@formatter:off
			String text = "" +
					"type Person|zh:员工  { " +
					"	Name|zh:姓名;" +
					"	Detail |zh:明细{" +
					"		Name;" +
					"		Age;" +
					"	};" +
					"};";
			//@formatter:on		

		Type type = parseType(text);

		assertEquals("Person", type.name);
		assertEquals("员工", type.nameAlias.get("zh"));

		assertEquals(2, type.fields.size());
		int i = 0;
		assertEquals("Name", type.fields.get(i).name);
		assertEquals(Require, type.fields.get(i).importance);
		assertEquals("姓名", type.fields.get(i).nameAlias.get("zh"));

		i++;
		assertEquals("Detail", type.fields.get(i).name);
		assertEquals(Inline, type.fields.get(i).refer);
		assertEquals("明细", type.fields.get(i).nameAlias.get("zh"));
		assertEquals("明细", type.fields.get(i).type.nameAlias.get("zh"));
	}

	public void testProgramDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"type Person|zh:员工  { " +
					"	Name|zh:姓名;" +
					"	Detail |zh:明细{" +
					"		Name;" +
					"		Age;" +
					"	};" +
					"};";
			//@formatter:on		
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);

			List<Type> types = parser.programDefinition();

			Type type = types.get(0);

			assertEquals("Person", type.name);
			assertEquals("员工", type.nameAlias.get("zh"));

			assertEquals(2, type.fields.size());
			int i = 0;
			assertEquals("Name", type.fields.get(i).name);
			assertEquals(Require, type.fields.get(i).importance);
			assertEquals("姓名", type.fields.get(i).nameAlias.get("zh"));

			i++;
			assertEquals("Detail", type.fields.get(i).name);
			assertEquals("明细", type.fields.get(i).nameAlias.get("zh"));
			assertEquals("明细", type.fields.get(i).type.nameAlias.get("zh"));

			type = types.get(1);
			assertEquals("Person$Detail", type.name);
			assertEquals("明细", type.nameAlias.get("zh"));

		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
}

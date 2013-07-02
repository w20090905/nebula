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

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.joda.time.format.DateTimeFormat;

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
		Field f = parseField("!Age := 1014 * 1024;");
		assertEquals(1014 * 1024, f.defaultValue);

		f = parseField("!Age := \"test\";");
		assertEquals("test", f.defaultValue);

		f = parseField("!Age := 1.3;");
		assertEquals(new BigDecimal("1.3"), f.defaultValue);

		f = parseField("!Age := 4 > 5;");
		assertEquals(false, f.defaultValue);
	}

	public void testFieldDefinition_derived() {
		Field f = parseField("!MyAge Age = Age + 10 * 1000;");
		assertEquals("MyAge", f.name);
		assertEquals(true, f.derived);
		EntityExpression expr = f.derivedExpr;
		Entity e = new EditableEntity();
		int Age = 15;
		e.put("Age", Age);

		assertEquals(Age + 10 * 1000, expr.eval(e));
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

	private Expr<?> parseCst(String text) {
		try {

			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);

			Expr<?> expr = parser.constExpr();
			assertEquals(0, parser.getNumberOfSyntaxErrors());

			return expr;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testConstExpr() {
		assertEquals(1234, parseCst("1234").exec());
		assertEquals(new BigDecimal("1.1"), parseCst("1.1").exec());
		assertEquals("1.1", parseCst("\"1.1\"").exec());

		assertEquals(DateTimeFormat.forPattern("HH:mm:ss").parseDateTime("12:23:00"), parseCst("12:23:00").exec());
		assertEquals(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").parseDateTime("2006-11-23 12:23:00.234"),
				parseCst("2006-11-23 12:23:00.234").exec());
		assertEquals(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2006-11-23 12:23:00"),
				parseCst("2006-11-23 12:23:00").exec());
		assertEquals(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("2006-11-23"), parseCst("2006-11-23").exec());
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

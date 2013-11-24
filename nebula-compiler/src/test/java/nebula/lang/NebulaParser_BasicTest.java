package nebula.lang;

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

import util.InheritHashMap;

public class NebulaParser_BasicTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	private TypeImp parseType(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			TypeImp type = parser.typeDefinition();

			assertEquals(0, parser.getNumberOfSyntaxErrors());

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
					"	姓名2  Name\n" +
					"};";
			//@formatter:on	

		TypeImp type = parseType(text);

		assertEquals("Person", type.getName());

		assertEquals(2, type.getFields().size());

		Field f = type.getFields().get(0);

		assertEquals("姓名", f.name);
		assertEquals(ByVal, f.refer);
		assertFalse(f.isNullable());
	}

	public void testTypeKeyUnique() {
		//@formatter:off
			String text = "" +
					"type Person { " +
					"	姓名  Name;" +
					"	姓名2  Name\n" +
					"};";
			//@formatter:on	

		TypeImp type = parseType(text);

		assertEquals(false, type.getFields().get(0).isKey());
		assertEquals(false, type.getFields().get(0).isUnique());
		assertEquals(false, type.getFields().get(1).isKey());
		assertEquals(false, type.getFields().get(1).isUnique());

		//@formatter:off
		text = "" +
				"type Person { " +
				"	!姓名  Name;" +
				"	姓名2  Name\n" +
				"};";
		//@formatter:on	

		type = parseType(text);

		assertEquals(true, type.getFields().get(0).isKey());
		assertEquals(true, type.getFields().get(0).isUnique());
		assertEquals(false, type.getFields().get(1).isKey());
		assertEquals(false, type.getFields().get(1).isUnique());

		//@formatter:off
		text = "" +
				"type Person { " +
				"	!!姓名  Name;" +
				"	姓名2  Name\n" +
				"};";
		//@formatter:on	

		type = parseType(text);

		assertEquals(true, type.getFields().get(0).isKey());
		assertEquals(true, type.getFields().get(0).isUnique());
		assertEquals(false, type.getFields().get(1).isKey());
		assertEquals(false, type.getFields().get(1).isUnique());

		//@formatter:off
		text = "" +
				"type Person { " +
				"	!!姓名  Name;" +
				"	!姓名2  Name\n" +
				"};";
		//@formatter:on	

		type = parseType(text);

		assertEquals(true, type.getFields().get(0).isKey());
		assertEquals(true, type.getFields().get(0).isUnique());
		assertEquals(false, type.getFields().get(1).isKey());
		assertEquals(true, type.getFields().get(1).isUnique());

		//@formatter:off
		text = "" +
				"type Person { " +
				"	!姓名  Name;" +
				"	!姓名2  Name\n" +
				"};";
		//@formatter:on	

		type = parseType(text);

		assertEquals(true, type.getFields().get(0).isKey());
		assertEquals(true, type.getFields().get(0).isUnique());
		assertEquals(false, type.getFields().get(1).isKey());
		assertEquals(true, type.getFields().get(1).isUnique());
	}

	private Field parseField(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			TypeImp type = new TypeImp(compiler, "Test");
			Field field;
			field = new Field(type, "Name");
			field.type = parser.resolveType("Name");
			type.fields.add(field);

			field = new Field(type, "Age");
			field.type = parser.resolveType("Age");
			type.fields.add(field);

			parser.currentType = type;

			field = parser.fieldDefinition(type);
			parser.exitTopType();

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
		assertEquals(true, v.isUnique());
	}

	public void testFieldDefinition_subType() {
		String text = "!Detail{Name;Age Height;};";

		Field f = parseField(text);

		assertEquals("Detail", f.name);
		assertEquals("Test$Detail", f.type.getName());
		assertEquals(Inline, f.refer);
		assertEquals("Name", f.type.getFields().get(0).name);
		assertEquals("Name", f.type.getFields().get(0).type.getName());
		assertEquals("Age", f.type.getFields().get(1).name);
		assertEquals("Height", f.type.getFields().get(1).type.getName());
	}

	public void testFieldDefinition_default() {
		assertEquals(1014L * 1024L, parseField("!MyAge Age := 1014 * 1024;").exprAsm.eval(null, null, null));
		assertEquals("test", parseField("!MyAge Age := \"test\";").exprAsm.eval(null, null, null));
		assertEquals("test", parseField("!Name := ```test``` ;").exprAsm.eval(null, null, null));

		assertEquals("# Title\n## firstline\ntest content", parseField("!Name := ```\n# Title\n## firstline\ntest content``` ;").exprAsm.eval(null, null, null));

		assertEquals(new BigDecimal("1.3"), parseField("!MyAge Age := 1.3;").exprAsm.eval(null, null, null));
		assertEquals(4 > 5, parseField("!MyAge Age := 4 > 5;").exprAsm.eval(null, null, null));

	}

	public void testFieldDefinition_derived() {
		Entity e = new EditableEntity();
		long Age = 15;
		String name = "wangshilian";
		e.put("Name", name);
		e.put("Age", Age);

		assertEquals(Age, parseField("!MyAge Age = this.Age;").exprAsm.eval(null, null, e));
		assertEquals(Age + 10 * 1000, parseField("!MyAge Age = this.Age + 10 * 1000;").exprAsm.eval(null, null, e));

		assertEquals(name, parseField("!MyAge Name = this.Name;").exprAsm.eval(null, null, e));
	}

	public void testFieldDefinition_quicktype() {
		Field f = parseField("	Max-Age;");
		assertEquals("MaxAge", f.name);
	}

	public void testFieldImportance_Importance() {
		assertEquals(true, parseField("!!Name;").isKey());
		assertEquals(true, parseField("!!Name;").isUnique());
		assertEquals(true, parseField("!Name;").isUnique());
		assertEquals(true, parseField("*Name;").isCore());
		assertEquals(true, parseField("#Name;").isRequired());
		assertEquals(true, parseField("?Name;").isNullable());
		assertEquals(true, parseField("!Name;").isUnique());
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

	private InheritHashMap parseAnnotation(String text) {
		try {
			InheritHashMap attrs = new InheritHashMap();

			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);

			parser.annotationItemDefinition(attrs);
			assertEquals(0, parser.getNumberOfSyntaxErrors());

			return attrs;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testAnnotation() {
		assertEquals("MaxLength", parseAnnotation("MaxLength").get("MaxLength"));
		assertEquals("X", parseAnnotation("MaxLength(\"X\")").get("MaxLength"));
		assertEquals(new BigDecimal("1.1"), parseAnnotation("MaxLength(1.1) ").get("MaxLength"));
	}

	public void testTypeAliases() {
		assertEquals("Person", parseType("type Person {Name;};").getDisplayName());
		assertEquals("员工", parseType("type Person|员工  {Name;};").getDisplayName());
		assertEquals("Person", parseType("type Person|zh:员工  {Name;};").getDisplayName());
		assertEquals("员工", parseType("type Person|zh:员工  {Name;};").nameAlias.get("zh"));
	}

	public void testFieldAliases() {
		assertEquals("年龄", parseField("Age;").getDisplayName());
		assertEquals("Age", parseField("Age Age;").getDisplayName());
		assertEquals("年龄Local", parseField("Age|年龄Local Age;").getDisplayName());
		assertEquals("临时 Age", parseField("临时-Age;").getDisplayName());
		assertEquals("临时 Age", parseField("临时-Age Age;").getDisplayName());
		assertEquals("永久年龄", parseField("临时-Age|永久年龄 Age;").getDisplayName());

		assertEquals("Name", parseField("Name|zh:姓名|\"zh-tw\":台湾姓名;").getDisplayName());
		assertEquals("姓名", parseField("Name|zh:姓名|\"zh-tw\":台湾姓名;").nameAlias.get("zh-cn"));
		assertEquals("台湾姓名", parseField("Name|zh:姓名|\"zh-tw\":台湾姓名;").nameAlias.get("zh-tw"));
		assertEquals("姓名", parseField("Name|zh:姓名|\"zh-tw\":台湾姓名;").nameAlias.get("zh"));
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
		assertEquals(String.valueOf(1234L), parseCst("1234").toString());
		assertEquals(String.valueOf(new BigDecimal("1.1")), parseCst("1.1").toString());
		assertEquals(String.valueOf("1.1"), parseCst("\"1.1\"").toString());

		assertEquals(String.valueOf("QWEQWEQWEWQE"), parseCst("\"QWEQWEQWEWQE\"").toString());
		assertEquals(String.valueOf("QWEQWEQWEWQE"), parseCst("\'QWEQWEQWEWQE\'").toString());

		assertEquals(String.valueOf(""), parseCst("``````").toString());
		assertEquals(String.valueOf("1234567890"), parseCst("```1234567890```").toString());
		assertEquals(String.valueOf("12345\n67890"), parseCst("```12345\n67890```").toString());
		assertEquals(String.valueOf("12345\n67890"), parseCst("```\n12345\n67890```").toString());
		assertEquals(String.valueOf("12345\n67890"), parseCst("```\r\n12345\n67890```").toString());

		assertEquals("12:23:00", parseCst("12:23:00").toString());
		assertEquals("2006-11-23 12:23:00.234", parseCst("2006-11-23 12:23:00.234").toString());
		assertEquals("2006-11-23 12:23:00", parseCst("2006-11-23 12:23:00").toString());
		assertEquals("2006-11-23", parseCst("2006-11-23").toString());
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
					"	Detail2 {" +
					"		Name;" +
					"		Age;" +
					"	};" +
					"};";
			//@formatter:on		

		TypeImp type = parseType(text);

		assertEquals("Person", type.getName());
		assertEquals("员工", type.getNameAlias().get("zh"));

		assertEquals(3, type.getFields().size());
		int i = 0;
		assertEquals("Name", type.getFields().get(i).name);
		assertEquals(true, type.getFields().get(i).isRequired());
		assertEquals("姓名", type.getFields().get(i).nameAlias.get("zh"));

		i++;
		assertEquals("Detail", type.getFields().get(i).name);
		assertEquals(Inline, type.getFields().get(i).refer);
		assertEquals("明细", type.getFields().get(i).nameAlias.get("zh"));
		assertEquals("明细", type.getFields().get(i).type.getNameAlias().get("zh"));
		i++;
		assertEquals("Detail2", type.getFields().get(i).name);
		assertEquals(Inline, type.getFields().get(i).refer);
		assertEquals("Detail2", type.getFields().get(i).nameAlias.getDefault());
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

			List<TypeImp> types = parser.programDefinition();

			Type type = types.get(0);

			assertEquals("Person", type.getName());
			assertEquals("员工", type.getNameAlias().get("zh"));

			assertEquals(2, type.getFields().size());
			int i = 0;
			assertEquals("Name", type.getFields().get(i).name);
			assertEquals(true, type.getFields().get(i).isRequired());
			assertEquals("姓名", type.getFields().get(i).nameAlias.get("zh"));

			i++;
			assertEquals("Detail", type.getFields().get(i).getName());
			assertEquals("明细", type.getFields().get(i).nameAlias.get("zh"));
			assertEquals("明细", type.getFields().get(i).type.getNameAlias().get("zh"));

			type = types.get(1);
			assertEquals("Person$Detail", type.getName());
			assertEquals("明细", type.getNameAlias().get("zh"));

		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
}

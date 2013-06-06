package nebula.lang;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaFlowParserTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	public void testTypeDefinition() {
		try {
			//@formatter:off
			String text = "" +
					"flow Issue { \n" +
					"	[employee] Begin{ \n" +
					"		Name;\n" +
					"		Age;\n" +
					"	};\n" +
					"	[me.department.$leader] Approve{ \n" +
					"	};\n" +
					"	[me.company.hr.$leader] Approve{ \n" +
					"	};\n" +
					"	[me.company.hr.$leader] Step1:  Approve{ \n" +
					"	};\n" +
					"	[me.company.hr.$leader] Step2 : Approve;\n" +
					"};\n";
			//@formatter:on		
			NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);

			Flow flow = parser.flowDefinition();

			assertEquals("Issue", flow.name);

			assertEquals(5, flow.steps.size());

			Flow.Step step = flow.steps.get(0);

			assertEquals("Begin",  step.name);
			assertEquals("Begin",  step.stepType.name);
			
			assertEquals(2,  step.stepType.fields.size());
			int i = 0;
			assertEquals("Name", step.stepType.fields.get(i++).name);
			assertEquals("Age", step.stepType.fields.get(i++).name);
			
			step = null;
			

			 step = flow.steps.get(1);

			assertEquals("Approve", step.name);
			assertEquals("Approve", step.stepType.name);
			i = 0;

			step = null;

			step = flow.steps.get(2);

			assertEquals("Approve2", step.name);
			assertEquals("Approve", step.stepType.name);
			i = 0;
			step = null;
			

			step = flow.steps.get(3);

			assertEquals("Step1", step.name);
			assertEquals("Step1", step.stepType.name);
			assertEquals("Approve", step.stepType.superType.name);
			i = 0;
			step = null;

			step = flow.steps.get(4);

			assertEquals("Step2", step.name);
			assertEquals("Approve", step.stepType.name);
			i = 0;
			step = null;
		} catch (RecognitionException e) {
			fail(e.toString());
		}
	}
	//
	// public void testFieldDefinition() {
	// try {
//			//@formatter:off
//			String text = "!Name;";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Type type = new Type(compiler,"Test");
	// Field v = parser.fieldDefinition(type);
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("Name", v.name);
	// assertEquals(Key, v.importance);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testFieldDefinition_subType() {
	// try {
//			//@formatter:off
//			String text = "!Detail{Name;Age Height;};";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Type type = new Type(compiler,"Test");
	// Field v = parser.fieldDefinition(type);
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("Detail", v.name);
	// assertEquals("Test$Detail", v.type.name);
	// assertEquals(Inline, v.refer);
	// assertEquals("Name", v.type.fields.get(0).name);
	// assertEquals("Name", v.type.fields.get(0).type.name);
	// assertEquals("Age", v.type.fields.get(1).name);
	// assertEquals("Height", v.type.fields.get(1).type.name);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// /*
	// * '!' {v=Key;}
	// | '*' {v=Core;}
	// | '#' {v=Require;}
	// | '?' {v=Unimportant;}
	// | {v=Require;}
	// */
	// public void testFieldImportance_KEY() {
	// try {
//			//@formatter:off
//			String text = "!";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Importance v = parser.fieldImportance();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Key, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testFieldImportance_CORE() {
	// try {
//			//@formatter:off
//			String text = "*";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Importance v = parser.fieldImportance();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Core, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testFieldImportance_REQUIRE() {
	// try {
//			//@formatter:off
//			String text = "#";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Importance v = parser.fieldImportance();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Require, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testFieldImportance_UNIMPORTANT() {
	// try {
//			//@formatter:off
//			String text = "?";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Importance v = parser.fieldImportance();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Unimportant, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testInlineDefinition_Inline() {
	// try {
//			//@formatter:off
//			String text = "&";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Reference v = parser.inlineDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Reference.Inline, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testInlineDefinition_CASCADE() {
	// try {
//			//@formatter:off
//			String text = "%";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Reference v = parser.inlineDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(Cascade, v);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testArrayDefinition() {
	// try {
//			//@formatter:off
//			String text = "[]";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// NebulaFlowParser.arrayDefinition_return v = parser.arrayDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(null, v.from);
	// assertEquals(null, v.to);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testArrayDefinition_1() {
	// try {
//			//@formatter:off
//			String text = "[1]";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// NebulaFlowParser.arrayDefinition_return v = parser.arrayDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("1", v.from);
	// assertEquals(null, v.to);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testArrayDefinition_1_() {
	// try {
//			//@formatter:off
//			String text = "[1..]";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// NebulaFlowParser.arrayDefinition_return v = parser.arrayDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("1", v.from);
	// assertEquals(null, v.to);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testArrayDefinition_1_10() {
	// try {
//			//@formatter:off
//			String text = "[1..10]";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// NebulaFlowParser.arrayDefinition_return v = parser.arrayDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("1", v.from);
	// assertEquals("10", v.to);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testArrayDefinition_0_10() {
	// try {
//			//@formatter:off
//			String text = "[..10]";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// NebulaFlowParser.arrayDefinition_return v = parser.arrayDefinition();
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(null, v.from);
	// assertEquals("10", v.to);
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testAttrDefinition_String_X() {
	// try {
//			//@formatter:off
//			String text = "MaxLength(\"X\")";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// InheritHashMap attrs = new InheritHashMap();
	// parser.attrItemDefinition(attrs);
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals("X", attrs.get("MaxLength"));
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	// public void testAttrDefinition_BigDecimal_10() {
	// try {
//			//@formatter:off
//			String text = "MaxLength(1.1) ";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// InheritHashMap attrs = new InheritHashMap();
	// parser.attrItemDefinition(attrs);
	//
	// assertEquals(0, parser.getNumberOfSyntaxErrors());
	// assertEquals(new BigDecimal("1.1"), attrs.get("MaxLength"));
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	//
	// public void testAliasDefinition() {
	// try {
//			//@formatter:off
//			String text = "" +
//					"type Person|zh:员工  { " +
//					"	Name|zh:姓名;" +
//					"};";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Type type = parser.typeDefinition();
	//
	// assertEquals("Person", type.name);
	//
	// assertEquals(1, type.fields.size());
	// assertEquals("Name", type.fields.get(0).name);
	// assertEquals("员工", type.nameAlias.get("zh"));
	// assertEquals(Require, type.fields.get(0).importance);
	// assertEquals("姓名", type.fields.get(0).nameAlias.get("zh"));
	//
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	//
	//
	// public void testNestTypeAliasDefinition() {
	// try {
//			//@formatter:off
//			String text = "" +
//					"type Person|zh:员工  { " +
//					"	Name|zh:姓名;" +
//					"	Detail |zh:明细{" +
//					"		Name;" +
//					"		Age;" +
//					"	};" +
//					"};";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// Type type = parser.typeDefinition();
	//
	// assertEquals("Person", type.name);
	// assertEquals("员工", type.nameAlias.get("zh"));
	//
	// assertEquals(2, type.fields.size());
	// int i=0;
	// assertEquals("Name", type.fields.get(i).name);
	// assertEquals(Require, type.fields.get(i).importance);
	// assertEquals("姓名", type.fields.get(i).nameAlias.get("zh"));
	//
	// i++;
	// assertEquals("Detail", type.fields.get(i).name);
	// assertEquals(Inline, type.fields.get(i).refer);
	// assertEquals("明细", type.fields.get(i).nameAlias.get("zh"));
	// assertEquals("明细", type.fields.get(i).type.nameAlias.get("zh"));
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
	// public void testProgramDefinition() {
	// try {
//			//@formatter:off
//			String text = "" +
//					"type Person|zh:员工  { " +
//					"	Name|zh:姓名;" +
//					"	Detail |zh:明细{" +
//					"		Name;" +
//					"		Age;" +
//					"	};" +
//					"};";
//			//@formatter:on		
	// NebulaFlowLexer lexer = new NebulaFlowLexer(new ANTLRStringStream(text));
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// NebulaFlowParser parser = new NebulaFlowParser(tokens, compiler);
	//
	// List<Type> types = parser.programDefinition();
	//
	// Type type = types.get(0);
	//
	// assertEquals("Person", type.name);
	// assertEquals("员工", type.nameAlias.get("zh"));
	//
	// assertEquals(2, type.fields.size());
	// int i=0;
	// assertEquals("Name", type.fields.get(i).name);
	// assertEquals(Require, type.fields.get(i).importance);
	// assertEquals("姓名", type.fields.get(i).nameAlias.get("zh"));
	//
	// i++;
	// assertEquals("Detail", type.fields.get(i).name);
	// assertEquals("明细", type.fields.get(i).nameAlias.get("zh"));
	// assertEquals("明细", type.fields.get(i).type.nameAlias.get("zh"));
	//
	//
	// type = types.get(1);
	// assertEquals("Person$Detail", type.name);
	// assertEquals("明细", type.nameAlias.get("zh"));
	//
	// } catch (RecognitionException e) {
	// fail(e.toString());
	// }
	// }
}

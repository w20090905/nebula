package nebula.lang;

import junit.framework.TestCase;
import nebula.data.impl.EditableEntity;
import nebula.lang.Flow.Step;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Flow_BasicTest extends TestCase {
	RuntimeContext context = new RuntimeContext() {
	};
	TypeLoaderForFlowTest typeLoader;

	@Override
	protected void setUp() throws Exception {
		typeLoader = new TypeLoaderForFlowTest(new SystemTypeLoader());
	}

	private Flow parseFlow(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, typeLoader);
			Flow flow = parser.flowDefinition();
			assertEquals(0, parser.getNumberOfSyntaxErrors());
			return flow;
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
			assertEquals(0, parser.getNumberOfSyntaxErrors());
			return type;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	private Step parseStep(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, typeLoader);
			Flow flow = new Flow(typeLoader, typeLoader.findType(TypeStandalone.Flow.name()), "Test");
			flow.addStep("test", "Approve", typeLoader.findType("Approve"));
			Step step = parser.stepDefinition(flow);
			assertEquals(0, parser.getNumberOfSyntaxErrors());
			return step;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	public void testFlowDefinition_Flow_Base() {
		//@formatter:off
		String text = "" +
				"type Flow { };";
		//@formatter:on		

		TypeImp type = parseType(text);

		assertNotNull(type);
	}

	public void testFlowDefinition_Step_Base() {

		//@formatter:off
		String text = "" +
				"type Step {" +
				"		@Runtime NextStep String := \"Next\";" +
				"		@Runtime DoItNow YesNo := false;" +
				"		@Runtime init(){" +
				"		}; " +
				"		@Runtime next(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime end(){" +
				"			this.NextStep = \"End\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime skip(){" +
				"			this.NextStep = \"Next\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime submit(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"};";
		//@formatter:on	

		TypeImp type = parseType(text);

		EditableEntity entity = new EditableEntity();

		assertNotNull(type);
		int i = 0;
		assertEquals("NextStep", type.getFields().get(i).name);
		assertEquals("String", type.getFields().get(i).type.getName());
		entity.put("NextStep", type.getFields().get(i).exprAsm.eval(context, null, entity));

		assertEquals("Next", entity.get("NextStep"));
		assertEquals(null, entity.get("DoItNow"));

		i++;
		assertEquals("DoItNow", type.getFields().get(i).name);
		assertEquals("YesNo", type.getFields().get(i).type.getName());
		entity.put("DoItNow", type.getFields().get(i).exprAsm.eval(context, null,entity));

		assertEquals("Next", entity.get("NextStep"));
		assertEquals(false, entity.get("DoItNow"));

		i = 0;

		assertEquals("init", type.actions.get(i).name);

		type.actions.get(i).actionAsm.exec(context, null,entity);
		assertEquals("Next", entity.get("NextStep"));
		assertEquals(false, entity.get("DoItNow"));

		i++;

		assertEquals("next", type.actions.get(i).name);

		type.actions.get(i).actionAsm.exec(context, null,entity);

		assertEquals("Next", entity.get("NextStep"));
		assertEquals(true, entity.get("DoItNow"));

		i++;

		assertEquals("end", type.actions.get(i).name);

		type.actions.get(i).actionAsm.exec(context, null,entity);
		assertEquals("End", entity.get("NextStep"));
		assertEquals(true, entity.get("DoItNow"));

		i++;

		assertEquals("skip", type.actions.get(i).name);

		type.actions.get(i).actionAsm.exec(context, null,entity);
		assertEquals("Next", entity.get("NextStep"));
		assertEquals(true, entity.get("DoItNow"));
	}

	public void testFlowDefinition_basic() {
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
				"	[me.company.hr.$leader] Approve;\n" +
				"	[me.company.hr.$leader] Step1:  Approve{ \n" +
				"	};\n" +
				"	[me.company.hr.$leader] Step2 : Approve;\n" +
				"};\n";
		//@formatter:on		

		Flow flow = parseFlow(text);

		assertEquals("Issue", flow.name);
		assertEquals("Issue", flow.name);

		assertEquals(6, flow.steps.size());

		Flow.Step step = flow.steps.get(0);

		assertEquals("Begin", step.name);
		assertEquals("Issue$Begin1", step.type.getName());

		assertEquals(2, step.type.getDeclaredFields().size());
		int i = 0;
		assertEquals("Name", step.type.getDeclaredFields().get(i++).name);
		assertEquals("Age", step.type.getDeclaredFields().get(i++).name);

		step = null;

		step = flow.steps.get(1);

		assertEquals("Approve", step.name);
		assertEquals("Issue$Approve2", step.type.getName());
		i = 0;

		step = null;

		step = flow.steps.get(2);

		assertEquals("Approve2", step.name);
		assertEquals("Issue$Approve3", step.type.getName());
		i = 0;
		step = null;

		step = flow.steps.get(3);

		assertEquals("Approve3", step.name);
		assertEquals("Approve", step.type.getName());
		i = 0;
		step = null;

		step = flow.steps.get(4);

		assertEquals("Step1", step.name);
		assertEquals("Issue$Step1", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());
		i = 0;
		step = null;

		step = flow.steps.get(5);

		assertEquals("Step2", step.name);
		assertEquals("Approve", step.type.getName());
		i = 0;
		step = null;
	}

	public void testFlowDefinition_Flow() {
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
				"	[me.company.hr.$leader] Approve;\n" +
				"	[me.company.hr.$leader] Step1:  Approve{ \n" +
				"	};\n" +
				"	[me.company.hr.$leader] Step2 : Approve;\n" +
				"};\n";
		//@formatter:on		

		Flow flow = parseFlow(text);

		assertEquals("Issue", flow.name);
		assertEquals("Issue", flow.name);

		assertEquals(6, flow.steps.size());

		Flow.Step step = flow.steps.get(0);

		assertEquals("Begin", step.name);
		assertEquals("Issue$Begin1", step.type.getName());

		assertEquals(2, step.type.getDeclaredFields().size());
		int i = 0;
		assertEquals("Name", step.type.getDeclaredFields().get(i++).name);
		assertEquals("Age", step.type.getDeclaredFields().get(i++).name);

		step = null;

		step = flow.steps.get(1);

		assertEquals("Approve", step.name);
		assertEquals("Issue$Approve2", step.type.getName());
		i = 0;

		step = null;

		step = flow.steps.get(2);

		assertEquals("Approve2", step.name);
		assertEquals("Issue$Approve3", step.type.getName());
		i = 0;
		step = null;

		step = flow.steps.get(3);

		assertEquals("Approve3", step.name);
		assertEquals("Approve", step.type.getName());
		i = 0;
		step = null;

		step = flow.steps.get(4);

		assertEquals("Step1", step.name);
		assertEquals("Issue$Step1", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());
		i = 0;
		step = null;

		step = flow.steps.get(5);

		assertEquals("Step2", step.name);
		assertEquals("Approve", step.type.getName());
		i = 0;
		step = null;

	}

	public void testFlowDefinition_action() {
		//@formatter:off
		String text = "" +
				"flow Issue { \n" +
				"	[employee] Begin{ \n" +
				"		Name;\n" +
				"		Age :=0;\n" +
				"		init(){this.Age=10;}\n" +
				"	};\n" +
				"};\n";
		//@formatter:on		

		Flow flow = parseFlow(text);

		assertEquals("Issue", flow.name);
		assertEquals("Issue", flow.name);

		assertEquals(1, flow.steps.size());

		Flow.Step step = flow.steps.get(0);

		assertEquals("Begin", step.name);
		assertEquals("Issue$Begin1", step.type.getName());

		assertEquals(2, step.type.getDeclaredFields().size());
		int i = 0;
		assertEquals("Name", step.type.getDeclaredFields().get(i++).name);
		assertEquals("Age", step.type.getDeclaredFields().get(i++).name);

	}

	public void testStepDefinition() {
		// String text = null;
		Step step = null;

		step = parseStep("Begin;");
		assertEquals("Begin", step.name);
		assertEquals("Begin", step.type.getName());

		step = parseStep("Approve;");
		assertEquals("Approve2", step.name);
		assertEquals("Approve", step.type.getName());

		step = parseStep("@Next(\"GP\") Approve;");
		assertEquals("Approve2", step.name);
		assertEquals("Approve", step.type.getName());
		assertEquals("GP", step.getAttrs().get(Step.Next));

		step = parseStep("aa : Approve;");
		assertEquals("aa", step.name);
		assertEquals("Approve", step.type.getName());

		step = parseStep("aa extends Approve;");
		assertEquals("aa", step.name);
		assertEquals("Approve", step.type.getName());
		assertEquals("Step", step.type.getSuperType().getName());

		step = parseStep("AA extends Approve{};");
		assertEquals("AA", step.name);
		assertEquals(step.resideFlow.name + "$AA", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());

		step = parseStep("Begin{ };");
		assertEquals("Begin", step.name);
		assertEquals(step.resideFlow.name + "$Begin2", step.type.getName());
		assertEquals("Begin", step.type.getSuperType().getName());
		
		step = parseStep("Begin{data.*; };");
		assertEquals("Begin", step.name);
		assertEquals(step.resideFlow.name + "$Begin2", step.type.getName());
		assertEquals("Begin", step.type.getSuperType().getName());
		assertEquals("WithAllField", step.type.getAttrs().get("WithAllField"));

		step = parseStep("Approve { };");
		assertEquals("Approve2", step.name);
		assertEquals(step.resideFlow.name + "$Approve2", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());

		String query = "dfdsf";

		step = parseStep("[" + query + "] Approve { };");
		assertEquals("Approve2", step.name);
		assertEquals("[" + query + "]", step.actorQuery);
		assertEquals(step.resideFlow.name + "$Approve2", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());

		step = parseStep("[" + query + "] Approve { Age; };");
		assertEquals("Approve2", step.name);
		assertEquals("[" + query + "]", step.actorQuery);
		assertEquals(step.resideFlow.name + "$Approve2", step.type.getName());
		assertEquals("Approve", step.type.getSuperType().getName());

		assertEquals(1, step.type.getDeclaredFields().size());
		assertEquals("Age", step.type.getDeclaredFields().get(0).name);

	}
}

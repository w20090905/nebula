package nebula.lang;

import junit.framework.TestCase;
import nebula.lang.Flow.Step;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class NebulaParser_Flow_BasicTest extends TestCase {
	TypeLoaderForFlowTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForFlowTest(new SystemTypeLoader());
	}

	private Flow parseFlow(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			Flow flow = parser.flowDefinition();
			assertEquals(0, parser.getNumberOfSyntaxErrors());
			return flow;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
	}

	private Step parseStep(String text) {
		try {
			NebulaLexer lexer = new NebulaLexer(new ANTLRStringStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NebulaParser parser = new NebulaParser(tokens, compiler);
			Flow flow = new Flow(compiler, "Test");
			flow.addStep("test", "Approve", compiler.findType("Approve"));
			Step step = parser.stepDefinition(flow);
			assertEquals(0, parser.getNumberOfSyntaxErrors());
			return step;
		} catch (RecognitionException e) {
			fail(e.toString());
			return null;
		}
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
		assertEquals("Issue$Begin1", step.stepType.name);

		assertEquals(2, step.stepType.fields.size());
		int i = 0;
		assertEquals("Name", step.stepType.fields.get(i++).name);
		assertEquals("Age", step.stepType.fields.get(i++).name);

		step = null;

		step = flow.steps.get(1);

		assertEquals("Approve", step.name);
		assertEquals("Issue$Approve2", step.stepType.name);
		i = 0;

		step = null;

		step = flow.steps.get(2);

		assertEquals("Approve2", step.name);
		assertEquals("Issue$Approve3", step.stepType.name);
		i = 0;
		step = null;

		step = flow.steps.get(3);

		assertEquals("Approve3", step.name);
		assertEquals("Approve", step.stepType.name);
		i = 0;
		step = null;

		step = flow.steps.get(4);

		assertEquals("Step1", step.name);
		assertEquals("Issue$Step1", step.stepType.name);
		assertEquals("Approve", step.stepType.superType.name);
		i = 0;
		step = null;

		step = flow.steps.get(5);

		assertEquals("Step2", step.name);
		assertEquals("Approve", step.stepType.name);
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
		assertEquals("Issue$Begin1", step.stepType.name);

		assertEquals(2, step.stepType.fields.size());
		int i = 0;
		assertEquals("Name", step.stepType.fields.get(i++).name);
		assertEquals("Age", step.stepType.fields.get(i++).name);

	}

	
	public void testStepDefinition() {
		// String text = null;
		Step step = null;

		step = parseStep("Begin;");
		assertEquals("Begin", step.name);
		assertEquals("Begin", step.stepType.name);

		step = parseStep("Approve;");
		assertEquals("Approve2", step.name);
		assertEquals("Approve", step.stepType.name);

		step = parseStep("aa : Approve;");
		assertEquals("aa", step.name);
		assertEquals("Approve", step.stepType.name);

		step = parseStep("aa extends Approve;");
		assertEquals("aa", step.name);
		assertEquals("Approve", step.stepType.name);
		assertEquals("Step", step.stepType.superType.name);
		
		step = parseStep("AA extends Approve{};");
		assertEquals("AA", step.name);
		assertEquals(step.resideFlow.name + "$AA", step.stepType.name);
		assertEquals("Approve", step.stepType.superType.name);

		step = parseStep("Begin{ };");
		assertEquals("Begin", step.name);
		assertEquals(step.resideFlow.name + "$Begin2", step.stepType.name);
		assertEquals("Begin", step.stepType.superType.name);

		step = parseStep("Approve { };");
		assertEquals("Approve2", step.name);
		assertEquals(step.resideFlow.name + "$Approve2", step.stepType.name);
		assertEquals("Approve", step.stepType.superType.name);

		String query = "dfdsf";

		step = parseStep("[" + query + "] Approve { };");
		assertEquals("Approve2", step.name);
		assertEquals("[" + query + "]", step.actorQuery);
		assertEquals(step.resideFlow.name + "$Approve2", step.stepType.name);
		assertEquals("Approve", step.stepType.superType.name);

		step = parseStep("[" + query + "] Approve { Age; };");
		assertEquals("Approve2", step.name);
		assertEquals("[" + query + "]", step.actorQuery);
		assertEquals(step.resideFlow.name + "$Approve2", step.stepType.name);
		assertEquals("Approve", step.stepType.superType.name);

		assertEquals(1, step.stepType.fields.size());
		assertEquals("Age", step.stepType.fields.get(0).name);

	}
}

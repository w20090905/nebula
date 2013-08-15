package nebula.flow;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import nebula.lang.Flow;
import nebula.lang.NebulaLexer;
import nebula.lang.NebulaParser;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoaderForFlowTest;
import junit.framework.TestCase;

public class FlowEngineTest extends TestCase {
	TypeLoaderForFlowTest typeLoader;

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

	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testFlowEngine() {
		//@formatter:off
		String text = "" +
				"flow Issue { \n" +
				"	[employee] Begin{ \n" +
				"		Name;\n" +
				"		Age :=0;\n" +
				"		init(){this.Age=10;}\n" +
				"	};\n" +
				"	[employee] Approve;\n" +
				"	[employee] End{ };\n" +
				"};\n";
		//@formatter:on		

		Flow flow = parseFlow(text);
		
		FlowEngine engine = new FlowEngine(flow);
		engine.start();
		System.out.println(engine.currentStep.getName() + " : " + engine.data);
		
		engine.currentStepEntity.put("Name", "wangshilian");
		engine.currentStepEntity.put("Age", 10L);
		engine.stepSubmit();
		
		System.out.println(engine.currentStep.getName() + " : " + engine.data);
		
		
		engine.stepSubmit();

		System.out.println(engine.currentStep.getName() + " : " + engine.data);
		
	}
//
//	public final void testStart() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testStartSubmitString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testStartSubmit() {
//		fail("Not yet implemented"); // TODO
//	}

}

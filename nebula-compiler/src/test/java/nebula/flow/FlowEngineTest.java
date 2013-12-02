package nebula.flow;

import junit.framework.TestCase;
import nebula.lang.Field;
import nebula.lang.Flow;
import nebula.lang.NebulaClassLoader;
import nebula.lang.NebulaLexer;
import nebula.lang.NebulaParser;
import nebula.lang.RuntimeContext;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoaderForFlowTest;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class FlowEngineTest extends TestCase {
	TypeLoaderForFlowTest typeLoader;
	RuntimeContext context = new RuntimeContext() {
	};

	protected void setUp() throws Exception {
		NebulaClassLoader.clear();
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
				"		Age :=10;\n" +
				"	};\n" +
				"	[employee] Approve;\n" +
				"	[employee] Approve;\n" +
				"	[employee] End{ };\n" +
				"};\n";
		//@formatter:on		

		String name = "wangshilian";
		Long age = 10L;
		Flow flow = parseFlow(text);

		FlowEngine engine = new FlowEngine(context, flow);
		// 启动流程
		engine.start();

		// 初始画面
		assertEquals("Begin", engine.currentStep.getName());
		assertNull(engine.data.get("Name"));
		assertNull(engine.data.get("Age"));
		for (Field field : engine.currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 录入数据，提交
		engine.currentStepEntity.put("Name", name);
		engine.currentStepEntity.put("Age", age);
		engine.stepSubmit();

		// 进入审批画面
		assertEquals("Approve", engine.currentStep.getName());
		assertEquals(name, engine.data.get("Name"));
		assertEquals(age, engine.data.get("Age"));

		// 审批通过
		engine.stepSubmit();

		// 进入审批画面
		assertEquals("Approve2", engine.currentStep.getName());
		assertEquals(name, engine.data.get("Name"));
		assertEquals(age, engine.data.get("Age"));

		// 审批通过
		engine.stepSubmit();

		// 进入结束Step
		assertEquals("End", engine.currentStep.getName());
		assertEquals(name, engine.data.get("Name"));
		assertEquals(age, engine.data.get("Age"));
	}

	public final void testFlowEngine_Skip() {
		//@formatter:off
		String text = "" +
				"flow Issue { \n" +
				"	[employee] Begin{ \n" +
				"		Name;\n" +
				"		Age :=0;\n" +
				"		init(){this.Age=10;}\n" +
				"	};\n" +
				"	[employee] Approve{ \n" +
				"		Age;" +
				"	init() {\n" +
				" 		if(this.Age==10)this.skip();\n" +
				"	};\n" +
				"};\n" +
				"	[employee] Approve;\n" +
				"	[employee] End{ };\n" +
				"};\n";
		//@formatter:on		

		String name = "wangshilian";
		Long age = 10L;
		Flow flow = parseFlow(text);

		FlowEngine engine = new FlowEngine(context, flow);
		// 启动流程
		engine.start();

		// 初始画面
		assertEquals("Begin", engine.currentStep.getName());
		assertNull(engine.data.get("Name"));
		assertNull(engine.data.get("Age"));

		// 录入数据，提交
		engine.currentStepEntity.put("Name", name);
		engine.currentStepEntity.put("Age", age);
		engine.stepSubmit();

		// 跳过第一级审批画面
//		assertEquals("Approve", engine.currentStep.getName());
//		assertEquals(name, engine.data.get("Name"));
//		assertEquals(age, engine.data.get("Age"));

		// 审批通过
//		engine.stepSubmit();

		// 进入第二级审批画面
		assertEquals("Approve2", engine.currentStep.getName());
		assertEquals(name, engine.data.get("Name"));
		assertEquals(age, engine.data.get("Age"));

		// 审批通过
		engine.stepSubmit();

		// 进入结束Step
		assertEquals("End", engine.currentStep.getName());
		assertEquals(name, engine.data.get("Name"));
		assertEquals(age, engine.data.get("Age"));
	}
//
//	public final void testStart() {
//		fail("Not yet implemented");
//	}
//
//	public final void testStartSubmitString() {
//		fail("Not yet implemented");
//	}
//
//	public final void testStartSubmit() {
//		fail("Not yet implemented");
//	}

}

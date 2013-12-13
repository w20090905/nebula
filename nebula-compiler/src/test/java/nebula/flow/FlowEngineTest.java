package nebula.flow;

import java.util.List;

import junit.framework.TestCase;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.DefaultDataRepos;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.TypeDatastore;
import nebula.lang.Field;
import nebula.lang.Flow;
import nebula.lang.Flow.Step;
import nebula.lang.RuntimeContext;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoaderForFlowTest;

public class FlowEngineTest extends TestCase {
	TypeLoaderForFlowTest typeLoader;
	DataRepos datarepos;
	TypeDatastore types;
	RuntimeContext context = new RuntimeContext() {
	};

	protected void setUp() throws Exception {
		typeLoader = new TypeLoaderForFlowTest(new SystemTypeLoader());
		types = new TypeDatastore(typeLoader);
		datarepos = new DefaultDataRepos(types);
	}

	private Flow parseFlow(String text) {
		return (Flow) typeLoader.load(text);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testFlowEngine() {
		//@formatter:off
		String text = "" +
				"flow Issue { \n" +
				"	@Next(\"GP\") [employee] Begin{ \n" +
				"	data.*;	" +
				"		Name;\n" +
				"		Age :=10;\n" +
				"	};\n" +
				"	[employee] Approve | 审批;\n" +
				"	[employee] Approve | 审批审批;\n" +
				"	[employee] End{ };\n" +
				"};\n";
		//@formatter:on		

		String name = "wangshilian";
		Long age = 10L;
		Flow flow = parseFlow(text);

		FlowEngine engine = new FlowEngine(datarepos, flow);
		// 启动流程
		EditableEntity data = engine.start(context);

		// 初始画面
		List<Entity> steps = data.get("steps");
		Entity currentStepEntity = steps.get(steps.size() - 1);
		Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		assertEquals("Begin", currentStep.getName());
		assertEquals("GP", currentStep.getAttrs().get(Step.NextAnnotation));
		assertNull(data.get("Name"));
		assertNull(data.get("Age"));
		for (Field field : currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 录入数据，提交
		currentStepEntity.put("Name", name);
		currentStepEntity.put("Age", age);
		engine.stepSubmit(context, data);

		// 进入审批画面
		steps = data.get("steps");

		assertEquals("审批", data.get("curStep"));
		assertEquals(name, data.get("Name"));
		
		currentStepEntity = steps.get(steps.size() - 1);
		currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));
		
		assertEquals("Approve", currentStep.getName());
		assertEquals(name, data.get("Name"));
		assertEquals(age, data.get("Age"));
		for (Field field : currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 审批通过
		assertNull(currentStepEntity.get("Comment"));
		currentStepEntity.put("Comment", "Approve2 OK");
		engine.stepSubmit(context, data);

		// 进入审批画面
		steps = data.get("steps");
		currentStepEntity = steps.get(steps.size() - 1);
		currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));
		assertEquals("Approve2", currentStep.getName());
		assertEquals(name, data.get("Name"));
		assertEquals(age, data.get("Age"));
		for (Field field : currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 审批通过
		assertNull(currentStepEntity.get("Comment"));
		currentStepEntity.put("Comment", "Approve3 OK");
		engine.stepSubmit(context, data);

		// 进入结束Step
		steps = data.get("steps");
		currentStepEntity = steps.get(steps.size() - 1);
		currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));
		assertEquals("End", currentStep.getName());
		assertEquals(name, data.get("Name"));
		assertEquals(age, data.get("Age"));

		DataStore<Entity> d = datarepos.define(String.class, Entity.class, flow.getName());
		Entity entity = d.listAll().get(0);
		System.out.println(entity);
	}

	public final void testFlowEngine_Skip() {
		//@formatter:off
		String text = "" +
				"flow Issue { \n" +
				"	[ALL] Begin{ \n" +
				"		Name;\n" +
				"		Age :=0;\n" +
				"		<init>(){this.Age=10;}\n" +
				"	};\n" +
				"	[owner] Approve{ \n" +
				"		Age;" +
				"	<init>() {\n" +
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

		FlowEngine engine = new FlowEngine(datarepos, flow);
		// 启动流程
		EditableEntity data = engine.start(context);

		// 初始画面		
		List<Entity> steps = data.get("steps");
		Entity currentStepEntity = steps.get(steps.size() - 1);
		Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		assertEquals("Begin", currentStep.getName());
		assertNull(data.get("Name"));
		assertNull(data.get("Age"));
		for (Field field : currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 录入数据，提交
		currentStepEntity.put("Name", name);
		currentStepEntity.put("Age", age);
		engine.stepSubmit(context,data);

		// 跳过第一级审批画面
		// assertEquals("Approve", currentStep.getName());
		// assertEquals(name, data.get("Name"));
		// assertEquals(age, data.get("Age"));

		// 审批通过
		// engine.stepSubmit();

		// 进入第二级审批画面
		steps = data.get("steps");
		currentStepEntity = steps.get(steps.size() - 1);
		currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		assertEquals("Approve2", currentStep.getName());
		assertEquals(name, data.get("Name"));
		assertEquals(age, data.get("Age"));
		for (Field field : currentStep.getType().getActions()) {
			if (!field.isInternal()) {
				System.out.println("Action : " + field.getDisplayName());
			}
		}

		// 审批通过
		engine.stepSubmit(context,data);

		// 进入结束Step
		steps = data.get("steps");
		currentStepEntity = steps.get(steps.size() - 1);
		currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		assertEquals("End", currentStep.getName());
		assertEquals(name, data.get("Name"));
		assertEquals(age, data.get("Age"));
	}
	//
	// public final void testStart() {
	// fail("Not yet implemented");
	// }
	//
	// public final void testStartSubmitString() {
	// fail("Not yet implemented");
	// }
	//
	// public final void testStartSubmit() {
	// fail("Not yet implemented");
	// }

}

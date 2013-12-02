package nebula.flow;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Field;
import nebula.lang.Flow;
import nebula.lang.Flow.Step;
import nebula.lang.NebulaNative;
import nebula.lang.RuntimeContext;

import com.google.common.base.Preconditions;

public class FlowEngine {
	EditableEntity data;
	final Flow flow;
	Step currentStep = null;
	Entity currentStepEntity = null;
	DataRepos datarepos;
	final RuntimeContext context;

	FlowEngine(final RuntimeContext context, final Flow flow) {
		this.context = context;
		this.flow = flow;
	}

	void start() {
		data = new EditableEntity();
		stepIn(flow.getSteps().get(Step.Begin));
	}

	void stepIn(Step step) {
		currentStep = step;
		currentStepEntity = new EditableEntity();

		// copy data from flow
		for (Field f : currentStep.getType().getFields()) {
			currentStepEntity.put(f.getName(), data.get(f.getName()));
		}

		NebulaNative.ctor(context, datarepos, currentStepEntity, step.getType());

		Field action = step.getType().getActionByName(Step.Init);
		Preconditions.checkNotNull(action);
		action.getActionAsm().exec(context, datarepos, currentStepEntity);

		if ((Boolean) currentStepEntity.get(Step.DoItNow) != null && (Boolean) currentStepEntity.get(Step.DoItNow)) {
			String next = (String) currentStepEntity.get(Step.NextStep);
			Step nextStep = null;
			if (Step.Next.equals(next)) {
				nextStep = flow.getSteps().get(currentStep.getIndex() + 1);
			} else {
				nextStep = flow.getSteps().get(next);
			}
			stepIn(nextStep);
			return;
		}

		for (Field f : step.getType().getFields()) {
			System.out.println(f.getName());
		}
	}

	void stepSubmit(String actionName) {
		// copy data to flow
		for (Field f : currentStep.getType().getFields()) {
			data.put(f.getName(), currentStepEntity.get(f.getName()));
			System.out.println("entity." + f.getName() + " = this." + f.getName());
		}

		Field action = currentStep.getType().getActionByName(actionName);
		Preconditions.checkNotNull(action);
		action.getActionAsm().exec(context, datarepos, currentStepEntity);

		if ((Boolean) currentStepEntity.get(Step.DoItNow) != null && (Boolean) currentStepEntity.get(Step.DoItNow)) {
			String next = (String) currentStepEntity.get(Step.NextStep);
			Step nextStep = null;
			if (Step.Next.equals(next)) {
				nextStep = flow.getSteps().get(currentStep.getIndex() + 1);
			} else {
				nextStep = flow.getSteps().get(next);
			}
			stepIn(nextStep);
			return;
		}
	}

	void stepSubmit() {
		stepSubmit(Step.Submit);
	}
}

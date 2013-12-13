package nebula.flow;

import java.util.ArrayList;
import java.util.List;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Field;
import nebula.lang.Flow;
import nebula.lang.Flow.Step;
import nebula.lang.NebulaNative;
import nebula.lang.RuntimeContext;

import com.google.common.base.Preconditions;

public class FlowEngine {
	final Flow flow;
	final DataRepos datarepos;
	final DataStore<Entity> datastore;

	public FlowEngine(DataRepos datarepos, final Flow flow) {
		this.datarepos = datarepos;
		this.flow = flow;
		this.datastore = datarepos.define(String.class, Entity.class, flow.getName());
	}

	public EditableEntity start(final RuntimeContext context) {
		EditableEntity data = new EditableEntity();
		data.put("ID", System.currentTimeMillis());
		data.put("steps", new ArrayList<Entity>());
		stepIn(context, flow.getSteps().get(Step.Begin), data);
		return data;
	}

	private void stepIn(final RuntimeContext context, Step step, EditableEntity data) {
		Step currentStep = step;
		EditableEntity currentStepEntity = new EditableEntity();

		// copy data from flow
		for (Field f : currentStep.getType().getDeclaredFields()) {
			if (!f.isInternal()) currentStepEntity.put(f.getName(), data.get(f.getName()));
		}

		NebulaNative.ctor(context, datarepos, currentStepEntity, step.getType());

		currentStepEntity.put(Step.Field_ActualCurrrentStep, currentStep.getName());
		data.put("curStep", currentStep.getDisplayName());

		Field action = step.getType().getActionByName(Step.Init);
		Preconditions.checkNotNull(action);
		action.getActionAsm().exec(context, datarepos, currentStepEntity);

		if ((Boolean) currentStepEntity.get(Step.Field_DoItNow) != null && (Boolean) currentStepEntity.get(Step.Field_DoItNow)) {
			String next = (String) currentStepEntity.get(Step.Field_NextStep);
			Step nextStep = null;
			if (Step.Next.equals(next)) {
				nextStep = flow.getSteps().get(currentStep.getIndex() + 1);
			} else if (Step.Previous.equals(next)) {
				nextStep = flow.getSteps().get(currentStep.getIndex() - 1);
			} else {
				nextStep = flow.getSteps().get(next);
			}
			currentStepEntity.put(Step.Field_ActualNextStep, nextStep.getName());
			addStepEntity(data, currentStepEntity);

			stepIn(context, nextStep, data);
			return;
		} else {
			addStepEntity(data, currentStepEntity);
		}
	}

	@SuppressWarnings("unchecked")
	private void addStepEntity(EditableEntity data, Entity currentStepEntity) {
		((List<Entity>) data.get("steps")).add(currentStepEntity);
	}

	public void stepSubmit(final RuntimeContext context, String actionName, EditableEntity data) {
		List<Entity> steps = data.get("steps");
		Entity currentStepEntity = steps.get(steps.size() - 1);
		Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		// copy data to flow
		for (Field f : currentStep.getType().getDeclaredFields()) {
			if (!f.isInternal()) data.put(f.getName(), currentStepEntity.get(f.getName()));
		}

		Field action = currentStep.getType().getActionByName(actionName);
		Preconditions.checkNotNull(action);
		action.getActionAsm().exec(context, datarepos, currentStepEntity);

		if ((Boolean) currentStepEntity.get(Step.Field_DoItNow) != null && (Boolean) currentStepEntity.get(Step.Field_DoItNow)) {
			String next = (String) currentStepEntity.get(Step.Field_NextStep);
			Step nextStep = null;
			if (Step.Next.equals(next)) {
				nextStep = flow.getSteps().get(currentStep.getIndex() + 1);
			} else {
				nextStep = flow.getSteps().get(next);
			}
			currentStepEntity.put(Step.Next, nextStep.getName());
			stepIn(context, nextStep, data);
		}
		datastore.add(data);
		datastore.flush();
	}

	public void stepSubmit(final RuntimeContext context, EditableEntity data) {
		stepSubmit(context, Step.Submit, data);
	}
}

package nebula.lang;

import nebula.data.SmartList;
import nebula.data.Timable;

import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Flow extends Type {
	public static final String APPROVE = "Approve";

	Multiset<String> names = HashMultiset.create();

	SmartList<String, Step> steps;

	Flow(TypeLoader typeLoader, String name) {
		super(typeLoader, name);
		steps = new SmartList<String, Step>(new Function<Step, String>() {
			@Override
			public String apply(Step input) {
				return input.name;
			}
		});
	}

	public Step addStep(String actorQuery, String name, Type stepType) {
		names.add(name);
		int cnt = names.count(name);
		if (cnt > 1) {
			name = name + cnt;
		}

		Step step = new Step(this, name, actorQuery, stepType);
		this.steps.add(step);
		return step;
	}

	class Step implements Timable {
		final Flow resideFlow;
		final Type stepType;
		final String name;
		final String actorQuery;

		Step(Flow resideFlow, String name, String actorQuery, Type stepType) {
			this.resideFlow = resideFlow;
			this.stepType = stepType;
			this.name = name;
			this.actorQuery = actorQuery;
		}

		@Override
		public long getLastModified() {
			return System.currentTimeMillis();
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.name + "[" + stepType.toString()+  "]";
		}
	}
}

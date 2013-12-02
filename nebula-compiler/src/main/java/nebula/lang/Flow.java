package nebula.lang;

import nebula.data.SmartList;
import nebula.data.Timable;

import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Flow extends TypeImp {
	public static final String APPROVE = "Approve";

	Multiset<String> names = HashMultiset.create();

	SmartList<String, Step> steps;

	Flow(TypeLoader typeLoader, Type superType, String name) {
		super(typeLoader, name, superType, TypeStandalone.Flow);
		// Type(TypeLoader typeLoader, String name, Type superType,
		// TypeStandalone standalone) {
		// super(typeLoader, name);
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

		Step step = new Step(this, this.steps.size(), name, actorQuery, stepType);
		this.steps.add(step);
		return step;
	}

	public class Step implements Timable {
		// Runtime Field
		public final static String Field_NextStep = "NextStep";
		public final static String Field_DoItNow = "DoItNow";
		
		
		// Actions
		public final static String Init = "init";
		public final static String Submit = "submit";
		
		// Steps
		public final static String Begin = "Begin";
		public final static String Previous = "Previous";
		public final static String Next = "Next";
		public final static String Cancel = "Cancel";
		public final static String Terminal = "Terminal";
		public final static String End = "End";
		
		final Flow resideFlow;
		final Type type;
		final String name;
		final String actorQuery;
		final int index;

		Step(Flow resideFlow, int index, String name, String actorQuery, Type stepType) {
			this.resideFlow = resideFlow;
			this.index = index;
			this.type = stepType;
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
			return this.name + "[" + type.toString() + "]";
		}

		public Flow getResideFlow() {
			return resideFlow;
		}

		public Type getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public int getIndex() {
			return index;
		}
	}

	public SmartList<String, Step> getSteps() {
		return steps;
	}
}

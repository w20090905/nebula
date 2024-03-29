package nebula.lang;

import util.InheritHashMap;
import nebula.data.SmartList;
import nebula.data.Timable;

import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Flow extends TypeImp {
	public final static String Field_CurrrentStepEntity = "<currentStep>";
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
		{
			Field id = new Field(this, "ID");
			id.type = typeLoader.findType("ID");
			id.modifiers = Modifier.Key;
			id.refer = Reference.ByVal;
			id.nameAlias = new Aliases(id.name);
			fields.add(id);
		}
		{
			Field id = new Field(this,"curStep");
			id.type = typeLoader.findType("Name");
			id.refer = Reference.ByVal;
			id.nameAlias = new Aliases("当前状态");
			fields.add(id);
		}
		
	}

	public Step addStep(String actorQuery, String name, Type stepType) {
		names.add(name);
		int cnt = names.count(name);
		if (cnt > 1) {
			name = name + cnt;
		}

		Step step = new Step(this, this.steps.size(), name, actorQuery, stepType);

		this.steps.add(step);

		if (Step.Begin.equals(step.name)) {
			this.fields.addAll(step.type.getDeclaredFields()); // 需设定为无需持久化
		}
		return step;
	}

	public class Step implements Timable {
		// Runtime Field
		public final static String Field_ActualCurrrentStep = "ActualCurrentStep";
		public final static String Field_ActualNextStep = "ActualNextStep";
		public final static String Field_NextStep = "NextStep";
		public final static String Field_DoItNow = "DoItNow";

		// Actions
		public final static String Init = "init";
		public final static String Submit = "submit";
		// Annotation
		public final static String NextAnnotation = "Next";
		// Steps
		public final static String Begin = "Begin";
		public final static String Previous = "<Previous>";
		public final static String Next = "<Next>";
		public final static String Cancel = "<Cancel>";
		public final static String Terminal = "<Terminal>";
		public final static String End = "End";

		final Flow resideFlow;
		final Type type;
		final String name;
		final String actorQuery;
		final int index;
		final InheritHashMap attrs;
		Aliases nameAlias;

		Step(Flow resideFlow, int index, String name, String actorQuery, Type stepType) {
			this.resideFlow = resideFlow;
			this.index = index;
			this.type = stepType;
			this.name = name;
			this.actorQuery = actorQuery;
			this.attrs = new InheritHashMap();
			this.nameAlias = new Aliases(name);
		}

		public String getDisplayName() {
			return nameAlias.getDefault();
		}

		@Override
		public long getLastModified() {
			return System.currentTimeMillis();
		}

		@Override
		public String toString() {
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

		public InheritHashMap getAttrs() {
			return attrs;
		}

		public void setNameAlias(Aliases aliases) {
			this.nameAlias = aliases;
		}
	}

	@Override
	public SmartList<String, Step> getSteps() {
		return steps;
	}
}

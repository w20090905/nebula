package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.DataWatcher;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.flow.FlowEngine;
import nebula.lang.Flow;
import nebula.lang.Flow.Step;
import nebula.lang.RuntimeContext;
import nebula.lang.Type;

public class FlowNewResouce extends AbstractResouce {
	static final String Field_Flow_ID = "FlowID";
	static final String Field_Step_Name = "StepName";
	static final String Field_GetAction_Name = "$getaction";
	static final String Field_Action_Name = "$action";

	private final FlowEngine engine;
	private Flow flow;
	// private final DataStore<Entity> datastore;
	private final DataHelper<Entity, Reader, Writer> jsonflow;
	private final Map<String, DataHelper<Entity, Reader, Writer>> stepJsons;

	public FlowNewResouce(final DataRepos dataRepos, DataStore<Entity> datas, Type type) {
		super("text/json", 1, 1);
		// this.dataRepos = dataRepos;
		// this.datastore = datas;

		Broker.brokerOf(type).addWatcher(new DataWatcher<Type>() {			
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				flow = (Flow)newData;
				return false;
			}
		});

		this.engine = new FlowEngine(dataRepos, this.flow);
		stepJsons = new HashMap<String, DataHelper<Entity, Reader, Writer>>();
		for (Step step : this.flow.getSteps()) {
			stepJsons.put(step.getName(), JsonHelperProvider.getHelper(step.getType()));
		}
		jsonflow = JsonHelperProvider.getHelper(type);
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = null;
		data = engine.start(new RuntimeContext());
		List<Entity> steps = data.get("steps");
		Entity currentStepEntity = steps.get(steps.size() - 1);
		Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));

		DataHelper<Entity, Reader, Writer> jsonStep = this.stepJsons.get(currentStep.getName());

		long newModified = System.currentTimeMillis();

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			write.write('{');
			{
				write.write("\"flow\":");
				jsonflow.stringifyTo(data, write);
				write.write(",\"step\":");
				jsonStep.stringifyTo(currentStepEntity, write);
			}
			write.write('}');
			write.flush();
			this.lastModified = newModified;
			this.cache = bout.toByteArray();
		} finally {
			try {
				if (bout != null) bout.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void put(HttpServletRequest req) throws IOException {
		throw new RuntimeException("Cann't find object ");
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object ");
	}
}

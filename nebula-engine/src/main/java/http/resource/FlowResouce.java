package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import nebula.data.impl.EditableEntity;
import nebula.data.json.DataHelper;
import nebula.data.json.JsonHelperProvider;
import nebula.flow.FlowEngine;
import nebula.lang.Flow;
import nebula.lang.Flow.Step;
import nebula.lang.NebulaNative;
import nebula.lang.RuntimeContext;
import nebula.lang.Type;
import nebula.server.Resource;
import nebula.server.ResourceEngine;

public class FlowResouce extends AbstractResouce implements ResourceEngine {
	static final String Field_Flow_ID = "FlowID";
	static final String Field_Step_Name = "StepName";
	static final String Field_GetAction_Name = "$getaction";
	static final String Field_Action_Name = "$action";

	final FlowEngine engine;
	private Flow flow;
	private final DataStore<Entity> datastore;
	private final Map<String, DataHelper<Entity, Reader, Writer>> stepJsons;
	private final DataRepos dataRepos;
	private long id;

	public FlowResouce(final DataRepos dataRepos, DataStore<Entity> datas, Type type, String id) {
		super("text/json", 1, 1);
		this.dataRepos = dataRepos;
		this.datastore = datas;
		this.id = Long.parseLong(id);
		Broker.brokerOf(type).addWatcher(new DataWatcher<Type>() {
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				flow = (Flow) newData;
				return false;
			}
		});

		this.engine = new FlowEngine(dataRepos, this.flow);
		stepJsons = new HashMap<String, DataHelper<Entity, Reader, Writer>>();
		for (Step step : this.flow.getSteps()) {
			stepJsons.put(step.getName(), JsonHelperProvider.getFlowHelper(type, step.getType()));
		}
		// jsonflow = JsonHelperProvider.getHelper(type);
	}

	@Override
	protected void get(HttpServletRequest req) throws IOException {
		Entity data = datastore.get(id).editable();
		List<Entity> steps = data.get("steps");
		Entity currentStepEntity = steps.get(steps.size() - 1);
		Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));
		DataHelper<Entity, Reader, Writer> jsonStep = this.stepJsons.get(currentStep.getName());
		data.put(Flow.Field_CurrrentStepEntity, currentStepEntity);
		datastore.clearChanges();
		
		long newModified = System.currentTimeMillis();

		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			Writer write = new OutputStreamWriter(bout);
			jsonStep.stringifyTo(data, write);
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
		Entity readonly = datastore.get(id);
		if (readonly != null) {
			Entity data = readonly.editable();
			List<Entity> steps = data.get("steps");
			Entity currentStepEntity = steps.get(steps.size() - 1);
			Step currentStep = flow.getSteps().get((String) currentStepEntity.get(Step.Field_ActualCurrrentStep));
			DataHelper<Entity, Reader, Writer> jsonStep = this.stepJsons.get(currentStep.getName());
			
			data.put(Flow.Field_CurrrentStepEntity, currentStepEntity);			
			jsonStep.readFrom(data, new InputStreamReader(req.getInputStream()));
			currentStepEntity = data.getEntity(Flow.Field_CurrrentStepEntity);
			
			String action = req.getParameter("$action");
			if (action != null && !"save".equals(action)) {
				RuntimeContext context = new RuntimeContext();
				engine.stepSubmit(context, action, (EditableEntity)data);
			}
		} else {
			throw new RuntimeException("Cann't find object " + id);
		}
	}

	@Override
	protected void delete(HttpServletRequest req) {
		throw new RuntimeException("Cann't find object " + flow.getName());
	}

	@Override
	public Resource resolve(String path) {
		// TODO Auto-generated method stub
		return null;
	}
}

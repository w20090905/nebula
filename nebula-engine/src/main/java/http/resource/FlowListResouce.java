package http.resource;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.Classificator;
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
import nebula.lang.RuntimeContext;
import nebula.lang.Type;

import org.eclipse.jetty.util.URIUtil;

import util.FileUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class FlowListResouce extends AbstractResouce {
	private final DataHelper<Entity, Reader, Writer> jsonHolder;
	private final DataStore<Entity> datastoreHolder;
	final LoadingCache<String, DataHolder> dataCache;
	final Type type;
	private Flow flow;

	interface DataHolder {
		byte[] get();
	}

	class DataHolderClassificator implements DataHolder {
		Classificator<String, Entity> classificator;
		String value;

		public DataHolderClassificator(Classificator<String, Entity> classificator, String value) {
			this.classificator = checkNotNull(classificator);
			this.value = checkNotNull(value);
		}

		public byte[] get() {
			List<Entity> dataList = classificator.getData(value);
			return buildFrom(dataList);
		}
	}

	private byte[] buildFrom(List<Entity> dataList) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Entity data : dataList) {
			if (!start) {
				out.append(',');
			} else {
				start = false;
			}
			jsonHolder.stringifyTo(data, new OutputStreamWriter(out));
		}
		out.append(']');

		out.flush();
		out.close();
		return bout.toByteArray();
	}

	final DataRepos dataRepos;

	public FlowListResouce(final DataRepos dataRepos, DataHelper<Entity, Reader, Writer> json, DataStore<Entity> datas, Type type) {
		super("text/json", 0, 1000);
		this.dataRepos = dataRepos;
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.type = type;
		this.dataCache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, DataHolder>() {
			public DataHolder load(String query) {
				String[] params = query.split("&");
				DataStore<Entity> dataStore = datastoreHolder;
				Map<String, Classificator<String, Entity>> classificatores = dataStore.getClassificatores();
				String cKey = null;
				String cValue = null;
				for (String keyvalue : params) {
					String[] kv = keyvalue.split("=");
					String key = kv[0];
					String value = kv[1];
					if (classificatores.containsKey(key)) {
						cKey = key;
						cValue = value;
						return new DataHolderClassificator(classificatores.get(checkNotNull(cKey)), cValue);
					}
				}
				return null;
			}
		});

		Broker.brokerOf(type).addWatcher(new DataWatcher<Type>() {
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				flow = (Flow) newData;
				return false;
			}
		});
		Broker.brokerOf(type).addWatcher(new DataWatcher<Type>() {
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				dataCache.cleanUp();
				return false;
			}
		});		
		

//			stepJsons.put(step.getName(), JsonHelperProvider.getHelper(step.getType()));
		
		json= JsonHelperProvider.getFlowHelper(type, flow.getSteps().get(Step.Begin).getType());
	}

	protected void get(HttpServletRequest req) {
		try {
			String query = URIUtil.decodePath(req.getQueryString());
			List<Entity> dataList;
			if (query == null || query.length() == 0) {
				dataList = datastoreHolder.listAll();
				this.cache = buildFrom(dataList);
			} else {
				this.cache = dataCache.get(query).get();
			}

			this.lastModified = System.currentTimeMillis();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String post(HttpServletRequest req) throws IOException {
//		DataStore<Entity> store = datastoreHolder;
		InputStream in = req.getInputStream();
		if (log.isTraceEnabled()) {
			in = FileUtil.print(in);
		}
		FlowEngine engine = new FlowEngine(dataRepos, (Flow) Broker.valueOf(type));
		RuntimeContext context = new RuntimeContext();
		EditableEntity data = engine.start(context);
		
		jsonHolder.readFrom(data, new InputStreamReader(in));
		engine.stepSubmit(context, data);

		return req.getPathInfo() + data.getID();
	}
}

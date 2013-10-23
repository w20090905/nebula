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
import nebula.data.DataStore;
import nebula.data.DataWatcher;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;

import org.eclipse.jetty.util.URIUtil;

import util.FileUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public class EntityListResouce extends AbstractResouce {
	private final Broker<DataHelper<Entity, Reader, Writer>> jsonHolder;
	private final Broker<DataStore<Entity>> datastoreHolder;
	final LoadingCache<String, DataHolder> dataCache;

	class TreeDataHolder implements DataHolder {
		String parentFieldName;
		String value;

		public TreeDataHolder(String parentFieldName, String value) {
			this.parentFieldName = parentFieldName;
			this.value = checkNotNull(value);
		}

		public byte[] get() {
			List<Entity> dataList = datastoreHolder.get().listAll();
			List<Entity> to = Lists.newArrayList();

			for (Entity entity : dataList) {
				if (value.equals(entity.getID())) {
					to.add(entity);
					addChildren(entity, dataList, to);
				}
			}

			return buildFrom(to);
		}

		private List<Entity> addChildren(Entity root, List<Entity> from, List<Entity> to) {
			String id = (String) root.getID();
			for (Entity e : from) {
				if (id.equals(e.get(parentFieldName))) {
					to.add(e);
					addChildren(e, from, to);
				}
			}
			return to;
		}
	}

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
			jsonHolder.get().stringifyTo(data, new OutputStreamWriter(out));
		}
		out.append(']');

		out.flush();
		out.close();
		return bout.toByteArray();
	}

	public EntityListResouce(final Broker<Type> typeBroker, Broker<DataHelper<Entity, Reader, Writer>> json, Broker<DataStore<Entity>> datas) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.dataCache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, DataHolder>() {
			public DataHolder load(String query) {
				String[] params = query.split("&");
				DataStore<Entity> dataStore = datastoreHolder.get();
				Map<String, Classificator<String, Entity>> classificatores = dataStore.getClassificatores();
				String cKey = null;
				String cValue = null;
				for (String keyvalue : params) {
					String[] kv = keyvalue.split("=");
					String key = kv[0];
					String value = kv[1];
					if ("root".equals(key)) {
						Field c = null;
						for (Field f : typeBroker.get().getDeclaredFields()) {
							if (f.getRefer() == Reference.Cascade) {
								c = f;
							}
						}
						return new TreeDataHolder(c.getName() + typeBroker.get().getKeyField().getName(), value);
					} else if (classificatores.containsKey(key)) {
						cKey = key;
						cValue = value;
						return new DataHolderClassificator(classificatores.get(checkNotNull(cKey)), cValue);
					}
				}
				return null;
			}
		});

		json.addWatcher(new DataWatcher<DataHelper<Entity, Reader, Writer>>() {

			@Override
			public boolean onUpdate(DataHelper<Entity, Reader, Writer> newData, DataHelper<Entity, Reader, Writer> oldData) {
				dataCache.cleanUp();
				return false;
			}
		});
	}

	protected void get(HttpServletRequest req) {
		try {
			String query = URIUtil.decodePath(req.getQueryString());
			List<Entity> dataList;
			if (query == null || query.length() == 0) {
				dataList = datastoreHolder.get().listAll();
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
		DataStore<Entity> store = datastoreHolder.get();
		InputStream in = req.getInputStream();
		if (log.isTraceEnabled()) {
			in = FileUtil.print(in);
		}
		Entity inData = jsonHolder.get().readFrom(null, new InputStreamReader(in));

		store.add(inData);
		store.flush();
		return req.getPathInfo() + inData.getID();
	}
}

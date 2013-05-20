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

import nebula.data.Classificator;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Holder;
import nebula.data.json.DataHelper;

import org.eclipse.jetty.util.URIUtil;

import util.FileUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class EntityListResouce extends AbstractResouce {
	private final Holder<DataHelper<Entity, Reader, Writer>> jsonHolder;
	private final Holder<DataStore<Entity>> datastoreHolder;
	final EntityFilterBuilder filterBuilder;
	final LoadingCache<String, DataHolder> dataCache;

	class DataHolder {
		Classificator<String, Entity> classificator;
		String value;
		byte[] buffer;

		public DataHolder(Classificator<String, Entity> classificator, String value) {
			this.classificator = checkNotNull(classificator);
			this.value = checkNotNull(value);
		}

		byte[] get() {
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

	public EntityListResouce(Holder<DataHelper<Entity, Reader, Writer>> json, Holder<DataStore<Entity>> datas,
			final EntityFilterBuilder filterBuilder) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.filterBuilder = filterBuilder;
		this.dataCache = CacheBuilder.newBuilder().maximumSize(1000)
				.build(new CacheLoader<String, DataHolder>() {
					public DataHolder load(String query) {
						String[] params = query.split("&");
						DataStore<Entity> dataStore = datastoreHolder.get();
						Map<String, Classificator<String, Entity>> classificatores = dataStore.getClassificatores();
						String cKey = null;
						String cValue  = null;
						for (String keyvalue : params) {
							String[] kv = keyvalue.split("=");
							String key = kv[0];
							String value = kv[1];
							if (classificatores.containsKey(key)) {
								cKey = key;
								cValue = value;
								break;
							}
						}

						return new DataHolder(classificatores.get(checkNotNull(cKey)), cValue);
					}
				});
	}

	protected void get(HttpServletRequest req) {
		try {
			String query = URIUtil.decodePath(req.getQueryString());
			List<Entity> dataList;
			if (query == null || query.length() == 0) {
				dataList = datastoreHolder.get().all();
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

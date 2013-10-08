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

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.Classificator;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import util.FileUtil;

public class AttachedEntityListResouce extends AbstractResouce {
	private final Broker<DataHelper<Entity, Reader, Writer>> jsonHolder;
	private final Broker<DataStore<Entity>> datastoreHolder;
	// final LoadingCache<String, DataHolder> dataCache;
	DataHolder dataCached;

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

	public AttachedEntityListResouce(Broker<DataHelper<Entity, Reader, Writer>> json, Broker<DataStore<Entity>> datas, final String attachedToTypeName,
			final String attachToID) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.dataCached = new DataHolder(datastoreHolder.get().getClassificator(checkNotNull(attachedToTypeName)), attachToID);
	}

	protected void get(HttpServletRequest req) {
		this.cache = dataCached.get();
		this.lastModified = System.currentTimeMillis();
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

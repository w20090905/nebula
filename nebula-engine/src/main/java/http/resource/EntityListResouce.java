package http.resource;

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

import nebula.Filter;
import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;
import util.FileUtil;

public class EntityListResouce extends AbstractResouce {
	private final DataHolder<DataHelper<Entity, Reader, Writer>> jsonHolder;
	private final DataHolder<DataStore<Entity>> datastoreHolder;
	final EntityFilterBuilder filterBuilder;

	public EntityListResouce(DataHolder<DataHelper<Entity, Reader, Writer>> json, DataHolder<DataStore<Entity>> datas,
			final EntityFilterBuilder filterBuilder) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.filterBuilder = filterBuilder;
	}

	protected void get(HttpServletRequest req) {
		List<Entity> dataList;

		if (req.getQueryString() == null || req.getQueryString().length() == 0) {
			dataList = datastoreHolder.get().all();
		} else {
			Filter<Entity> filter = filterBuilder.buildFrom(req.getParameterMap(), null);
			dataList = datastoreHolder.get().query(filter);
		}

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
		this.lastModified = System.currentTimeMillis();
		this.cache = bout.toByteArray();
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

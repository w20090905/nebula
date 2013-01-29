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

import nebula.Filter;
import nebula.data.DataHolder;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;

import org.simpleframework.http.Address;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import util.FileUtil;

public class EntityListResouce extends AbstractResouce {
	private final DataHolder<DataHelper<Entity,Reader,Writer>> jsonHolder;
	private final DataHolder<DataStore<Entity>> datastoreHolder;
	final EntityFilterBuilder filterBuilder;

	public EntityListResouce(DataHolder<DataHelper<Entity,Reader,Writer>> json, DataHolder<DataStore<Entity>> datas, final EntityFilterBuilder filterBuilder) {
		super("text/json", 0, 1000);
		this.jsonHolder = json;
		this.datastoreHolder = datas;
		this.filterBuilder = filterBuilder;
	}

	protected void get(Address address) {
		Query query =address.getQuery();
		List<Entity> dataList;

		if (query.isEmpty()) {
			dataList = datastoreHolder.get().all();
		} else {
			Filter<Entity> filter = filterBuilder.buildFrom(query, null);
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
	protected String post(Request req) {
		try {
			DataStore<Entity> store = datastoreHolder.get();
			InputStream in = req.getInputStream();
			if (log.isTraceEnabled()) {
				in = FileUtil.print(in);
			}
			Entity inData = jsonHolder.get().readFrom(null, new InputStreamReader(in));
			
			store.add(inData);
			store.flush();
			return req.getAddress().getPath() + inData.getID();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

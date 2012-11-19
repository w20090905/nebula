package http.resource;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import nebula.Filter;
import nebula.data.Entity;
import nebula.data.Store;
import nebula.data.json.JsonHelper;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import util.FileUtil;

public class EntityListResouce extends AbstractResouce {
	private final JsonHelper<Entity> json;
	private final Store<Entity> datas;
	final EntityFilterBuilder filterBuilder;

	public EntityListResouce(JsonHelper<Entity> json, Store<Entity> datas, final EntityFilterBuilder filterBuilder) {
		this.json = json;
		this.datas = datas;
		this.filterBuilder = filterBuilder;
	}

	protected void get(Request req) {
		Query query = req.getAddress().getQuery();
		List<Entity> dataList;

		if (query.isEmpty()) {
			dataList = datas.all();
		} else {
			Filter<Entity> filter = filterBuilder.buildFrom(query, null);
			dataList = datas.query(filter);
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
			json.stringifyTo(data, new OutputStreamWriter(out));
		}
		out.append(']');

		out.flush();
		out.close();
		this.lastModified = System.currentTimeMillis();
		this.cache = bout.toByteArray();
	}

	@Override
	protected void post(Request req) {
		try {
			Entity data = datas.createNew();
			InputStream in = req.getInputStream();
			if (log.isTraceEnabled()) {
				in = FileUtil.print(in);
			}
			json.readFrom(data, new InputStreamReader(in));
			datas.add(data);
			datas.flush();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

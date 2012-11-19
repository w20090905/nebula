package http.resource;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import nebula.Filter;
import nebula.SmartList;
import nebula.data.json.JsonHelper;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class TypeListResouce extends AbstractResouce {
	private static Log log = LogFactory.getLog(TypeListResouce.class);
	private final JsonHelper<Type> json;
	private final SmartList<Type> types;
	final TypeLoader typeLoader;
	final TypeFilterBuilder filterBuilder;

	public TypeListResouce(TypeLoader typeLoader, JsonHelper<Type> json, TypeFilterBuilder filterBuilder) {
		this.types = typeLoader.all();
		this.typeLoader = typeLoader;
		this.json = json;
		this.filterBuilder = filterBuilder;
	}

	@Override
	protected void get(Request req) {
		Query query = req.getAddress().getQuery();
		List<Type> dataList;

		if (query.isEmpty()) {
			dataList = types;
		} else {
			Filter<Type> filter = filterBuilder.buildFrom(query, null);
			dataList = types.query(filter);
		}
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Type data : dataList) {
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
		System.out.println("in post");
		try {
			Type type = json.readFrom(null, new InputStreamReader(req.getInputStream()));
			type = typeLoader.update(type);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

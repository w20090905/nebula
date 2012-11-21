package http.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

import nebula.Filter;
import nebula.data.json.JsonHelper;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Address;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import util.FileUtil;

public class TypeListResouce extends AbstractResouce {
	private static Log log = LogFactory.getLog(TypeListResouce.class);
	private final JsonHelper<Type> json;
	final TypeLoader typeLoader;
	final TypeFilterBuilder filterBuilder;

	protected byte[] cacheAll;

	public TypeListResouce(TypeLoader typeLoader, JsonHelper<Type> json, TypeFilterBuilder filterBuilder) {
		super("text/json", 0, 0);
		this.typeLoader = typeLoader;
		this.json = json;
		this.filterBuilder = filterBuilder;
	}

	@Override
	protected void get(Address address) {
		Query query = address.getQuery();
		List<Type> dataList;

		if (query.isEmpty()) {
			long newModified = typeLoader.getLastModified();
			if (newModified == this.lastModified) {
				super.cache = this.cacheAll;
				return;
			}		
			this.lastModified = newModified;
			dataList = typeLoader.all();
		} else {
			Filter<Type> filter = filterBuilder.buildFrom(query, null);
			dataList = typeLoader.all().query(filter);
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
		this.cache = bout.toByteArray();

		if (query.isEmpty()) {
			this.cacheAll = bout.toByteArray();
		}
	}

	protected String post(Request req) throws IOException {
		BufferedInputStream bio = new BufferedInputStream(req.getInputStream());
		if (log.isTraceEnabled()) {
			log.trace("Input stream : ");
			log.trace(FileUtil.readAllTextFrom(bio));
		}

		String newCode = FileUtil.readAllTextFrom(bio);
		Type newType = typeLoader.update(null, newCode);
		return req.getAddress().getPath() + newType.getName();
	}
}

package http.engine;

import http.json.JsonProvider;
import http.json.JsonProvider.JsonSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import nebula.SmartList;
import nebula.data.Entity;
import nebula.lang.EditableTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.resource.Resource;

import util.FileUtil;

public class TypeListResouce extends BasicResouce {
	private static Log log = LogFactory.getLog(TypeListResouce.class);
	private final JsonSerializer<Type> json;
	private final SmartList<Type> types;
	final TypeLoader typeLoader;

	@SuppressWarnings("unchecked")
	public TypeListResouce(TypeLoader typeLoader, SmartList<?> datas,JsonSerializer<Type> json) {
		this.types = (SmartList<Type>) datas;
		this.typeLoader = typeLoader;
		this.json = json;
	}

	@Override
	protected void makeResponse() {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);

		boolean start = true;
		out.append('[');
		for (Type data : types) {
			if (!start) {
				out.append(',');
			} else {
				start = false;
			}
			json.stringifyTo(data, out);
		}
		out.append(']');

		out.flush();
		out.close();
		this.lastModified = System.currentTimeMillis();
		this.buffer = bout.toByteArray();
	}

	@Override
	protected void post(Request req) {
		System.out.println("in post");
		try {
			Type type = json.readFrom(null, req.getInputStream());			
			type = typeLoader.update(type);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

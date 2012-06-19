package http.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nebula.data.Entity;
import nebula.data.mem.EntitySerialize;
import nebula.lang.Type;
import nebula.lang.TypeSerialize;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class JsonProvider {
	@SuppressWarnings("unchecked")
	public static <T> JsonSerializer<T> getSerialize(Class<T> clz) {
		JsonSerializer<T> s;
		if (clz == Type.class) {
			JsonSerialize<T> json = (JsonSerialize<T>) new TypeSerialize();
			s = new DefaultJsonSerializer<T>(json);

		} else if (clz == Entity.class) {
			JsonSerialize<T> json = (JsonSerialize<T>) new EntitySerialize();
			s = new DefaultJsonSerializer<T>(json);
		} else {
			s = null;
		}

		return s;
	}

	public static interface JsonSerializer<T> {
		void stringifyTo(T d, OutputStream o);

		T readFrom(T d, InputStream in);
	}

	static class DefaultJsonSerializer<T> implements JsonSerializer<T> {
		final JsonSerialize<T> json;

		DefaultJsonSerializer(JsonSerialize<T> json) {
			this.json = json;
		}

		@Override
		public void stringifyTo(T d, OutputStream o) {
			try {
				JsonFactory f = new JsonFactory();
				JsonGenerator g = f.createJsonGenerator(o);
				json.write(g, d);
				g.flush();
				// g.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public T readFrom(T d, InputStream in) {
			try {
				JsonFactory f = new JsonFactory();
				JsonParser p = f.createJsonParser(in);
				return json.read(p, d);
				// p.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}

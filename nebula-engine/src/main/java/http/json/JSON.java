package http.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nebula.lang.TypeSerialize;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class JSON {
	@SuppressWarnings("unchecked")
	public static <T> JsonSerializer<T> getSerialize(Class<T> clz) {
		JsonSerialize<T> json = (JsonSerialize<T>) new TypeSerialize();
		return new DefaultJsonSerializer<T>(json);
	}

	public static interface JsonSerializer<T> {
		void stringifyTo(T d, OutputStream o);

		void readFrom(T d, InputStream in);
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
				g.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void readFrom(T d, InputStream in) {
			try {
				JsonFactory f = new JsonFactory();
				JsonParser p = f.createJsonParser(in);
				json.read(p, d);
				p.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}

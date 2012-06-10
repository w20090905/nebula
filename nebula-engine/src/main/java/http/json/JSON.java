package http.json;

import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

public class JSON {
	@SuppressWarnings("unchecked")
	public static <T> JsonSerializer<T> getSerialize(Class<T> clz) {
		JsonSerialize<T> json = (JsonSerialize<T>) new TypeSerialize();
		return new DefaultJsonSerializer<T>(json);
	}

	public static interface JsonSerializer<T> {
		void stringifyTo(T d, OutputStream o);
		// void readFrom(InputStream i, T d);
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

	}
}

package http.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public interface JsonSerialize<T> {
	void write(JsonGenerator o, T d);

	T read(JsonParser p,T d);
}

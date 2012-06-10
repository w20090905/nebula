package http.json;

import org.codehaus.jackson.JsonGenerator;

public interface JsonSerialize<T> {
	void write(JsonGenerator o, T d);

	void read(JsonGenerator i, T d);
}

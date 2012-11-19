package nebula.data.json;

import java.io.Reader;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public interface JsonHelper<T> {
	T read(JsonParser o, T d) ;
	void write(JsonGenerator o, T d) ;

	T readFrom(T d, Reader in);
	void stringifyTo(T d, Writer o);
}
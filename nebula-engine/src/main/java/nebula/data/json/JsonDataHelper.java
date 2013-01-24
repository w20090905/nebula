package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;


public interface JsonDataHelper<T> extends DataHelper<T, JsonParser, JsonGenerator>{
	T readFrom(T d, JsonParser in);
	void stringifyTo(T d, JsonGenerator o);
}
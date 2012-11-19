package http.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public interface JsonBasicTypeDealer {
	Object readFrom(JsonParser res) throws Exception;
	void writeTo(Object value, String pageName, JsonGenerator res) throws Exception;
}

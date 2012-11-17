package http.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;


public interface JsonDataDealer {
	Object readFrom(JsonParser res);
	void writeTo(Object value,String pageName, JsonGenerator res);
}

package http.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class LongJsonDataDealer implements JsonDataDealer {

	@Override
	public Object readFrom(JsonParser res) {
		try {
			return res.getLongValue();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeNumberField(pageName, value != null ? (Long) value : 0L);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

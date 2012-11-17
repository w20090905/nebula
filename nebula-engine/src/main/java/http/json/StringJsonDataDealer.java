package http.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class StringJsonDataDealer implements JsonDataDealer {

	@Override
	public Object readFrom(JsonParser res) {
		try {
			return res.getText();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeStringField(pageName, value!=null?(String)value:"");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

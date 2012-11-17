package http.json;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class DecimalJsonDataDealer implements JsonDataDealer {

	@Override
	public Object readFrom(JsonParser res) {
		try {
			return res.getDecimalValue();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeNumberField(pageName, value!=null?(BigDecimal)value:new BigDecimal(0));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

package http.json;

import java.io.IOException;
import java.sql.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class DateJsonDataDealer implements JsonDataDealer {

	@Override
	public Date readFrom(JsonParser res) {
		try {
			Date v = null;
			String vs = res.getText();
			if (vs.length() >= 10) {
				v = Date.valueOf(vs);
			}
			return v;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeStringField(pageName,  value!=null?value.toString():"");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

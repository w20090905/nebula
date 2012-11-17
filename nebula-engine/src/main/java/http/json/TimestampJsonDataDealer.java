package http.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class TimestampJsonDataDealer implements JsonDataDealer{

	DateFormat formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:DD.sss");

	@Override
	public Object readFrom(JsonParser res) {
		try {
			Timestamp v = null;
			String vs = res.getText();
			if (vs.length() >= 22) {
				v = Timestamp.valueOf(vs);
			}
			return v;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeStringField(pageName, value!=null?value.toString():"");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

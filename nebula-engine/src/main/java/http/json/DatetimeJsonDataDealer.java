package http.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class DatetimeJsonDataDealer implements JsonDataDealer {

	DateFormat formater = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	@Override
	public Object readFrom(JsonParser res) {
		try {
			Timestamp v = null;
			String vs = res.getText();
			if (vs.length() >= 19) {
				v = new Timestamp(formater.parse(vs).getTime());
			}
			return v;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(Object value, String pageName, JsonGenerator res) {
		try {
			res.writeStringField(pageName,  value!=null?formater.format((Timestamp)value):"");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

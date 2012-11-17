package http.json;

import java.io.IOException;
import java.sql.Time;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class TimeJsonDataDealer implements JsonDataDealer{
	
	@Override
	public Object readFrom(JsonParser res) {
		try {
			Time v = null;
			String vs = res.getText();
			if(vs.length()>=8){
				v = Time.valueOf(vs);
			}
			return  v;
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

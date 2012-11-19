package http.json;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import nebula.lang.RawTypes;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class PageField {
	public PageField(String name, String pageName, RawTypes innerType) {
		this.name = name;
		this.pageName = pageName;
		this.innerType = innerType;

		switch (this.innerType) {
		case Long:
			this.dataDealer = new LongJsonDataDealer();
			break;
		case Decimal:
			this.dataDealer = new DecimalJsonDataDealer();
			break;
		case String:
			this.dataDealer = new StringJsonDataDealer();
			break;
		case Date:
			this.dataDealer = new DateJsonDataDealer();
			break;
		case Time:
			this.dataDealer = new TimeJsonDataDealer();
			break;
		case Datetime:
			this.dataDealer = new DatetimeJsonDataDealer();
			break;
		case Timestamp:
			this.dataDealer = new TimestampJsonDataDealer();
			break;
		default:
			break;
		}
	}

	public String name;
	public String pageName;
	public RawTypes innerType;
	public JsonBasicTypeDealer dataDealer;
}

class DateJsonDataDealer implements JsonBasicTypeDealer {
	public Date readFrom(JsonParser res) throws Exception {
		return res.getText().length() >= 10 ? Date.valueOf(res.getText()) : null;
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeStringField(pageName, value != null ? value.toString() : "");
	}
}

class TimeJsonDataDealer implements JsonBasicTypeDealer {
	public Object readFrom(JsonParser res) throws Exception {
		return res.getText().length() >= 8 ? Time.valueOf(res.getText()) : null;
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeStringField(pageName, value != null ? value.toString() : "");
	}
}

class DatetimeJsonDataDealer implements JsonBasicTypeDealer {
	final static DateFormat formater = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public Object readFrom(JsonParser res) throws Exception {
		return res.getText().length() >= 19 ? new Timestamp(formater.parse(res.getText()).getTime()) : null;
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeStringField(pageName, value != null ? formater.format((Timestamp) value) : "");
	}
}

class TimestampJsonDataDealer implements JsonBasicTypeDealer {
	final static DateFormat formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:DD.sss");
	public Object readFrom(JsonParser res) throws Exception {
		return res.getText().length() >= 22 ? Timestamp.valueOf(res.getText()) : null;
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeStringField(pageName, value != null ? value.toString() : "");
	}
}

class LongJsonDataDealer implements JsonBasicTypeDealer {
	public Object readFrom(JsonParser res) throws Exception {
		return res.getLongValue();
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeNumberField(pageName, value != null ? (Long) value : 0L);
	}
}

class DecimalJsonDataDealer implements JsonBasicTypeDealer {
	public Object readFrom(JsonParser res) throws Exception {
		return res.getDecimalValue();
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeNumberField(pageName, value != null ? (BigDecimal) value : new BigDecimal(0));
	}
}

class StringJsonDataDealer implements JsonBasicTypeDealer {
	public Object readFrom(JsonParser res) throws Exception {
		return res.getText();
	}
	public void writeTo(Object value, String pageName, JsonGenerator res) throws Exception {
		res.writeStringField(pageName, value != null ? (String) value : "");
	}
}

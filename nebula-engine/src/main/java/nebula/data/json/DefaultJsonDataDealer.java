package nebula.data.json;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

abstract class DefaultJsonDataDealer<T> implements JsonDataDealer<T> {
	public T readFrom(JsonParser in, int index) throws Exception {
		throw new UnsupportedOperationException("readFrom(JsonParser in,int index)");
	}

	public void writeTo(int index, Object value, JsonGenerator gen) throws Exception {
		throw new UnsupportedOperationException("writeTo(int index, Object value,JsonGenerator gen)");
	}
}

class BooleanJsonDataDealer extends DefaultJsonDataDealer<Boolean> {
	public Boolean readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_TRUE || token == JsonToken.VALUE_FALSE;
		return parser.getValueAsBoolean();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeBoolean(value != null ? (Boolean) value : false);
	}
}

class LongJsonDataDealer extends DefaultJsonDataDealer<Long> {
	public Long readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_NUMBER_INT;
		return parser.getLongValue();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeNumber(value != null ? (Long) value : 0L);
	}
}

class DecimalJsonDataDealer extends DefaultJsonDataDealer<BigDecimal> {
	public BigDecimal readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_NUMBER_FLOAT;
		return parser.getDecimalValue();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeNumber(value != null ? (BigDecimal) value : new BigDecimal(0));
	}
}

class StringJsonDataDealer extends DefaultJsonDataDealer<String> {
	public String readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? (String) value : "");
	}
}

class TextBlockJsonDataDealer extends DefaultJsonDataDealer<String> {
	public String readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? (String) value : "");
	}
}

class DateJsonDataDealer extends DefaultJsonDataDealer<Date> {
	public Date readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 10 ? Date.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? value.toString() : "");
	}
}

class TimeJsonDataDealer extends DefaultJsonDataDealer<Time> {
	public Time readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 8 ? Time.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? value.toString() : "");
	}
}

class DatetimeJsonDataDealer extends DefaultJsonDataDealer<Timestamp> {
	final DateFormat formater = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public Timestamp readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 19 ? new Timestamp(formater.parse(parser.getText()).getTime()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? formater.format((Timestamp) value) : "");
	}
}

class TimestampJsonDataDealer extends DefaultJsonDataDealer<Timestamp> {
	final DateFormat formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:DD.sss");

	public Timestamp readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 22 ? Timestamp.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? value.toString() : "");
	}
}

class ListJsonDataDealer<T> extends DefaultJsonDataDealer<List<T>> {
	final JsonDataDealer<T> jsonFieldDealer;

	public ListJsonDataDealer(JsonDataDealer<T> jsonFieldDealer) {
		this.jsonFieldDealer = jsonFieldDealer;
	}

	public List<T> readFrom(JsonParser parser, String name) throws Exception {
		List<T> vList = new ArrayList<>();
		JsonToken token;
		token = parser.getCurrentToken();
		assert token == JsonToken.START_ARRAY;
		
		while ((token=parser.nextToken()) != JsonToken.END_ARRAY) {
			vList.add(jsonFieldDealer.readFrom(parser, name));
		}
		assert token == JsonToken.END_ARRAY;
		return vList;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {

		gen.writeStartArray();

		@SuppressWarnings("unchecked")
		List<T> entityList = (List<T>) value;
		for (T entity : entityList) {
			jsonFieldDealer.writeTo(name, entity, gen);
		}

		gen.writeEndArray();
	}
}

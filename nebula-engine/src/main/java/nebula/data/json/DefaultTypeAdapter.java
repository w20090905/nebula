package nebula.data.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import nebula.data.TypeAdapter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

abstract class DefaultTypeAdapter<T> implements TypeAdapter<T, JsonParser, JsonGenerator> {
	public T readFrom(JsonParser in, int index) throws Exception {
		throw new UnsupportedOperationException("readFrom(JsonParser in,int index)");
	}

	public void writeTo(int index, Object value, JsonGenerator gen) throws Exception {
		throw new UnsupportedOperationException("writeTo(int index, Object value,JsonGenerator gen)");
	}
}

class BooleanJsonDataDealer extends DefaultTypeAdapter<Boolean> {
	public Boolean readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_TRUE || token == JsonToken.VALUE_FALSE;
		return parser.getValueAsBoolean();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeBoolean(value != null ? (Boolean) value : false);
	}
}

class LongJsonDataDealer extends DefaultTypeAdapter<Long> {
	public Long readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_NUMBER_INT;
		return parser.getLongValue();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeNumber(value != null ? (Long) value : 0L);
	}
}

class DecimalJsonDataDealer extends DefaultTypeAdapter<BigDecimal> {
	public BigDecimal readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_NUMBER_FLOAT;
		return parser.getDecimalValue();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeNumber(value != null ? (BigDecimal) value : new BigDecimal(0));
	}
}

class StringJsonDataDealer extends DefaultTypeAdapter<String> {
	public String readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? (String) value : "");
	}
}

class TextBlockJsonDataDealer extends DefaultTypeAdapter<String> {
	public String readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText();
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? (String) value : "");
	}
}

class DateJsonDataDealer extends DefaultTypeAdapter<DateTime> {
	final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd");
	public DateTime readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 10 ? formater.parseDateTime(parser.getText()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? formater.print((ReadableInstant)value) : "");
	}
}

class TimeJsonDataDealer extends DefaultTypeAdapter<DateTime> {
	final DateTimeFormatter formaterHHMMSS = DateTimeFormat.forPattern("kk:mm:ss");
	final DateTimeFormatter formaterHHMM = DateTimeFormat.forPattern("kk:mm");
	final DateTimeFormatter formaterHH = DateTimeFormat.forPattern("kk");
	public DateTime readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		String value = parser.getText();
		int len = value.length();
		if(len==8){
			return formaterHHMMSS.parseDateTime(parser.getText());	
		}else 		if(len==5){
			return formaterHHMM.parseDateTime(parser.getText());	
		}else 		if(len==2){
			return formaterHH.parseDateTime(parser.getText());	
		}else 		if(len==1){
			return formaterHH.parseDateTime(parser.getText());	
		}else {
			return null;
		}
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? formaterHHMMSS.print((ReadableInstant)value) : "");
	}
}

class DatetimeJsonDataDealer extends DefaultTypeAdapter<DateTime> {
	final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd kk:mm:ss");

	public DateTime readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 19 ? formater.parseDateTime(parser.getText()) : null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? formater.print((ReadableInstant)value) : "");
	}
}

class TimestampJsonDataDealer extends DefaultTypeAdapter<Long> {
	final DateTimeFormatter formater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

	public Long readFrom(JsonParser parser, String name) throws Exception {
		JsonToken token = parser.getCurrentToken();
		assert token == JsonToken.VALUE_STRING;
		return parser.getText().length() >= 22 ? formater.parseDateTime(parser.getText()).getMillis(): null;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {
		gen.writeString(value != null ? formater.print(new DateTime((Long)value)) : "");
	}
}

class ListJsonDataDealer<T> extends DefaultTypeAdapter<List<T>> {
	final DefaultTypeAdapter<T> jsonFieldDealer;

	public ListJsonDataDealer(DefaultTypeAdapter<T> jsonFieldDealer) {
		this.jsonFieldDealer = jsonFieldDealer;
	}

	public List<T> readFrom(JsonParser parser, String name) throws Exception {
		List<T> vList = new ArrayList<T>();
		JsonToken token;
		token = parser.getCurrentToken();
		assert token == JsonToken.START_ARRAY;

		while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
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

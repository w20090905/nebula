package nebula.data.json;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import nebula.data.BasicFieldDealer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

abstract class DefaultJsonFieldDealer<T> implements BasicFieldDealer<T,JsonParser,JsonGenerator> {
	public T readFrom(JsonParser in,int index) throws Exception {
		throw new UnsupportedOperationException("readFrom(JsonParser in,int index)");
	}

	public void writeTo(int index, Object value,JsonGenerator gen) throws Exception {
		throw new UnsupportedOperationException("writeTo(int index, Object value,JsonGenerator gen)");
	}
}

class BooleanJsonDataDealer extends DefaultJsonFieldDealer<Boolean> {
	public Boolean readFrom(JsonParser parser,String name)throws Exception {
		return parser.getValueAsBoolean();
	}
	
	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeBooleanField(name, value != null ? (Boolean) value : false);
	}
}

class LongJsonDataDealer extends DefaultJsonFieldDealer<Long> {
	public Long readFrom(JsonParser parser,String name)throws Exception {
		return parser.getLongValue();
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeNumberField(name, value != null ? (Long) value : 0L);
	}
}

class DecimalJsonDataDealer extends DefaultJsonFieldDealer<BigDecimal> {
	public BigDecimal readFrom(JsonParser parser,String name)throws Exception {
		return parser.getDecimalValue();
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeNumberField(name, value != null ? (BigDecimal) value : new BigDecimal(0));
	}
}

class StringJsonDataDealer extends DefaultJsonFieldDealer<String> {
	public String readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText();
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? (String) value : "");
	}
}

class TextBlockJsonDataDealer extends DefaultJsonFieldDealer<String> {
	public String readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText();
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? (String) value : "");
	}
}

class DateJsonDataDealer extends DefaultJsonFieldDealer<Date> {
	public Date readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText().length() >= 10 ? Date.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? value.toString() : "");
	}
}

class TimeJsonDataDealer extends DefaultJsonFieldDealer<Time> {
	public Time readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText().length() >= 8 ? Time.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? value.toString() : "");
	}
}

class DatetimeJsonDataDealer extends DefaultJsonFieldDealer<Timestamp> {
	final DateFormat formater = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public Timestamp readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText().length() >= 19 ? new Timestamp(formater.parse(parser.getText()).getTime()) : null;
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? formater.format((Timestamp) value) : "");
	}
}

class TimestampJsonDataDealer extends DefaultJsonFieldDealer<Timestamp> {
	final DateFormat formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:DD.sss");

	public Timestamp readFrom(JsonParser parser,String name)throws Exception {
		return parser.getText().length() >= 22 ? Timestamp.valueOf(parser.getText()) : null;
	}

	public void writeTo(String name, Object value,JsonGenerator gen) throws Exception {
		gen.writeStringField(name, value != null ? value.toString() : "");
	}
}

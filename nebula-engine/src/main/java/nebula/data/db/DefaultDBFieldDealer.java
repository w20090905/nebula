package nebula.data.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;

import nebula.data.BasicFieldDealer;

abstract class DefaultDBFieldDealer<T> implements BasicFieldDealer<T, ResultSet, PreparedStatement> {
	public T readFrom(ResultSet in, String name) throws Exception {
		throw new UnsupportedOperationException("readFrom(ResultSet in, String name)");
	}
	public void writeTo(String name, Object value, PreparedStatement gen) throws Exception {
		throw new UnsupportedOperationException("writeTo(int index,T value)");
	}
}

class BooleanDataDealer extends DefaultDBFieldDealer<Boolean> {
	@Override
	public Boolean readFrom(ResultSet res, int index) throws Exception {
		return res.getBoolean(index);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setBoolean(index, v != null ? (Boolean) v : false);
	}
}

class Long_BigInt_DataDealer extends DefaultDBFieldDealer<Long> {
	@Override
	public Long readFrom(ResultSet res, int index) throws Exception {
		return res.getLong(index);
	}
	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setLong(index, v != null ? (Long) v : 0);
	}
}

class DecimalDealer extends DefaultDBFieldDealer<BigDecimal> {
	@Override
	public BigDecimal readFrom(ResultSet res, int i) throws Exception {
		return res.getBigDecimal(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setBigDecimal(index, (BigDecimal) v);
	}
}

class TextBlock_Varchar_DataDealer extends DefaultDBFieldDealer<String> {
	@Override
	public String readFrom(ResultSet res, int i) throws Exception {
		return res.getString(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setString(index, (String) v);
	}
}

class String_Varchar_DataDealer extends DefaultDBFieldDealer<String> {
	@Override
	public String readFrom(ResultSet res, int i) throws Exception {
		return res.getString(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setString(index, (String) v);
	}
}

class DateDataDealer extends DefaultDBFieldDealer<Date> {
	@Override
	public Date readFrom(ResultSet res, int i) throws Exception {
		return res.getDate(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setDate(index, (Date) v);
	}
}

class TimeDataDealer extends DefaultDBFieldDealer<Time> {
	@Override
	public Time readFrom(ResultSet res, int i) throws Exception {
		return res.getTime(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTime(index, (Time) v);
	}
}

class DatetimeDataDealer extends DefaultDBFieldDealer<Timestamp> {
	@Override
	public Timestamp readFrom(ResultSet res, int i) throws Exception {
		return res.getTimestamp(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTimestamp(index, (Timestamp) v);
	}
}

class TimestampDataDealer extends DefaultDBFieldDealer<Timestamp> {
	@Override
	public Timestamp readFrom(ResultSet res, int i) throws Exception {
		return res.getTimestamp(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTimestamp(index, (Timestamp) v);
	}
}

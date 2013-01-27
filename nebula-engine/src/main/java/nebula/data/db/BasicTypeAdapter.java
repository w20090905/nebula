package nebula.data.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.EnumMap;

import nebula.data.TypeAdapter;
import nebula.lang.RawTypes;

abstract class BasicTypeAdapter<T extends Object> implements TypeAdapter<T, ResultSet, PreparedStatement> {
	public T readFrom(ResultSet in, String name) throws Exception {
		throw new UnsupportedOperationException("readFrom(ResultSet in, String name)");
	}

	public void writeTo(String name, Object value, PreparedStatement gen) throws Exception {
		throw new UnsupportedOperationException("writeTo(int index,T value)");
	}

	static EnumMap<RawTypes, BasicTypeAdapter<?>> typeMaps = new EnumMap<RawTypes, BasicTypeAdapter<?>>(RawTypes.class);
	static {
		typeMaps.put(RawTypes.Boolean, new DbBooleanTypeAdapter());
		typeMaps.put(RawTypes.Long, new DbLong_BigInt_TypeAdapter());
		typeMaps.put(RawTypes.Decimal, new DbDecimalDealer());
		typeMaps.put(RawTypes.String, new DbString_Varchar_TypeAdapter());
		typeMaps.put(RawTypes.Text, new DbTextBlock_Varchar_TypeAdapter());
		typeMaps.put(RawTypes.Date, new DbDateTypeAdapter());
		typeMaps.put(RawTypes.Time, new DbTimeTypeAdapter());
		typeMaps.put(RawTypes.Datetime, new DbTimestampTypeAdapter());
		typeMaps.put(RawTypes.Timestamp, new DbTimestampTypeAdapter());
	}
}

class DbBooleanTypeAdapter extends BasicTypeAdapter<Boolean> {
	@Override
	public Boolean readFrom(ResultSet res, int index) throws Exception {
		return res.getBoolean(index);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setBoolean(index, v != null ? (Boolean) v : false);
	}
}

class DbLong_BigInt_TypeAdapter extends BasicTypeAdapter<Long> {
	@Override
	public Long readFrom(ResultSet res, int index) throws Exception {
		return res.getLong(index);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setLong(index, v != null ? (Long) v : 0);
	}
}

class DbDecimalDealer extends BasicTypeAdapter<BigDecimal> {
	@Override
	public BigDecimal readFrom(ResultSet res, int i) throws Exception {
		return res.getBigDecimal(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setBigDecimal(index, (BigDecimal) v);
	}
}

class DbTextBlock_Varchar_TypeAdapter extends BasicTypeAdapter<String> {
	@Override
	public String readFrom(ResultSet res, int i) throws Exception {
		return res.getString(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setString(index, (String) v);
	}
}

class DbString_Varchar_TypeAdapter extends BasicTypeAdapter<String> {
	@Override
	public String readFrom(ResultSet res, int i) throws Exception {
		return res.getString(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setString(index, (String) v);
	}
}

class DbDateTypeAdapter extends BasicTypeAdapter<Date> {
	@Override
	public Date readFrom(ResultSet res, int i) throws Exception {
		return res.getDate(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setDate(index, (Date) v);
	}
}

class DbTimeTypeAdapter extends BasicTypeAdapter<Time> {
	@Override
	public Time readFrom(ResultSet res, int i) throws Exception {
		return res.getTime(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTime(index, (Time) v);
	}
}

class DbDatetimeTypeAdapter extends BasicTypeAdapter<Timestamp> {
	@Override
	public Timestamp readFrom(ResultSet res, int i) throws Exception {
		return res.getTimestamp(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTimestamp(index, (Timestamp) v);
	}
}

class DbTimestampTypeAdapter extends BasicTypeAdapter<Timestamp> {
	@Override
	public Timestamp readFrom(ResultSet res, int i) throws Exception {
		return res.getTimestamp(i);
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) throws Exception {
		res.setTimestamp(index, (Timestamp) v);
	}
}

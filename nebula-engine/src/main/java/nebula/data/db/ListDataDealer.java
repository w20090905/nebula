package nebula.data.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Pattern;

import nebula.lang.RawTypes;

public abstract class ListDataDealer<T> extends BasicTypeAdapter<List<T>> {

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
	};

	static class DbBooleanTypeAdapter extends ListDataDealer<Boolean> {
		@Override
		public List<Boolean> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Boolean>();

			String[] strValues = strValue.split(",");
			List<Boolean> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(Boolean.parseBoolean(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Boolean> values = (List<Boolean>) value;

			StringBuilder sb = new StringBuilder();

			for (Boolean v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbLong_BigInt_TypeAdapter extends ListDataDealer<Long> {
		@Override
		public List<Long> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Long>();
			String[] strValues = strValue.split(",");
			List<Long> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(Long.parseLong(v, 10));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Long> values = (List<Long>) value;

			StringBuilder sb = new StringBuilder();

			for (Long v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbDecimalDealer extends ListDataDealer<BigDecimal> {
		@Override
		public List<BigDecimal> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<BigDecimal>();
			String[] strValues = strValue.split(",");
			List<BigDecimal> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(new BigDecimal(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<BigDecimal> values = (List<BigDecimal>) value;

			StringBuilder sb = new StringBuilder();

			for (BigDecimal v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbTextBlock_Varchar_TypeAdapter extends ListDataDealer<String> {
		Pattern sep = Pattern.compile("\\]\\]\\^\\~\\[\\[");

		@Override
		public List<String> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<String>();

			String[] strValues = sep.split(strValue, 0);

			List<String> values = new ArrayList<>(strValues.length);
			for (String v : strValues) {
				values.add(v);
			}
			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<String> values = (List<String>) value;

			StringBuilder sb = new StringBuilder();

			for (String v : values) {
				sb.append(v);
				sb.append("]]^~[[");
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 6));
			} else {
				res.setString(index, "");
			}

		}
	}

	static class DbString_Varchar_TypeAdapter extends ListDataDealer<String> {
		Pattern sep = Pattern.compile("(\\]\\]\\^\\~\\[\\[)");

		@Override
		public List<String> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<String>();
			String[] strValues = sep.split(strValue, 0);
			List<String> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {
				values.add(v);
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<String> values = (List<String>) value;

			StringBuilder sb = new StringBuilder();

			for (String v : values) {
				sb.append(v);
				sb.append("]]^~[[");
			}
			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 6));
			} else {
				res.setString(index, "");
			}

		}
	}

	static class DbDateTypeAdapter extends ListDataDealer<Date> {
		@Override
		public List<Date> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Date>();
			String[] strValues = strValue.split(",");
			List<Date> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(Date.valueOf(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Date> values = (List<Date>) value;

			StringBuilder sb = new StringBuilder();

			for (Date v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbTimeTypeAdapter extends ListDataDealer<Time> {
		@Override
		public List<Time> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Time>();
			String[] strValues = strValue.split(",");
			List<Time> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(Time.valueOf(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Time> values = (List<Time>) value;

			StringBuilder sb = new StringBuilder();

			for (Time v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbDatetimeTypeAdapter extends ListDataDealer<Timestamp> {
		@Override
		public List<Timestamp> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Timestamp>();

			String[] strValues = strValue.split(",");
			List<Timestamp> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {

				values.add(Timestamp.valueOf(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Timestamp> values = (List<Timestamp>) value;

			StringBuilder sb = new StringBuilder();

			for (Timestamp v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}

	static class DbTimestampTypeAdapter extends ListDataDealer<Timestamp> {
		@Override
		public List<Timestamp> readFrom(ResultSet res, int index) throws Exception {
			String strValue = res.getString(index);
			if (strValue == null || strValue.length() == 0) return new ArrayList<Timestamp>();
			String[] strValues = strValue.split(",");
			List<Timestamp> values = new ArrayList<>(strValues.length);

			for (String v : strValues) {
				values.add(Timestamp.valueOf(v));
			}

			return values;
		}

		@Override
		public void writeTo(int index, Object value, PreparedStatement res) throws Exception {
			if (value == null) {
				res.setString(index, "");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Timestamp> values = (List<Timestamp>) value;

			StringBuilder sb = new StringBuilder();

			for (Timestamp v : values) {
				sb.append(v);
				sb.append(',');
			}

			if (sb.length() > 0) {
				res.setString(index, sb.substring(0, sb.length() - 1));
			} else {
				res.setString(index, "");
			}
		}
	}
}

package nebula.data.db;

import java.sql.Types;
import java.util.EnumMap;

import nebula.lang.RawTypes;

public class DatabaseColumn{
	public final int jdbcType;

	static EnumMap<RawTypes, Integer> dbTypeMap = new EnumMap<RawTypes, Integer>(RawTypes.class);
	static {
		dbTypeMap.put(RawTypes.Boolean, Types.BOOLEAN);
		dbTypeMap.put(RawTypes.Long, Types.BIGINT);
		dbTypeMap.put(RawTypes.Decimal, Types.DECIMAL);
		dbTypeMap.put(RawTypes.String, Types.VARCHAR);
		dbTypeMap.put(RawTypes.Text, Types.VARCHAR);
		dbTypeMap.put(RawTypes.Date, Types.DATE);
		dbTypeMap.put(RawTypes.Time, Types.TIME);
		dbTypeMap.put(RawTypes.Datetime, Types.TIMESTAMP);
		dbTypeMap.put(RawTypes.Timestamp, Types.TIMESTAMP);
	}

	public DatabaseColumn(String fieldName, String columnName, boolean key, boolean nullable, boolean array,
			RawTypes rawType, long size, int precision, int scale) {

		this.fieldName = fieldName;
		this.columnName = columnName;
		this.key = key;
		this.nullable = nullable;
		this.array = array;

		this.rawType = rawType;

		this.precision = precision;
		this.scale = scale;

		if (array) {
			this.size = 4000;
			this.jdbcType = Types.VARCHAR;
		} else {
			this.size = size;
			this.jdbcType = dbTypeMap.get(rawType);
		}

	}

	// String fieldName, String columnName, boolean key, boolean nullable,
	// RawTypes rawType,long size,
	// int precision, int scale

	protected final String fieldName;
	protected final String columnName;
	protected final boolean key;
	protected final boolean nullable;
	protected final boolean array;
	protected final RawTypes rawType;
	protected final long size;
	protected final int precision;
	protected final int scale;

	@Override
	public String toString() {
		return "DbColumn [fieldName=" + fieldName + ", columnName=" + columnName + ", rawType=" + rawType + ", size="
				+ size + ", precision=" + precision + ", scale=" + scale + ", key=" + key + "]";
	}

}

package nebula.data.db;

import java.sql.Types;

import nebula.lang.RawTypes;

public class DbColumn {
	public DbColumn(String fieldName, String columnName, boolean key, boolean nullable, RawTypes rawType,long size,
			int precision, int scale) {

		this.fieldName = fieldName;
		this.columnName = columnName;
		this.key = key;
		this.nullable = nullable;

		this.rawType = rawType;
		
		this.size = size;
		this.precision = precision;
		this.scale = scale;

		switch (rawType) {
		case Boolean:
			datadealer = new DbBooleanTypeAdapter();
			jdbcType = Types.BOOLEAN;
			break;
		case Long:
			datadealer = new DbLong_BigInt_TypeAdapter();
			jdbcType = Types.BIGINT;
			break;
		case Decimal:
			datadealer = new DbDecimalDealer();
			jdbcType = Types.DECIMAL;
			break;
		case String:
			datadealer = new DbString_Varchar_TypeAdapter();
			jdbcType = Types.VARCHAR;
			break;
		case Text:
			datadealer = new DbTextBlock_Varchar_TypeAdapter();
			jdbcType = Types.VARCHAR;			
			break;
		case Date:
			datadealer = new DbDateTypeAdapter();
			jdbcType = Types.DATE;
			break;
		case Time:
			datadealer = new DbTimeTypeAdapter();
			jdbcType = Types.TIME;
			break;
		case Datetime:
			datadealer = new DbTimestampTypeAdapter();
			jdbcType = Types.TIMESTAMP;
			break;
		case Timestamp:
			datadealer = new DbTimestampTypeAdapter();
			jdbcType = Types.TIMESTAMP;
			break;
		default:
			throw new UnsupportedOperationException("rawType out range");
		}
	}

//	String fieldName, String columnName, boolean key, boolean nullable, RawTypes rawType,long size,
//	int precision, int scale
	
	
	public final BasicTypeAdapter<?> datadealer;
	public final String fieldName;
	public final String columnName;
	public final boolean key;
	public final boolean nullable;
	public final RawTypes rawType;
	public final int jdbcType;
	public final long size;
	public final int precision;
	public final int scale;
	@Override
	public String toString() {
		return "DbColumn [fieldName=" + fieldName + ", columnName=" + columnName + ", rawType=" + rawType
				+ ", jdbcType=" + jdbcType + ", size=" + size + ", precision=" + precision + ", scale=" + scale
				+ ", key=" + key + "]";
	}
	
	
}

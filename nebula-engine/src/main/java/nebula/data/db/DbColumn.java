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
			datadealer = new BooleanDataDealer();
			jdbcType = Types.BOOLEAN;
			break;
		case Long:
			datadealer = new Long_BigInt_DataDealer();
			jdbcType = Types.BIGINT;
			break;
		case Decimal:
			datadealer = new DecimalDealer();
			jdbcType = Types.DECIMAL;
			break;
		case String:
			datadealer = new String_Varchar_DataDealer();
			jdbcType = Types.VARCHAR;
			break;
		case Text:
			datadealer = new TextBlock_Varchar_DataDealer();
			jdbcType = Types.VARCHAR;			
			break;
		case Date:
			datadealer = new DateDataDealer();
			jdbcType = Types.DATE;
			break;
		case Time:
			datadealer = new TimeDataDealer();
			jdbcType = Types.TIME;
			break;
		case Datetime:
			datadealer = new TimestampDataDealer();
			jdbcType = Types.TIMESTAMP;
			break;
		case Timestamp:
			datadealer = new TimestampDataDealer();
			jdbcType = Types.TIMESTAMP;
			break;
		default:
			throw new UnsupportedOperationException("rawType out range");
		}
	}

//	String fieldName, String columnName, boolean key, boolean nullable, RawTypes rawType,long size,
//	int precision, int scale
	
	
	public final DbDataDealer<?> datadealer;
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

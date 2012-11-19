package nebula.data.db;

import nebula.lang.RawTypes;

public class DbColumn {
	public DbColumn(String fieldName, String columnName, boolean key, RawTypes rawType,long size,
			int precision, int scale) {

		this.fieldName = fieldName;
		this.columnName = columnName;
		this.key = key;

		this.rawType = rawType;
		this.size = size;
		this.precision = precision;
		this.scale = scale;

		switch (rawType) {
		case Boolean:
			datadealer = new BooleanDataDealer();
			break;
		case Long:
			datadealer = new Long_BigInt_DataDealer();
			break;
		case Decimal:
			datadealer = new DecimalDealer();
			break;
		case String:
			datadealer = new String_Varchar_DataDealer();
			break;
		case Text:
			datadealer = new TextBlock_Varchar_DataDealer();
			break;
		case Date:
			datadealer = new DateDataDealer();
			break;
		case Time:
			datadealer = new TimeDataDealer();
			break;
		case Datetime:
			datadealer = new TimestampDataDealer();
			break;
		case Timestamp:
			datadealer = new TimestampDataDealer();
			break;
		default:
			throw new UnsupportedOperationException("rawType out range");
		}
	}

	public final DefaultDBFieldDealer<?> datadealer;
	public final String fieldName;
	public final String columnName;
	public final RawTypes rawType;
	public final long size;
	public final int precision;
	public final int scale;
	public final boolean key;
}

package nebula.db;

import java.sql.Types;

import nebula.db.types.BigIntDataDealer;
import nebula.db.types.DateDataDealer;
import nebula.db.types.DecimalDealer;
import nebula.db.types.TimeDataDealer;
import nebula.db.types.TimestampDataDealer;
import nebula.db.types.VarcharDataDealer;

public class DbColumn {
	public DbColumn(String fieldName, String columnName, boolean key, int jdbcType, long size, int precision, int scale) {

		this.fieldName = fieldName;
		this.columnName = columnName;
		this.key = key;

		this.jdbcType = jdbcType;
		this.size = size;
		this.precision = precision;
		this.scale = scale;

		switch (jdbcType) {
		case Types.BIGINT:
			datadealer = new BigIntDataDealer();
			break;
		case Types.DECIMAL:
			datadealer = new DecimalDealer();
			break;
		case Types.VARCHAR:
			datadealer = new VarcharDataDealer();
			break;
		case Types.DATE:
			datadealer = new DateDataDealer();
			break;
		case Types.TIME:
			datadealer = new TimeDataDealer();
			break;
		case Types.TIMESTAMP:
			datadealer = new TimestampDataDealer();
			break;
		default:
			datadealer = new VarcharDataDealer();
			break;
		}
	}

	public final DBDataDealer datadealer;
	public final String fieldName;
	public final String columnName;
	public final int jdbcType;
	public final long size;
	public final int precision;
	public final int scale;
	public final boolean key;
}

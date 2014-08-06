package nebula.data.db;

import nebula.lang.RawTypes;

public class DbColumn {
	public DbColumn(String columnName, boolean key, boolean nullable, boolean array, RawTypes bizType, int size, int precision, int scale, int jdbcType,
			String define) {
		this.columnName = columnName;
		this.key = key;
		this.nullable = nullable;
		this.array = array;

		this.bizType = bizType;

		this.precision = precision;
		this.scale = scale;
		this.size = size;
		this.jdbcType = jdbcType;
		this.define = define;
	}

	public final String columnName;
	public final boolean array;
	public final RawTypes bizType;

	public final boolean key;
	public final boolean nullable;
	public final int size;
	public final int precision;
	public final int scale;

	public final int jdbcType;
	public final String define;
}

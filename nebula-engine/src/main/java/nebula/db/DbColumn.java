package nebula.db;

public class DbColumn {
	public DbColumn(Object parent, String fieldName, String columnName, String fieldTypeName, String columnTypeName, boolean key) {
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.fieldTypeName = fieldTypeName;
		this.columnTypeName = columnTypeName;
		this.key = key;
	}

	public DbColumn(String fieldName, String columnName, String fieldTypeName, String columnTypeName, boolean key) {
		this.fieldName = fieldName;
		this.fieldTypeName = fieldTypeName;
		this.columnName = columnName;
		this.columnTypeName = columnTypeName;
		this.key = key;
	}

	public final String fieldName;
	public final String columnName;
	public final String fieldTypeName;
	public final String columnTypeName;
	public final boolean key;

}

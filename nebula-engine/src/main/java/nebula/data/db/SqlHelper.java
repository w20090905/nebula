package nebula.data.db;

import java.util.ArrayList;
import java.util.List;

import nebula.lang.Field;
import nebula.lang.Importance;
import nebula.lang.RawTypes;
import nebula.lang.Type;
import util.InheritHashMap;

public class SqlHelper {

	Type clz;
	final DbColumn[] userColumns;
	final DbColumn[] systemColumns;
	final DbColumn[] keyColumns;
	final String tableName;
	final String fieldlist_comma;
	final String fieldlist_questions;
	final String wherekeys;
	final DbConfiguration config;

	private void addColumn(ArrayList<DbColumn> list, String name, Field field, boolean key) {
		InheritHashMap attrs = field.getAttrs();
		Object v;

		RawTypes rawType = field.getType().getRawType();

		v = attrs.get("maxLength");
		long size = v != null ? (Integer) v : 0;

		v = attrs.get("precision");
		int precision = v != null ? (Integer) v : 0;

		v = attrs.get("scale");
		int scale = v != null ? (Integer) v : 0;

		boolean nullable = field.getImportance() == Importance.Unimportant;

		DbColumn c = new DbColumn(name, decodeFieldName(name), key, nullable, rawType, size, precision, scale);
		list.add(c);
	}

	private void addColumn(ArrayList<DbColumn> list, String resideName, String name, Field field, boolean key) {
		InheritHashMap attrs = field.getAttrs();
		Object v;

		RawTypes rawType = field.getType().getRawType();

		v = attrs.get("maxLength");
		long size = v != null ? (Integer) v : 0;

		v = attrs.get("precision");
		int precision = v != null ? (Integer) v : 0;

		v = attrs.get("scale");
		int scale = v != null ? (Integer) v : 0;

		boolean nullable = field.getImportance() == Importance.Unimportant;

		DbColumn c = new DbColumn(resideName + name, decodeFieldName(resideName + "_" + name), key, nullable, rawType,
				size, precision, scale);
		list.add(c);
	}

	private String decodeFieldName(String fieldName) {
		return fieldName.toUpperCase();
	}

	private String decodeTypeName(String typeName) {
		return 'N' + typeName.replace('.', '_');
	}

	public String getTableName() {
		return this.tableName;
	}

	public SqlHelper(final DbConfiguration config, Type type) {

		try {
			this.config = config;

			this.clz = type;

			tableName = decodeTypeName(type.getName());

			List<Field> fl = type.getFields();
			ArrayList<DbColumn> listUserColumns = new ArrayList<DbColumn>();
			for (Field f : fl) {
				if (f.isArray()) {
					// TODO Add Array Support
					continue;
				}
				Type rT;
				switch (f.getRefer()) {
				case ByVal:
					addColumn(listUserColumns,f.getName(), f, f.isKey());
					break;
				case Inline:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						addColumn(listUserColumns,f.getName(), rf.getName(), rf, f.isKey() && rf.isKey());
					}
					break;
				case ByRef:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						switch (rf.getImportance()) {
						case Key:
						case Core:
							addColumn(listUserColumns,rT.getName(), rf.getName(), rf, f.isKey() && rf.isKey());
							break;
						}
					}
					break;
				case Cascade:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						switch (rf.getImportance()) {
						case Key:
						case Core:
							addColumn(listUserColumns,rT.getName(), rf.getName(), rf, f.isKey() || rf.isKey());
							break;
						}
					}
					break;
				}
			}

			StringBuilder sbForSelect = new StringBuilder();
			StringBuilder sbForWhere = new StringBuilder();
			ArrayList<DbColumn> listKeyColumns = new ArrayList<DbColumn>();
			String sql = "";

			for (DbColumn column : listUserColumns) {
				sbForSelect.append(column.columnName);
				sbForSelect.append(',');
				sbForWhere.append("?,");

				if (column.key) {
					listKeyColumns.add(column);
					sql += column.columnName + " = ? and ";
				}
			}

			ArrayList<DbColumn> listSystemColumns = new ArrayList<DbColumn>();
			DbColumn col = new DbColumn("LastModified_", "TIMESTAMP_", false, false, RawTypes.Timestamp, 0, 0, 0);
			listSystemColumns.add(col);

			this.keyColumns = listKeyColumns.toArray(new DbColumn[0]);
			this.wherekeys = sql.substring(0, sql.length() - 4);

			this.userColumns = listUserColumns.toArray(new DbColumn[0]);
			this.systemColumns = listSystemColumns.toArray(new DbColumn[0]);
			this.fieldlist_comma = sbForSelect.substring(0, sbForSelect.length() - 1);
			this.fieldlist_questions = sbForWhere.substring(0, sbForWhere.length() - 1);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String builderCount() {
		return "SELECT count(1) FROM " + this.tableName + " ";
	}

	public String builderList() {
		return "SELECT " + this.fieldlist_comma + ",TIMESTAMP_ FROM " + this.tableName + " ";
	}

	public String builderDrop() {
		return "DROP TABLE " + this.tableName + " ";
	}

	public String builderGetMeta() {
		return "SELECT * FROM " + this.tableName + " WHERE 0=1";
	}

	public String builderCreate() {
		StringBuilder sb = new StringBuilder();

		sb.append("CREATE TABLE ").append(this.tableName).append("(");

		for (DbColumn column : this.userColumns) {
			if (column.key) {
				sb.append(column.columnName).append(' ').append(config.toColumnDefine(column)).append(" NOT NULL")
						.append(",");
			} else {
				sb.append(column.columnName).append(' ').append(config.toColumnDefine(column)).append(",");
			}
		}

		sb.append("PRIMARY KEY ( ");
		for (DbColumn column : this.userColumns) {
			if (column.key) {
				sb.append(column.columnName).append(",");
			}
		}
		sb.setCharAt(sb.length() - 1, ')');
		sb.append(',');

		sb.append("TIMESTAMP_").append(" TIMESTAMP");
		sb.append(")");

		return sb.toString();

	}

	public String builderInsert() {
		return "INSERT INTO  " + this.tableName + "(" + fieldlist_comma + ",TIMESTAMP_) values(" + fieldlist_questions
				+ ",CURRENT_TIMESTAMP)";

	}

	public String builderUpdate() {
		String sb = "UPDATE " + this.tableName + " SET ";

		for (DbColumn column : this.userColumns) {
			sb += column.columnName + " = ? ,";
		}
		sb += " TIMESTAMP_= CURRENT_TIMESTAMP ";
		sb += "WHERE " + wherekeys + "";
		return sb.toString();
	}

	public String builderDelete() {
		return "DELETE FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public String builderAddColumn(DbColumn column) {
		if (column.key) {
			return "ALTER TABLE " + this.tableName + " ADD COLUMN " + column.columnName + " "
					+ config.toColumnDefine(column) + " NOT NULL";
		} else {
			return "ALTER TABLE " + this.tableName + " ADD COLUMN " + column.columnName + " "
					+ config.toColumnDefine(column);

		}
	}

	public String builderDropPrimaryKey() {
		return "ALTER TABLE " + this.tableName + " DROP PRIMARY KEY";
	}

	public String builderAddPrimaryKey(String keys) {
		return "ALTER TABLE " + this.tableName + " ADD PRIMARY KEY ( " + keys + ") ";
	}

	public String builderModifyColumnDateType(DbColumn column) {
		return "ALTER TABLE " + this.tableName + " ALTER COLUMN " + column.columnName + " SET DATA TYPE "
				+ config.toColumnDefine(column);
	}

	public String builderRemoveColumn(String columnName) {
		return "ALTER TABLE " + this.tableName + " DROP COLUMN " + columnName + " ";
	}

	public String builderDeleteAll() {
		return "DELETE FROM " + this.tableName + " ";
	}

	public String builderGet() {
		return "SELECT " + fieldlist_comma + ",TIMESTAMP_  FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public DbColumn[] getUserColumns() {
		return this.userColumns;
	}

	public DbColumn[] getSystemColumns() {
		return this.systemColumns;
	}

	public DbColumn[] getKeyColumns() {
		return this.keyColumns;
	}

}

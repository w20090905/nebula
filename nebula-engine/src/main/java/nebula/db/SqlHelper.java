package nebula.db;

import java.util.ArrayList;
import java.util.List;

import nebula.lang.Field;
import nebula.lang.Type;

public class SqlHelper {

	Type clz;
	final DbColumn[] columns;
	final DbColumn[] keyColumns;
	final String tableName;
	final String fieldlist_comma;
	final String fieldlist_questions;
	final String wherekeys;
	ArrayList<DbColumn> fs;

	private void addColumn(String name, Type type, boolean key) {
		DbColumn c = new DbColumn(name, decodeFieldName(name), type.getName(), type.getName(), key);
		fs.add(c);
	}

	private void addColumn(String resideName, String name, Type type, boolean key) {
		DbColumn c = new DbColumn(resideName + name, 
				decodeFieldName(resideName + "_" + name), 
				type.getName(),	type.getName(), key);
		fs.add(c);
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

	public SqlHelper(Type type) {

		try {
			this.clz = type;

			tableName = decodeTypeName(type.getName());

			List<Field> fl = type.getFields();

			fs = new ArrayList<DbColumn>();
			for (Field f : fl) {
				if (f.isArray()) {
					// TODO Add Array Support
					continue;
				}
				Type rT;
				switch (f.getRefer()) {
				case ByVal:
					addColumn(f.getName(), f.getType(), f.isKey());
					break;
				case Inline:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						addColumn(f.getName(), rf.getName(), rf.getType(), f.isKey() && rf.isKey());
					}
					break;
				case ByRef:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						switch (rf.getImportance()) {
						case Key:
						case Core:
							addColumn(rT.getName(), rf.getName(), rf.getType(), f.isKey() && rf.isKey());
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
							addColumn(rT.getName(), rf.getName(), rf.getType(), f.isKey() || rf.isKey());
							break;
						}
					}
					break;
				}
			}

			StringBuilder sbForSelect = new StringBuilder();
			StringBuilder sbForWhere = new StringBuilder();
			ArrayList<DbColumn> buildKeyColumns = new ArrayList<DbColumn>();
			String sql = "";

			for (DbColumn column : fs) {
				sbForSelect.append(column.columnName);
				sbForSelect.append(',');
				sbForWhere.append("?,");

				if (column.key) {
					buildKeyColumns.add(column);
					sql += column.columnName + " = ? and ";
				}
			}

			this.keyColumns = buildKeyColumns.toArray(new DbColumn[0]);
			this.wherekeys = sql.substring(0, sql.length() - 4);

			this.columns = fs.toArray(new DbColumn[0]);
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
		
		int cnt = 0;
		
		for (DbColumn column : this.columns) {
			if(column.key)cnt++;
		}
		
		
		for (DbColumn column : this.columns) {
			if (column.key && cnt==1) {
				sb.append(column.columnName).append(" varchar(40) PRIMARY KEY").append(",");
			} else {
				sb.append(column.columnName).append(" varchar(40)").append(",");
			}
		}
		if(cnt>1){
			sb.append("PRIMARY KEY ( ");
			for (DbColumn column : this.columns) {
				if(column.key){
					sb.append(column.columnName).append(",");					
				}
			}
			sb.setCharAt(sb.length()-1,')');
			sb.append(',');
		}
		
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

		for (DbColumn column : this.columns) {
			sb += column.columnName + " = ? ,";
		}
		sb += " TIMESTAMP_= CURRENT_TIMESTAMP ";
		sb += "WHERE " + wherekeys + "";
		return sb.toString();
	}

	public String builderDelete() {
		return "DELETE FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public String builderAddColumn(DbColumn c) {
		return "ALTER TABLE " + this.tableName + " ADD COLUMN " + c.columnName + " VARCHAR(40)";
	}

	public String builderDeleteAll() {
		return "DELETE FROM " + this.tableName + " ";
	}

	public String builderGet() {
		return "SELECT " + fieldlist_comma + ",TIMESTAMP_  FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public DbColumn[] builderColumns() {
		return this.columns;
	}

	public DbColumn[] getKeyColumns() {
		return this.keyColumns;
	}

}

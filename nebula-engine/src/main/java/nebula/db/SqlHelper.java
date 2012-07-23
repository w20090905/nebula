package nebula.db;

import java.util.ArrayList;
import java.util.List;

import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;

public class SqlHelper {

	protected Type clz;
	protected final DbColumn[] columns;
	protected final DbColumn[] keyColumns;
	protected final String tableName;
	protected final String fieldlist_comma;
	protected final String fieldlist_questions;
	protected final String wherekeys;

	protected String decodeFieldName(String typeName, String fieldName) {
		return fieldName;
	}

	protected String decodeTypeName(String typeName) {
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

			ArrayList<DbColumn> fs = new ArrayList<DbColumn>();
			for (Field f : fl) {
				if (f.isArray()) {
					// TODO
				} else if (f.getRefer() == Reference.ByVal) {
					fs.add(new DbColumn(this, decodeFieldName(type.getName(), f.getName()),
							f.isKey()));
//				} else if (f.getRefer() == Field.INLINE) {
//					 fs.add(f.getName() + "_" + f.getType().getKeyField().getName());
//					Type refType = f.getType();
//					for (Field refField : refType.getFields()) {
//						String cName = decodeFieldName(type.getName(), f.getName()) + "_" + decodeFieldName(refType.getName(), refField.getName());
//						fs.add(new DbColumn(this, cName,f.getImportance() == Field.KEY));
//					}
//				} else if (f.getRefer() == Field.REFERENCE) {
//					for (Field referField : f.getType().getFields()) {
//						if (referField.getImportance() == Field.KEY || referField.getImportance() == Field.CORE) {
//							String cName = decodeFieldName(type.getName(), f.getName()) + "_" + decodeFieldName(f.getType().getName(), referField.getName());
//							fs.add(new DbColumn(this, cName,f.getImportance() == Field.KEY));
//						}
//					}
				}
			}

			StringBuilder sb = new StringBuilder();
			StringBuilder sbq = new StringBuilder();
			ArrayList<DbColumn> kfs = new ArrayList<DbColumn>();
			String sql = "";

			for (DbColumn column : fs) {
				sb.append(column.name);
				sb.append(',');
				sbq.append("?,");

				if (column.key) {
					kfs.add(column);
					sql += column.name + " = ? and ";
				}
			}

			this.keyColumns = kfs.toArray(new DbColumn[0]);
			this.wherekeys = sql.substring(0, sql.length() - 4);

			this.columns = fs.toArray(new DbColumn[0]);
			this.fieldlist_comma = sb.substring(0, sb.length() - 1);
			this.fieldlist_questions = sbq.substring(0, sbq.length() - 1);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String builderCount() {
		return "SELECT count(*) FROM " + this.tableName + " ";
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

		for (DbColumn column : this.columns) {
			if (column.key) {
				sb.append(column.name).append(" varchar(40) PRIMARY KEY").append(",");
			} else {
				sb.append(column.name).append(" varchar(40)").append(",");
			}
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
			sb += column.name + " = ? ,";
		}
		sb += " TIMESTAMP_= CURRENT_TIMESTAMP ";
		sb += "WHERE " + wherekeys + "";
		return sb.toString();
	}

	public String builderDelete() {
		return "DELETE FROM " + this.tableName + " WHERE " + wherekeys + "";
	}
	
	public String builderAddColumn(DbColumn c) {
		return "ALTER TABLE " + this.tableName + " ADD COLUMN " + c.name
				+ " VARCHAR(40)";
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

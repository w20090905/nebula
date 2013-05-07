package nebula.data.db;

import java.util.ArrayList;
import java.util.List;

import nebula.lang.Field;
import static nebula.lang.Importance.*;
import nebula.lang.RawTypes;
import nebula.lang.Reference;
import nebula.lang.Type;
import util.InheritHashMap;
import util.NamesEncoding;

public class DbTransactionDataSqlHelper {

	Type clz;
	final DatabaseColumn[] userColumns;
	final DatabaseColumn[] systemColumns;
	final DatabaseColumn[] keyColumns;
	final String tableName;
	final String fieldlist_comma;
	final String fieldlist_questions;
	final String wherekeys;
	final DbConfiguration config;

	final EntityFieldSerializer entitySerializer;

	private void addColumn(ArrayList<DatabaseColumn> list, String fieldName, String columnName, boolean array,
			Field field, boolean key) {
		InheritHashMap attrs = field.getAttrs();
		Object v;

		RawTypes rawType = field.getType().getRawType();

		v = attrs.get("maxLength");
		long size = v != null ? (Integer) v : 0;

		v = attrs.get("precision");
		int precision = v != null ? (Integer) v : 0;

		v = attrs.get("scale");
		int scale = v != null ? (Integer) v : 0;

		boolean nullable = field.getImportance() == Unimportant;

		DatabaseColumn c = new DatabaseColumn(fieldName, columnName, key, nullable, array, rawType, size, precision,
				scale);
		list.add(c);
	}

	private String toF(String resideName) {
		return resideName;
	}

	private String toF(String resideName, String fieldName) {
		return resideName + fieldName;
	}

	private String toC(String fieldName) {
		return NamesEncoding.encode(fieldName);
	}

	private String toC(String resideName, String fieldName) {
		return NamesEncoding.encode(resideName + "_" + fieldName);
	}

	private String decodeTypeName(String typeName) {
		return 'N' + typeName.replace('.', '_');
	}

	public String getTableName() {
		return this.tableName;
	}

	public DbTransactionDataSqlHelper(final DbConfiguration config, Type type) {

		try {
			this.config = config;

			this.clz = type;

			tableName = decodeTypeName(type.getName());

			List<DefaultFieldSerializer<?>> fieldSerializer = new ArrayList<DefaultFieldSerializer<?>>();

			List<DefaultFieldSerializer<?>> subFieldSerializer;

			List<Field> fl = type.getFields();
			ArrayList<DatabaseColumn> listUserColumns = new ArrayList<DatabaseColumn>();
			for (Field of : fl) {
				if (!of.isArray()) {
					switch (of.getRefer()) {
					case ByVal: // Basic Type Field // Type A1
						addColumn(listUserColumns, toF(of.getName()), toC(of.getName()), false, of, of.isKey());
						fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName()), toC(of.getName()), false,
								of.getType().getRawType()));
						break;
					case Inline: // inline object
						subFieldSerializer = new ArrayList<DefaultFieldSerializer<?>>();
						for (Field in1f : of.getType().getFields()) {
							if (!in1f.isArray()) {//
								switch (in1f.getRefer()) {
								case ByVal: // Type B1
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()),
											toC(of.getName(), in1f.getName()), false, in1f, of.isKey() && in1f.isKey());
									subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName(), toC(
											of.getName(), in1f.getName()), false, in1f.getType().getRawType()));
									break;
								case Inline: // Type B2
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type
																										// C1
											addColumn(listUserColumns,
													toF(of.getName(), in1f.getName() + in2f.getName()),
													toC(of.getName(), in1f.getName() + in2f.getName()), false, in2f,
													of.isKey() && in1f.isKey() && in2f.isKey());
											subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName()
													+ in2f.getName(),
													toC(of.getName(), in1f.getName() + in2f.getName()), false, in2f
															.getType().getRawType()));
										}
									}
									break;
								case ByRef: // Type B3
								case Cascade: // Type B4
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal
												&& (in2f.isKey() || in2f.isCore())) {
											addColumn(listUserColumns,
													toF(of.getName(), in1f.getName() + in2f.getName()),
													toC(of.getName(), in1f.getName() + in2f.getName()), false, in2f,
													of.isKey() && in1f.isKey() && in2f.isKey());
											subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName()
													+ in2f.getName(),
													toC(of.getName(), in1f.getName() + in2f.getName()), false, in2f
															.getType().getRawType()));
										}
									}
									break;
								}
							} else {
								switch (in1f.getRefer()) {
								case ByVal: // Type B5
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()),
											toC(of.getName(), in1f.getName()), true, in1f, of.isKey() && in1f.isKey());
									subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName(), toC(
											of.getName(), in1f.getName()), true, in1f.getType().getRawType()));
									break;
								case Inline: // Type B6
									ArrayList<ListTypeAdapter<?>> adapteres = new ArrayList<ListTypeAdapter<?>>();
									ArrayList<String> subFieldNames = new ArrayList<String>();

									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type
																										// D1
											addColumn(listUserColumns,
													toF(of.getName() + in1f.getName(), in2f.getName()),
													toC(of.getName() + in1f.getName(), in2f.getName()), true, in2f,
													false);
											adapteres.add(ListTypeAdapter.getAdapter(in2f.getType().getRawType()));
											subFieldNames.add(in2f.getName());
										}
									}

									subFieldSerializer.add(new EntityListFieldSerializer(in1f.getName(), adapteres, subFieldNames));
									break;
								case ByRef: // Type B7
								case Cascade: // Type B8
									throw new UnsupportedOperationException(
											"Refer Object cannot has array,must user inline array");
								}
							}
						}
						fieldSerializer.add(new EntityFieldSerializer(toF(of.getName()), toC(of.getName()),
								subFieldSerializer));
						break;
					case ByRef: // Type A3
					case Cascade: // Type A4
						for (Field in1f : of.getType().getFields()) {
							if (!in1f.isArray() && in1f.getRefer() == Reference.ByVal
									&& (in1f.isKey() || in1f.isCore())) {
								addColumn(listUserColumns, toF(of.getName(), in1f.getName()),
										toC(of.getName(), in1f.getName()), false, in1f, of.isKey() && in1f.isKey()
												&& in1f.isKey());
								fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName(), in1f.getName()),
										toC(of.getName(), in1f.getName()), false, in1f.getType().getRawType()));
							}
						}
						break;
					}
				} else {// 数组不可以是Key
					switch (of.getRefer()) {
					case ByVal: // Basic Type Field // Type A5
						addColumn(listUserColumns, toF(of.getName()), toC(of.getName()), true, of, of.isKey());
						fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName()), toC(of.getName()), true, of
								.getType().getRawType()));
						break;
					case Inline: // inline object // Type A6
						List<ListTypeAdapter<?>> adapteres = new ArrayList<ListTypeAdapter<?>>();
						List<String> subFieldNames = new ArrayList<String>();

						for (Field in1f : of.getType().getFields()) {
							if (!in1f.isArray()) {
								switch (in1f.getRefer()) {
								case ByVal: // Type E1
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()),
											toC(of.getName(), in1f.getName()), true, in1f, false);
									adapteres.add(ListTypeAdapter.getAdapter(in1f.getType().getRawType()));
									subFieldNames.add(in1f.getName());
									break;
								case Inline:// Type E2
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) {
											addColumn(listUserColumns,
													toF(of.getName(), in1f.getName() + in2f.getName()),
													toC(of.getName(), in1f.getName() + in2f.getName()), true, in2f,
													false);
											adapteres.add(ListTypeAdapter.getAdapter(in2f.getType().getRawType()));
											subFieldNames.add(in1f.getName() + in2f.getName());
										}
									}
									break;
								case ByRef:// Type E3
								case Cascade:// Type E4
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal
												&& (in2f.isKey() || in2f.isCore())) {
											addColumn(listUserColumns,
													toF(of.getName(), in1f.getName() + in2f.getName()),
													toC(of.getName(), in1f.getName() + in2f.getName()), true, in2f,
													false);
											adapteres.add(ListTypeAdapter.getAdapter(in2f.getType().getRawType()));
											subFieldNames.add(in1f.getName() + in2f.getName());
										}
									}
									break;
								}
							}
						}

						fieldSerializer.add(new EntityListFieldSerializer(toF(of.getName()), adapteres, subFieldNames));

						break;
					case ByRef:
					case Cascade:
						throw new UnsupportedOperationException("Refer Object cannot has array,must user inline array");
					}

				}

			}

			StringBuilder sbForSelect = new StringBuilder();
			StringBuilder sbForWhere = new StringBuilder();
			ArrayList<DatabaseColumn> listKeyColumns = new ArrayList<DatabaseColumn>();
			String sql = "";

			for (DatabaseColumn column : listUserColumns) {
				sbForSelect.append(column.columnName);
				sbForSelect.append(',');
				sbForWhere.append("?,");

				if (column.key) {
					listKeyColumns.add(column);
					sql += column.columnName + " = ? and ";
				}
			}

			ArrayList<DatabaseColumn> listSystemColumns = new ArrayList<DatabaseColumn>();
			DatabaseColumn col = new DatabaseColumn("LastModified_", "TIMESTAMP_", false, false, false,
					RawTypes.Timestamp, 0, 0, 0);
			listSystemColumns.add(col);

			fieldSerializer.add(new SystemTypeFieldSerializer("LastModified_", "LastModified_", false,
					RawTypes.Timestamp));

			this.entitySerializer = new EntityFieldSerializer(fieldSerializer);

			this.keyColumns = listKeyColumns.toArray(new DatabaseColumn[0]);
			this.wherekeys = sql.substring(0, sql.length() - 4);

			this.userColumns = listUserColumns.toArray(new DatabaseColumn[0]);
			this.systemColumns = listSystemColumns.toArray(new DatabaseColumn[0]);
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

		for (DatabaseColumn column : this.userColumns) {
			if (column.key) {
				sb.append(column.columnName).append(' ').append(config.toColumnDefine(column)).append(" NOT NULL")
						.append(",");
			} else {
				sb.append(column.columnName).append(' ').append(config.toColumnDefine(column)).append(",");
			}
		}

		sb.append("PRIMARY KEY ( ");
		for (DatabaseColumn column : this.userColumns) {
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

		for (DatabaseColumn column : this.userColumns) {
			sb += column.columnName + " = ? ,";
		}
		sb += " TIMESTAMP_= CURRENT_TIMESTAMP ";
		sb += "WHERE " + wherekeys + "";
		return sb.toString();
	}

	public String builderDelete() {
		return "DELETE FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public String builderAddColumn(DatabaseColumn column) {
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

	public String builderModifyColumnDateType(DatabaseColumn column) {
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

	public DatabaseColumn[] getUserColumns() {
		return this.userColumns;
	}

	public DatabaseColumn[] getSystemColumns() {
		return this.systemColumns;
	}

	public DatabaseColumn[] getKeyColumns() {
		return this.keyColumns;
	}

	public EntityFieldSerializer getEntitySerializer() {
		return entitySerializer;
	}

}

package nebula.data.db;

import java.util.ArrayList;
import java.util.List;

import nebula.data.db.serializer.BasicTypeFieldSerializer;
import nebula.data.db.serializer.DefaultFieldSerializer;
import nebula.data.db.serializer.EntityFieldSerializer;
import nebula.data.db.serializer.EntityListFieldSerializer;
import nebula.data.db.serializer.ListTypeAdapter;
import nebula.data.db.serializer.SystemTypeFieldSerializer;
import nebula.lang.Field;
import nebula.lang.RawTypes;
import nebula.lang.Reference;
import nebula.lang.Type;
import util.InheritHashMap;
import util.NamesEncoding;

public class DbSqlHelper {

	public static final String Column_ColumnDefinition = "ColumnDefinition";
	public static final String Column_Insertable = "Insertable";
	public static final String Column_Length = "MaxLength";
	public static final String Column_Name = "Column";
	public static final String Column_Nullable = "Nullable";
	public static final String Column_Precision = "Precision";
	public static final String Column_Scale = "Scale";
	public static final String Column_Table = "ColumnTable";
	public static final String Column_Unique = "Unique";
	public static final String Column_Updatable = "Updatable";
	public static final String Table_Name = "Table";

	Type clz;
	final DbConfiguration config;
	final EntityFieldSerializer entitySerializer;
	final String fieldlist_comma;
	final String fieldlist_questions;
	final DbColumn[] keyColumns;
	final DbColumn[] systemColumns;
	final String tableName;
	final DbColumn[] userColumns;

	final String wherekeys;

	public DbSqlHelper(final DbConfiguration config, Type type) {

		try {
			this.config = config;

			this.clz = type;

			if(type.getAttrs().containsKey(Table_Name)){
				String schema =(String) type.getAttrs().get(Type.LEGACY);
				tableName =schema + "." + (String)type.getAttrs().get(Table_Name);
			}else{
				tableName = decodeTypeName(type.getName());
			}

			List<DefaultFieldSerializer<?>> fieldSerializer = new ArrayList<DefaultFieldSerializer<?>>();

			List<DefaultFieldSerializer<?>> subFieldSerializer;

			List<Field> fl = type.getFields();
			ArrayList<DbColumn> listUserColumns = new ArrayList<DbColumn>();
			for (Field of : fl) {
				if (of.isTransparent()) continue;

				if (!of.isArray()) {
					switch (of.getRefer()) {
					case ByVal: // Basic Type Field // Type A1
						addColumn(listUserColumns, toF(of.getName()), toC(of), false, of, of.isKey());
						fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName()), toC(of), false, of.getType().getRawType()));
						break;
					case Inline: // inline object
						subFieldSerializer = new ArrayList<DefaultFieldSerializer<?>>();
						for (Field in1f : of.getType().getFields()) {
							if (in1f.isTransparent()) continue;// Skip when is
															// Transient

							if (!in1f.isArray()) {//
								switch (in1f.getRefer()) {
								case ByVal: // Type B1
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()), toC(of, in1f), false, in1f, of.isKey() && in1f.isKey());
									subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName(), toC(of, in1f), false, in1f.getType().getRawType()));
									break;
								case Inline: // Type B2
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type
																										// C1
											addColumn(listUserColumns, toF(of.getName(), in1f.getName() + in2f.getName()), toC(cOf(of), cOf(in1f) + cOf(in2f)),
													false, in2f, of.isKey() && in1f.isKey() && in2f.isKey());
											subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName() + in2f.getName(), toC(cOf(of), cOf(in1f)
													+ cOf(in2f)), false, in2f.getType().getRawType()));
										}
									}
									break;
								case ByRef: // Type B3
								case Cascade: // Type B4
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal && (in2f.isKey() || in2f.isCore())) {
											addColumn(listUserColumns, toF(of.getName(), in1f.getName() + in2f.getName()),
													toC(of.getName(), in1f.getName() + in2f.getName()), false, in2f, of.isKey() && in1f.isKey() && in2f.isKey());
											subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName() + in2f.getName(), toC(cOf(of), cOf(in1f)
													+ cOf(in2f)), false, in2f.getType().getRawType()));
										}
									}
									break;
								}
							} else {
								switch (in1f.getRefer()) {
								case ByVal: // Type B5
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()), toC(of, in1f), true, in1f, of.isKey() && in1f.isKey());
									subFieldSerializer.add(new BasicTypeFieldSerializer(in1f.getName(), toC(of, in1f), true, in1f.getType().getRawType()));
									break;
								case Inline: // Type B6
									ArrayList<ListTypeAdapter<?>> adapteres = new ArrayList<ListTypeAdapter<?>>();
									ArrayList<String> subFieldNames = new ArrayList<String>();

									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type
																										// D1
											addColumn(listUserColumns, toF(of.getName() + in1f.getName(), in2f.getName()), toC(cOf(of) + cOf(in1f), cOf(in2f)),
													true, in2f, false);
											adapteres.add(ListTypeAdapter.getAdapter(in2f.getType().getRawType()));
											subFieldNames.add(in2f.getName());
										}
									}

									subFieldSerializer.add(new EntityListFieldSerializer(in1f.getName(), adapteres, subFieldNames));
									break;
								case ByRef: // Type B7
								case Cascade: // Type B8
									throw new UnsupportedOperationException("Refer Object cannot has array,must user inline array");
								}
							}
						}
						fieldSerializer.add(new EntityFieldSerializer(toF(of.getName()), toC(of), subFieldSerializer));
						break;
					case ByRef: // Type A3
					case Cascade: // Type A4
						for (Field in1f : of.getType().getFields()) {
							if (!in1f.isArray() && in1f.getRefer() == Reference.ByVal && (in1f.isKey() || in1f.isCore())) {
								addColumn(listUserColumns, toF(of.getName(), in1f.getName()), toC(of, in1f), false, in1f,
										of.isKey() && in1f.isKey() && in1f.isKey());
								fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName(), in1f.getName()), toC(of, in1f), false, in1f.getType()
										.getRawType()));
							}
						}
						break;
					}
				} else {// 数组不可以是Key
					switch (of.getRefer()) {
					case ByVal: // Basic Type Field // Type A5
						addColumn(listUserColumns, toF(of.getName()), toC(of), true, of, of.isKey());
						fieldSerializer.add(new BasicTypeFieldSerializer(toF(of.getName()), toC(of), true, of.getType().getRawType()));
						break;
					case Inline: // inline object // Type A6
						List<ListTypeAdapter<?>> adapteres = new ArrayList<ListTypeAdapter<?>>();
						List<String> subFieldNames = new ArrayList<String>();

						for (Field in1f : of.getType().getFields()) {
							if (!in1f.isArray()) {
								switch (in1f.getRefer()) {
								case ByVal: // Type E1
									addColumn(listUserColumns, toF(of.getName(), in1f.getName()), toC(of, in1f), true, in1f, false);
									adapteres.add(ListTypeAdapter.getAdapter(in1f.getType().getRawType()));
									subFieldNames.add(in1f.getName());
									break;
								case Inline:// Type E2
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) {
											addColumn(listUserColumns, toF(of.getName(), in1f.getName() + in2f.getName()), toC(cOf(of), cOf(in1f) + cOf(in2f)),
													true, in2f, false);
											adapteres.add(ListTypeAdapter.getAdapter(in2f.getType().getRawType()));
											subFieldNames.add(in1f.getName() + in2f.getName());
										}
									}
									break;
								case ByRef:// Type E3
								case Cascade:// Type E4
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal && (in2f.isKey() || in2f.isCore())) {
											addColumn(listUserColumns, toF(of.getName(), in1f.getName() + in2f.getName()), toC(cOf(of), cOf(in1f) + cOf(in2f)),
													true, in2f, false);
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
			DbColumn col = new DbColumn("LastModified_", "TIMESTAMP_", false, false, false, RawTypes.Timestamp, 0, 0, 0);
			listSystemColumns.add(col);

			fieldSerializer.add(new SystemTypeFieldSerializer("LastModified_", "LastModified_", false, RawTypes.Timestamp));

			this.entitySerializer = new EntityFieldSerializer(fieldSerializer);

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

	private void addColumn(ArrayList<DbColumn> list, String fieldName, String columnName, boolean array, Field field, boolean key) {
		InheritHashMap attrs = field.getAttrs();
		Object v;

		RawTypes rawType = field.getType().getRawType();

		v = attrs.get(Column_Length);
		long size = v != null ? (Long) v : 0;

		v = attrs.get(Column_Precision);
		int precision = v != null ? ((Long) v).intValue() : 0;

		v = attrs.get(Column_Scale);
		int scale = v != null ? ((Long) v).intValue() : 0;

		boolean nullable = field.isNullable();

		DbColumn c = new DbColumn(fieldName, columnName, key, nullable, array, rawType, size, precision, scale);
		list.add(c);
	}

	public String builderAddColumn(DbColumn column) {
		if (column.key) {
			return "ALTER TABLE " + this.tableName + " ADD COLUMN " + column.columnName + " " + config.toColumnDefine(column) + " NOT NULL";
		} else {
			return "ALTER TABLE " + this.tableName + " ADD COLUMN " + column.columnName + " " + config.toColumnDefine(column);

		}
	}

	public String builderAddPrimaryKey(String keys) {
		return "ALTER TABLE " + this.tableName + " ADD PRIMARY KEY ( " + keys + ") ";
	}

	public String builderCount() {
		return "SELECT count(1) FROM " + this.tableName + " ";
	}

	public String builderCreate() {
		StringBuilder sb = new StringBuilder();

		sb.append("CREATE TABLE ").append(this.tableName).append("(");

		for (DbColumn column : this.userColumns) {
			if (column.key) {
				sb.append(column.columnName).append(' ').append(config.toColumnDefine(column)).append(" NOT NULL").append(",");
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

	public String builderDelete() {
		return "DELETE FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public String builderDeleteAll() {
		return "DELETE FROM " + this.tableName + " ";
	}

	public String builderDrop() {
		return "DROP TABLE " + this.tableName + " ";
	}

	public String builderDropPrimaryKey() {
		return "ALTER TABLE " + this.tableName + " DROP PRIMARY KEY";
	}

	public String builderGet() {
		return "SELECT " + fieldlist_comma + ",TIMESTAMP_  FROM " + this.tableName + " WHERE " + wherekeys + "";
	}

	public String builderGetMeta() {
		return "SELECT * FROM " + this.tableName + " WHERE 0=1";
	}

	public String builderInsert() {
		return "INSERT INTO  " + this.tableName + "(" + fieldlist_comma + ",TIMESTAMP_) values(" + fieldlist_questions + ",CURRENT_TIMESTAMP)";

	}

	public String builderList() {
		return "SELECT " + this.fieldlist_comma + ",TIMESTAMP_ FROM " + this.tableName + " ";
	}

	public String builderMaxId() {
		// ID表肯定只有一个Key，所以可以直接使用keyColumn[0]
		return "SELECT max(" + this.keyColumns[0].columnName + ") FROM " + this.tableName + " ";
	}

	public String builderModifyColumnDateType(DbColumn column) {
		return "ALTER TABLE " + this.tableName + " ALTER COLUMN " + column.columnName + " SET DATA TYPE " + config.toColumnDefine(column);
	}

	public String builderRemoveColumn(String columnName) {
		return "ALTER TABLE " + this.tableName + " DROP COLUMN " + columnName + " ";
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

	private String cOf(Field field) {
		return field.getAttrs().containsKey(Column_Name) ? (String) field.getAttrs().get(Column_Name) : field.getName();
	}

	private String decodeTypeName(String typeName) {
		return 'N' + typeName.replace('.', '_');
	}

	public EntityFieldSerializer getEntitySerializer() {
		return entitySerializer;
	}

	public DbColumn[] getKeyColumns() {
		return this.keyColumns;
	}

	public DbColumn[] getSystemColumns() {
		return this.systemColumns;
	}

	public String getTableName() {
		return this.tableName;
	}

	public DbColumn[] getUserColumns() {
		return this.userColumns;
	}

	private String toC(Field field) {
		return NamesEncoding.encode(cOf(field));
	}

	private String toC(Field field, Field in1f) {
		return NamesEncoding.encode(cOf(field) + "_" + cOf(in1f));
	}

	private String toC(String resideName, String fieldName) {
		return NamesEncoding.encode(resideName + "_" + fieldName);
	}

	private String toF(String resideName) {
		return resideName;
	}

	private String toF(String resideName, String fieldName) {
		return resideName + fieldName;
	}

}

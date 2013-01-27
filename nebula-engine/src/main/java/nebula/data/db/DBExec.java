package nebula.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.IDAdapter;
import nebula.SmartList;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.frame.SmartListImp;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBExec {
	private static final Log log = LogFactory.getLog(DBExec.class);

	final private Connection conn;

	@SuppressWarnings("unused")
	final private Type type;
	// final private String SQL_DROP;
	final private PreparedStatement SQL_GET;
	final private PreparedStatement SQL_INSERT;
	final private PreparedStatement SQL_UPDATE;
	final private PreparedStatement SQL_DELETE;
	final private PreparedStatement SQL_LIST;
	// final private String SQL_COUNT;

	final BasicTypeFieldSerializer[] userColumns;
	final BasicTypeFieldSerializer[] systemColumns;
	// final private String[] realFields;
	// final private DbColumn[] keyColumns;
	final SqlHelper builder;
	final DbConfiguration config;

	// final private Map<String, Integer> map;

	// final private String[] systemFields = new String[] { "TIMESTAMP_" };

	public DBExec(final DbConfiguration config, final Connection conn, final Type type, final SqlHelper helper) {
		this.config = config;
		this.type = type;
		this.conn = conn;

		builder = helper;

		// SQL_DROP = builder.builderDrop();

		userColumns = builder.getUserColumns();
		systemColumns = builder.getSystemColumns();

		this.ensureDBSchema();

		try {
			SQL_GET = conn.prepareStatement(builder.builderGet());
			SQL_INSERT = conn.prepareStatement(builder.builderInsert());
			SQL_UPDATE = conn.prepareStatement(builder.builderUpdate());
			SQL_DELETE = conn.prepareStatement(builder.builderDelete());
			SQL_LIST = conn.prepareStatement(builder.builderList());
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	private void ensureDBSchema() {
		Statement statement = null;
		boolean exist = false;
		ResultSet rs = null;
		try {

			final SmartList<BasicTypeFieldSerializer> mapColumns = new SmartListImp<BasicTypeFieldSerializer>("DbColumn", new IDAdapter<BasicTypeFieldSerializer>() {
				@Override
				public String getID(BasicTypeFieldSerializer data) {
					return data.columnName;
				}
			});

			for (int i = 0; i < userColumns.length; i++) {
				mapColumns.add(userColumns[i]);
			}

			statement = conn.createStatement();
			try {
				rs = statement.executeQuery(builder.builderGetMeta());
				exist = true;
			} catch (SQLSyntaxErrorException e) {
				// don't exist
			}

			if (exist) {

				boolean isKeyChanged = false;

				ResultSetMetaData metaData = rs.getMetaData();
				int columnsSize = metaData.getColumnCount();

				if (log.isDebugEnabled()) log.debug("== Before update column ");

				ArrayList<String> needDeletedColumns = new ArrayList<>();
				Map<String, String> allColumns = new HashMap<>();
				ArrayList<BasicTypeFieldSerializer> typeNotMatchColumns = new ArrayList<BasicTypeFieldSerializer>();
				ArrayList<BasicTypeFieldSerializer> typeSizeNotMatchColumns = new ArrayList<BasicTypeFieldSerializer>();

				String curKeys = "";

				for (int i = 1; i <= columnsSize; i++) {
					String columnName = metaData.getColumnName(i);

					BasicTypeFieldSerializer newColumn = mapColumns.get(columnName);
					if (newColumn == null) {
						if (!columnName.endsWith("_")) {
							needDeletedColumns.add(columnName);
						}
					} else {
						int size = metaData.getColumnDisplaySize(i);
						int nullable = metaData.isNullable(i);
						int precision = metaData.getPrecision(i);
						int scale = metaData.getScale(i);

						int jdbcType = metaData.getColumnType(i);

						if (jdbcType != newColumn.jdbcType) {
							typeNotMatchColumns.add(newColumn);
						} else {
							switch (jdbcType) {
							case Types.DECIMAL:
								if (newColumn.precision == precision && newColumn.scale == scale) {

								} else if (newColumn.precision > precision && newColumn.scale > scale) {
									typeSizeNotMatchColumns.add(newColumn);
								} else if (newColumn.precision > precision || newColumn.scale > scale) {
									int newPrecision = newColumn.precision > precision ? newColumn.precision
											: precision;
									int newScale = newColumn.scale > scale ? newColumn.scale : scale;
									typeSizeNotMatchColumns.add(new BasicTypeFieldSerializer(newColumn.fieldName, newColumn.columnName,
											newColumn.key, newColumn.nullable, false,newColumn.rawType, newColumn.size,
											newPrecision, newScale));
								}
								break;
							case Types.VARCHAR:
								if (newColumn.size > size) typeSizeNotMatchColumns.add(newColumn);
								break;
							}
						}

						if (nullable == ResultSetMetaData.columnNoNulls) {
							curKeys += columnName;
							if (!newColumn.key) {
								isKeyChanged = true;
							}
						}

						allColumns.put(columnName, columnName);
					}

					if (log.isDebugEnabled()) {
						log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
								+ metaData.getColumnDisplaySize(i));
					}
				}
				rs.close();
				conn.commit();

				StringBuffer newKeys = new StringBuffer();
				ArrayList<BasicTypeFieldSerializer> notExistColumns = new ArrayList<BasicTypeFieldSerializer>();
				for (BasicTypeFieldSerializer f : mapColumns) {
					if (!allColumns.containsKey(f.columnName)) {
						notExistColumns.add(f);
					}
					if (f.key) {
						if (curKeys.indexOf(f.columnName) < 0) {
							isKeyChanged = true;
						}
						if (newKeys.length() > 0) {
							newKeys.append(",");
						}
						newKeys.append(f.columnName);
					}
				}

				// key changed
				if (isKeyChanged) {
					statement = conn.createStatement();
					log.trace("##\t" + builder.builderDeleteAll());
					statement.addBatch(builder.builderDrop());
					statement.addBatch(builder.builderCreate());
					statement.executeBatch();

					if (log.isDebugEnabled()) {
						log.debug("== After add not exist column ");
						rs = statement.executeQuery(builder.builderGetMeta());
						metaData = rs.getMetaData();
						columnsSize = metaData.getColumnCount();
						for (int i = 1; i <= columnsSize; i++) {
							log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
									+ metaData.getColumnDisplaySize(i));
						}
						rs.close();
					}
				} else {
					// add not exist column to DB
					if (notExistColumns.size() > 0) {
						statement = conn.createStatement();
						for (BasicTypeFieldSerializer c : notExistColumns) {
							log.trace("##\t" + builder.builderAddColumn(c));
							statement.addBatch(builder.builderAddColumn(c));
						}
						 statement.executeBatch();
						 
						if (log.isDebugEnabled()) {
							log.debug("== After add not exist column ");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
										+ metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// update don't match column to DB
					if (typeNotMatchColumns.size() > 0) {
						statement = conn.createStatement();
						for (BasicTypeFieldSerializer c : typeNotMatchColumns) {
							log.trace("##\t" + builder.builderModifyColumnDateType(c));
							statement.addBatch(builder.builderRemoveColumn(c.columnName));
							statement.addBatch(builder.builderAddColumn(c));
						}
						statement.executeBatch();
						if (log.isDebugEnabled()) {
							log.debug("== After update don't match column to DB");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
										+ metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// update size don't match column to DB
					if (typeSizeNotMatchColumns.size() > 0) {
						statement = conn.createStatement();
						for (BasicTypeFieldSerializer c : typeSizeNotMatchColumns) {
							log.trace("##\t" + builder.builderModifyColumnDateType(c));
							statement.addBatch(builder.builderModifyColumnDateType(c));
						}
						statement.executeBatch();
						if (log.isDebugEnabled()) {
							log.debug("== update size don't match column to DB");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
										+ metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// remove unused column
					if (needDeletedColumns.size() > 0) {
						statement = conn.createStatement();
						for (String key : needDeletedColumns) {
							log.trace("##\t" + builder.builderRemoveColumn(key));
							statement.addBatch(builder.builderRemoveColumn(key));
						}
						statement.executeBatch();
						if (log.isDebugEnabled()) {
							log.debug("== remove unused column");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.debug("\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
										+ metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}
					
				}

				conn.commit();


			} else {
				statement.executeUpdate(builder.builderCreate());
				conn.commit();
			}

		} catch (SQLException e) {
			log.trace(e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new RuntimeException(e);
			}
		}
	}

	public void drop() {
		try {
			conn.createStatement().execute(builder.builderDrop());
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void update(Entity value, Object... keys) {
		log.debug(SQL_UPDATE + " : " + value);
		executeUpdate(SQL_UPDATE, value, keys);
	}

	public void insert(Entity value) {
		log.debug(SQL_INSERT + " : " + value);
		executeUpdate(SQL_INSERT, value);
	}

	public void deleteAll() {
		try {
			conn.createStatement().execute(builder.builderDeleteAll());
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public List<EditableEntity> getAll() {
		return executeQuery(SQL_LIST);
	}

	public void delete(Entity value) {
		log.debug(SQL_DELETE + " : " + value);
		executeUpdate(SQL_DELETE, value);
	}

	public EditableEntity get(Object... keys) {
		log.debug(SQL_GET + " : " + keys);

		List<EditableEntity> list = executeQuery(SQL_GET, keys);
		if (list == null) {
			throw new RuntimeException("Can not find record key:" + keys);
		}

		if (list.size() != 1) {
			throw new RuntimeException("Can not find record key:" + keys);
		}
		return list.get(0);
	}

	private void executeUpdate(PreparedStatement pstmt, Entity v, Object... keys) {
		ResultSet res = null;
		try {

			int pos = fromEntity(pstmt, v);

			for (int i = 0; i < keys.length; i++) {
				pstmt.setObject(pos + i, keys[i]);
			}

			pstmt.executeUpdate();
			conn.commit();
			pstmt.clearParameters();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private List<EditableEntity> executeQuery(PreparedStatement pstmt, Object... keys) {
		ResultSet res = null;
		try {
			int pos = 0;
			for (int i = 0; i < keys.length; i++) {
				pstmt.setObject(pos + i + 1, keys[i]);
			}
			res = pstmt.executeQuery();
			if (log.isDebugEnabled()) {
				log.debug("==\texecuteQuery Open Recordset");
			}
			List<EditableEntity> list = new ArrayList<EditableEntity>();

			while (res.next()) {
				EditableEntity v = toEntity(res);
				list.add(v);
			}

			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) {
					res.close();
					if (log.isDebugEnabled()) {
						log.debug("==\texecuteQuery Close Recordset");
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	int fromEntity(PreparedStatement prepareStatement, Entity entity) throws Exception {
		int pos = 1;
		for (BasicTypeFieldSerializer c : userColumns) {
			pos = c.output(prepareStatement, entity.get(c.fieldName), pos);
		}
		return pos;
	}

	EditableEntity toEntity(ResultSet result) throws Exception {
		EditableEntity entity = new EditableEntity();

		int pos = 1;
		for (BasicTypeFieldSerializer c : userColumns) {
			if(log.isDebugEnabled()){
				log.debug("\t" +  result.getMetaData().getColumnName(pos) + " : " + result.getObject(pos));
			}
			pos = c.inputWithoutCheck(result, pos, entity);
		}
		for (BasicTypeFieldSerializer c : systemColumns) {
			pos = c.inputWithoutCheck(result, pos, entity);
		}
		return entity;
	}

	public void close() {
		try {
			SQL_GET.close();
			SQL_INSERT.close();
			SQL_UPDATE.close();
			SQL_DELETE.close();
			SQL_LIST.close();
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

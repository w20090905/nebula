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

import nebula.data.Entity;
import nebula.data.LiveList;
import nebula.data.impl.EditableEntity;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class DbTransactionDataExecutor implements DbTxDataExecutor {
	private static final Log log = LogFactory.getLog(DbTransactionDataExecutor.class);

	final private Connection conn;

	@SuppressWarnings("unused")
	final private Type type;
	// final private String SQL_DROP;
	final private PreparedStatement SQL_GET;
	final private PreparedStatement SQL_INSERT;
	final private PreparedStatement SQL_UPDATE;
	final private PreparedStatement SQL_DELETE;
	final private PreparedStatement SQL_LIST;
	final private PreparedStatement SQL_MAX_ID;

	// final private String SQL_COUNT;

	final DatabaseColumn[] userColumns;
	final DatabaseColumn[] systemColumns;
	// final private String[] realFields;
	// final private DbColumn[] keyColumns;
	final DbMasterDataSqlHelper builder;
	final DbConfiguration config;

	final EntityFieldSerializer serializer;

	// final private Map<String, Integer> map;

	// final private String[] systemFields = new String[] { "TIMESTAMP_" };

	public DbTransactionDataExecutor(final DbConfiguration config, final Connection conn, final Type type,
			final DbMasterDataSqlHelper helper) {
		this.config = config;
		this.type = type;
		this.conn = conn;

		builder = helper;

		// SQL_DROP = builder.builderDrop();

		userColumns = builder.getUserColumns();
		systemColumns = builder.getSystemColumns();

		serializer = builder.getEntitySerializer();

		this.ensureDBSchema();

		try {
			SQL_GET = conn.prepareStatement(builder.builderGet());
			SQL_INSERT = conn.prepareStatement(builder.builderInsert());
			SQL_UPDATE = conn.prepareStatement(builder.builderUpdate());
			SQL_DELETE = conn.prepareStatement(builder.builderDelete());
			SQL_LIST = conn.prepareStatement(builder.builderList());
			SQL_MAX_ID = conn.prepareStatement(builder.builderMaxId());
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	private void ensureDBSchema() {
		Statement statement = null;
		boolean exist = false;
		ResultSet rs = null;
		try {

			final LiveList<String, DatabaseColumn> mapColumns = new LiveList<String, DatabaseColumn>(
					new Function<DatabaseColumn, String>() {
						@Override
						public String apply(DatabaseColumn data) {
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

				if (log.isTraceEnabled()) log.trace("\tcheck columns define");

				ArrayList<String> needDeletedColumns = new ArrayList<String>();
				Map<String, String> allColumns = new HashMap<String, String>();
				ArrayList<DatabaseColumn> typeNotMatchColumns = new ArrayList<DatabaseColumn>();
				ArrayList<DatabaseColumn> typeSizeNotMatchColumns = new ArrayList<DatabaseColumn>();

				String curKeys = "";

				for (int i = 1; i <= columnsSize; i++) {
					String columnName = metaData.getColumnName(i);

					DatabaseColumn newColumn = mapColumns.get(columnName);
					if (newColumn == null) {
						if (log.isTraceEnabled())
							log.trace("\t\t" + metaData.getColumnName(i)
									+ "\t column type not exist in type or is system column \t- "
									+ metaData.getColumnTypeName(i) + "\t" + metaData.getColumnDisplaySize(i));
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
							if (log.isTraceEnabled())
								log.trace("\t\t" + metaData.getColumnName(i)
										+ "\t column type not match,need update \t- " + metaData.getColumnTypeName(i)
										+ "\t" + metaData.getColumnDisplaySize(i));
							typeNotMatchColumns.add(newColumn);

						} else {
							switch (jdbcType) {
							case Types.DECIMAL:
								if (newColumn.precision == precision && newColumn.scale == scale) {

								} else if (newColumn.precision > precision && newColumn.scale > scale) {

									if (log.isTraceEnabled())
										log.trace("\t" + metaData.getColumnName(i)
												+ "\t\t column size not match,need update \t- "
												+ metaData.getColumnTypeName(i) + "\t"
												+ metaData.getColumnDisplaySize(i));
									typeSizeNotMatchColumns.add(newColumn);
								} else if (newColumn.precision > precision || newColumn.scale > scale) {
									int newPrecision = newColumn.precision > precision ? newColumn.precision
											: precision;
									int newScale = newColumn.scale > scale ? newColumn.scale : scale;
									typeSizeNotMatchColumns.add(new DatabaseColumn(newColumn.fieldName,
											newColumn.columnName, newColumn.key, newColumn.nullable, false,
											newColumn.rawType, newColumn.size, newPrecision, newScale));

									if (log.isTraceEnabled())
										log.trace("\t" + metaData.getColumnName(i)
												+ "\t\tcolumn precision not match,need update \t- "
												+ metaData.getColumnTypeName(i) + "\t"
												+ metaData.getColumnDisplaySize(i));
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

				}
				rs.close();
				conn.commit();

				if (log.isTraceEnabled()) log.trace("\tcheck columns define finished");

				if (log.isTraceEnabled()) log.trace("\tcheck key define");
				StringBuffer newKeys = new StringBuffer();
				ArrayList<DatabaseColumn> notExistColumns = new ArrayList<DatabaseColumn>();
				for (DatabaseColumn f : mapColumns) {
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
				if (log.isTraceEnabled()) log.trace("\tcheck key define finished");

				// key changed
				if (isKeyChanged) {
					statement = conn.createStatement();
					if (log.isTraceEnabled()) log.trace("\t[" + builder.tableName + "] key changed,reconstruct ");
					statement.addBatch(builder.builderDrop());
					statement.addBatch(builder.builderCreate());
					statement.executeBatch();
					log.trace("\t[" + builder.tableName + "] reconstruct finished table  finished");

					if (log.isTraceEnabled()) {
						if (log.isTraceEnabled())
							log.trace("\t[" + builder.tableName + "] table layout after add not exist column");
						rs = statement.executeQuery(builder.builderGetMeta());
						metaData = rs.getMetaData();
						columnsSize = metaData.getColumnCount();
						for (int i = 1; i <= columnsSize; i++) {
							log.trace("\t\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i) + "\t"
									+ metaData.getColumnDisplaySize(i));
						}
						rs.close();
					}
				} else {
					// add not exist column to DB
					if (notExistColumns.size() > 0) {
						if (log.isTraceEnabled()) log.trace("\tadd not exist column");
						statement = conn.createStatement();
						for (DatabaseColumn c : notExistColumns) {
							log.trace("\t\t add column - " + builder.builderAddColumn(c));
							statement.addBatch(builder.builderAddColumn(c));
						}
						statement.executeBatch();

						if (log.isTraceEnabled()) log.trace("\tadd not exist column finished");
						if (log.isTraceEnabled()) {
							if (log.isTraceEnabled())
								log.trace("\t[" + builder.tableName + "] table layout after add not exist column");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.trace("\t\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i)
										+ "\t" + metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// update don't match column to DB
					if (typeNotMatchColumns.size() > 0) {
						if (log.isTraceEnabled()) log.trace("\tupdate don't match column");
						statement = conn.createStatement();
						for (DatabaseColumn c : typeNotMatchColumns) {
							log.trace("\t\tupdate column - " + builder.builderModifyColumnDateType(c));
							statement.addBatch(builder.builderRemoveColumn(c.columnName));
							statement.addBatch(builder.builderAddColumn(c));
						}
						statement.executeBatch();
						if (log.isTraceEnabled()) log.trace("\tupdate don't match column finished");
						if (log.isTraceEnabled()) {
							if (log.isTraceEnabled())
								log.trace("\t[" + builder.tableName + "] table layout after update don't match column");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.trace("\t\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i)
										+ "\t" + metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// update size don't match column to DB
					if (typeSizeNotMatchColumns.size() > 0) {
						if (log.isTraceEnabled()) log.trace("\tupdate size don't match column");
						statement = conn.createStatement();
						for (DatabaseColumn c : typeSizeNotMatchColumns) {
							log.trace("\t\tmodify column - " + builder.builderModifyColumnDateType(c));
							statement.addBatch(builder.builderModifyColumnDateType(c));
						}
						statement.executeBatch();
						if (log.isTraceEnabled()) log.trace("\tupdate size don't match column finished");

						if (log.isTraceEnabled()) {
							if (log.isTraceEnabled())
								log.trace("\t[" + builder.tableName
										+ "] table layout after update size don't match column");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.debug("\t\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i)
										+ "\t" + metaData.getColumnDisplaySize(i));
							}
							rs.close();
						}
					}

					// remove unused column
					if (needDeletedColumns.size() > 0) {
						if (log.isTraceEnabled()) log.trace("\tremove unused column");
						statement = conn.createStatement();
						for (String key : needDeletedColumns) {
							log.trace("\t\tremove column - " + builder.builderRemoveColumn(key));
							statement.addBatch(builder.builderRemoveColumn(key));
						}
						statement.executeBatch();
						if (log.isTraceEnabled()) log.trace("\tremove unused column finished");

						if (log.isTraceEnabled()) {
							if (log.isTraceEnabled())
								log.trace("\t[" + builder.tableName + "] table layout after  remove unused column");
							rs = statement.executeQuery(builder.builderGetMeta());
							metaData = rs.getMetaData();
							columnsSize = metaData.getColumnCount();
							for (int i = 1; i <= columnsSize; i++) {
								log.trace("\t\t" + metaData.getColumnName(i) + "\t" + metaData.getColumnTypeName(i)
										+ "\t" + metaData.getColumnDisplaySize(i));
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
				log.error(e.getClass().getName(), e);
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void drop() {
		try {
			conn.createStatement().execute(builder.builderDrop());
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
		}
	}

	@Override
	public void update(Entity value, Object... keys) {
		log.debug(SQL_UPDATE + " : " + value);
		executeUpdate(SQL_UPDATE, value, keys);
	}

	@Override
	public void insert(Entity value) {
		log.debug("\tSQL_INSERT : " + value);
		executeUpdate(SQL_INSERT, value);
	}

	@Override
	public void deleteAll() {
		try {
			conn.createStatement().execute(builder.builderDeleteAll());
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<EditableEntity> getAll() {
		return executeQuerEntity(SQL_LIST); // Only hot data
	}

	@Override
	public void delete(Entity value) {
		log.debug(SQL_DELETE + " : " + value);
		executeUpdate(SQL_DELETE, value);
	}

	@Override
	public EditableEntity get(Object... keys) {
		if (log.isTraceEnabled()) log.trace("\tSQL_GET : " + Joiner.on(',').join(keys));

		List<EditableEntity> list = executeQuerEntity(SQL_GET, keys);
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

			int pos = serializer.fromEntity(pstmt, v);

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

	private List<EditableEntity> executeQuerEntity(PreparedStatement pstmt, Object... keys) {
		ResultSet res = null;
		try {
			int pos = 0;
			for (int i = 0; i < keys.length; i++) {
				pstmt.setObject(pos + i + 1, keys[i]);
			}
			res = pstmt.executeQuery();
			if (log.isTraceEnabled()) {
				log.trace("\texecuteQuery Open Recordset");
			}
			List<EditableEntity> list = new ArrayList<EditableEntity>();

			while (res.next()) {
				EditableEntity v = serializer.toEntity(res);
				list.add(v);
			}

			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) {
					res.close();
					if (log.isTraceEnabled()) {
						log.trace("\texecuteQuery Close Recordset");
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void close() {
		try {
			SQL_GET.close();
			SQL_INSERT.close();
			SQL_UPDATE.close();
			SQL_DELETE.close();
			SQL_LIST.close();
		} catch (SQLException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getCurrentMaxID() {
		ResultSet res = null;
		try {
			res = SQL_MAX_ID.executeQuery();
			if (log.isTraceEnabled()) {
				log.trace("\texecuteQuery Open Recordset");
			}
			while (res.next()) {
				return res.getLong(1);
			}
			return 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) {
					res.close();
					if (log.isTraceEnabled()) {
						log.trace("\texecuteQuery Close Recordset");
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}

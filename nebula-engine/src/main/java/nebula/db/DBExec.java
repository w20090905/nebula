package nebula.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.lang.Field;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBExec {
	private static final Log log = LogFactory.getLog(DBExec.class);

	final private Connection conn;

	final private Type type;
	final private String tableName;
	// final private String SQL_DROP;
	final private String SQL_CREATE;

	final private String SQL_GET;
	final private String SQL_GETMETA;
	final private String SQL_INSERT;
	final private String SQL_UPDATE;
	final private String SQL_DELETE;
	final private String SQL_DELETEALL;
	final private String SQL_LIST;
	// final private String SQL_COUNT;

	final private DbColumn[] columns;
	final private String[] realFields;
//	final private DbColumn[] keyColumns;
	final private SqlHelper builder;

	// final private Map<String, Integer> map;

//	final private String[] systemFields = new String[] { "TIMESTAMP_" };

	public DBExec(Connection conn, Type type, SqlHelper helper) {
		this.type = type;
		this.conn = conn;

		builder = helper;
		this.tableName = builder.getTableName();

		// SQL_DROP = builder.builderDrop();
		SQL_CREATE = builder.builderCreate();

		SQL_GET = builder.builderGet();
		SQL_GETMETA = builder.builderGetMeta();
		SQL_INSERT = builder.builderInsert();
		SQL_UPDATE = builder.builderUpdate();
		SQL_DELETE = builder.builderDelete();
		SQL_DELETEALL = builder.builderDeleteAll();

		columns = builder.builderColumns();

		realFields = new String[columns.length];
		for (int i = 0; i < columns.length; i++) {
			realFields[i] = columns[i].name;
		}

		// SQL_COUNT = builder.builderCount();
		SQL_LIST = builder.builderList();

//		keyColumns = builder.getKeyColumns();

		// Map<String, Integer> tmpMap = new HashMap<String, Integer>();
		// for (int i = 0; i < realFields.length; i++) {
		// tmpMap.put(realFields[i], i);
		// }
		// map = tmpMap;
	}

	public void init() {
		try {
			Statement statement = null;
			boolean exist = false;
			ResultSet rs = null;

			try {
				statement = conn.createStatement();
				rs = statement.executeQuery(SQL_GETMETA);
				exist = true;
				ResultSetMetaData metaData = rs.getMetaData();
				int columnsSize = metaData.getColumnCount();

				Map<String, String> cols = new HashMap<String, String>();

				if (log.isDebugEnabled()) log.debug("== Before update column ");

				for (int i = 0; i < columnsSize; i++) {
					cols.put(metaData.getColumnName(i + 1).toUpperCase(), metaData.getColumnTypeName(i + 1));
					if (log.isDebugEnabled()) {
						log.debug(metaData.getColumnName(i + 1) + "  \t");
						log.debug(metaData.getColumnDisplaySize(i + 1) + "\t");
						log.debug(metaData.getColumnTypeName(i + 1));
					}
				}

				ArrayList<String> notExistColumns = new ArrayList<String>();
				for (DbColumn f : columns) {
					if (!cols.containsKey(f.name.toUpperCase())) {
						notExistColumns.add(f.name);
					}
				}

				if (notExistColumns.size() > 0) {
					for (String fieldName : notExistColumns) {
						statement.addBatch("ALTER TABLE " + this.tableName + " ADD COLUMN " + fieldName
								+ " VARCHAR(40)");
					}
					statement.executeBatch();

					rs = statement.executeQuery(SQL_GETMETA);
					metaData = rs.getMetaData();
					columnsSize = metaData.getColumnCount();
					log.debug("== After update column ");
					for (int i = 0; i < columnsSize; i++) {
						log.debug(metaData.getColumnName(i + 1) + "  \t");
						log.debug(metaData.getColumnDisplaySize(i + 1) + "\t");
						log.debug(metaData.getColumnTypeName(i + 1));
					}
				}
				conn.commit();
			} catch (SQLException e) {
			}

			if (!exist) {
				statement.execute(SQL_CREATE);
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Map<String, Object> value, Object... keys) {
		log.debug(SQL_UPDATE + " : " + value);
		execute(conn, SQL_UPDATE, value, keys);
	}

	public void insert(Map<String, Object> value) {
		log.debug(SQL_INSERT + " : " + value);
		execute(conn, SQL_INSERT, value);
	}

	public void deleteAll() {
		execute(conn, SQL_DELETEALL);
	}

	public List<Map<String, Object>> getAll() {
		return query(conn, SQL_LIST);
	}

	public void delete(Map<String, Object> value) {
		log.debug(SQL_DELETE + " : " + value);
		execute(conn, SQL_DELETE, value);
	}

	public Map<String, Object> get(Object... keys) {
		log.debug(SQL_GET + " : " + keys);
		return get(conn, SQL_GET, keys);
	}

	Map<String, Object> get(Connection conn, String sql, Object... keys) {
		PreparedStatement statement = null;
		ResultSet res = null;
		try {
			statement = conn.prepareStatement(sql);

			for (int i = 1; i <= keys.length; i++) {
				statement.setObject(i, keys[i - 1]);
			}

			res = statement.executeQuery();

			if (!res.next()) {
				return null;
			}

			Map<String, Object> v = fillObject(res);

			if (res.next()) {
				throw new RuntimeException("Can not find record key:");
			}

			return v;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	List<Map<String, Object>> query(Connection conn, String sql, Object... keys) {
		PreparedStatement statement = null;
		ResultSet res = null;
		try {

			statement = conn.prepareStatement(sql);

			for (int i = 1; i <= keys.length; i++) {
				statement.setObject(i, keys[i]);
			}

			res = statement.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			while (res.next()) {
				Map<String, Object> v = fillObject(res);
				list.add(v);
			}

			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			log.debug("== SUCCEED When exec [[ " + sql + " ]]");

			try {
				if (res != null) res.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void execute(Connection conn, String sql, Object... keys) {
		PreparedStatement statement = null;
		ResultSet res = null;
		try {

			statement = conn.prepareStatement(sql);

			int pos = 0;

			for (int i = 0; i < keys.length; i++) {
				statement.setObject(pos + i + 1, keys[i]);
			}

			statement.execute();

			conn.commit();

			log.debug("== SUCCEED When exec " + sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void execute(Connection conn, String sql, Map<String, Object> v, Object... keys) {
		PreparedStatement statement = null;
		ResultSet res = null;
		try {

			statement = conn.prepareStatement(sql);

			int pos = fillParameter(statement, v);

			for (int i = 0; i < keys.length; i++) {
				statement.setObject(pos + i + 1, keys[i]);
			}

			statement.execute();

			conn.commit();

			log.debug("== SUCCEED When exec " + sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	int fillParameter(PreparedStatement prepareStatement, Map<String, Object> v) throws SQLException {
		int pos = 0;
		for (Field f : type.getFields()) {
			if (!f.isArray() && f.getRefer() == Field.SCALA) {
				prepareStatement.setString(1 + pos, (String) v.get(f.getName()));
				if(log.isTraceEnabled())
				log.trace((1 + pos) + " " + f.getName());
				pos++;
			}
		}
		return pos;
	}

	Map<String, Object> fillObject(ResultSet result) throws SQLException {
		Map<String, Object> v = new HashMap<>();
		int pos = 0;
		for (Field f : type.getFields()) {
			if (!f.isArray() && f.getRefer() == Field.SCALA) {
				v.put(f.getName(), result.getString(pos + 1));
				pos++;
			}
		}
		return v;
	}
}

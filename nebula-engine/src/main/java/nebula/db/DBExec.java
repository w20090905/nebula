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

	final private DbColumn[] columns;
	// final private String[] realFields;
	// final private DbColumn[] keyColumns;
	final private SqlHelper builder;

	// final private Map<String, Integer> map;

	// final private String[] systemFields = new String[] { "TIMESTAMP_" };

	public DBExec(DbConfiguration config, Connection conn, Type type, SqlHelper helper) {
		this.type = type;
		this.conn = conn;

		builder = helper;

		// SQL_DROP = builder.builderDrop();

		columns = builder.builderColumns();

		this.init();

		try {
			SQL_GET = conn.prepareStatement(builder.builderGet());
			SQL_INSERT = conn.prepareStatement(builder.builderInsert());
			SQL_UPDATE = conn.prepareStatement(builder.builderUpdate());
			SQL_DELETE = conn.prepareStatement(builder.builderDelete());
			SQL_LIST = conn.prepareStatement(builder.builderList());
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public void init() {
		try {
			Statement statement = null;
			boolean exist = false;
			ResultSet rs = null;

			try {

				statement = conn.createStatement();
				rs = statement.executeQuery(builder.builderGetMeta());
				exist = true;
				ResultSetMetaData metaData = rs.getMetaData();
				int columnsSize = metaData.getColumnCount();

				Map<String, String> cols = new HashMap<String, String>();

				if (log.isDebugEnabled()) log.debug("== Before update column ");

				for (int i = 0; i < columnsSize; i++) {
					cols.put(metaData.getColumnName(i + 1).toUpperCase(), metaData.getColumnTypeName(i + 1));
					if (log.isDebugEnabled()) {
						log.debug("\t" + metaData.getColumnName(i + 1) + "  \t" + metaData.getColumnDisplaySize(i + 1)
								+ "\t" + metaData.getColumnTypeName(i + 1));
					}
				}

				ArrayList<DbColumn> notExistColumns = new ArrayList<DbColumn>();
				for (DbColumn f : columns) {
					if (!cols.containsKey(f.columnName)) {
						notExistColumns.add(f);
					}
				}

				if (notExistColumns.size() > 0) {
					for (DbColumn c : notExistColumns) {
						statement.addBatch(builder.builderAddColumn(c));
					}
					statement.executeBatch();

					rs = statement.executeQuery(builder.builderGetMeta());
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
				statement.executeUpdate(builder.builderCreate());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void drop() {
		try {
			conn.createStatement().execute(builder.builderDrop());
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		}			
	}
	public void update(Map<String, Object> value, Object... keys) {
		log.debug(SQL_UPDATE + " : " + value);
		executeUpdate(SQL_UPDATE, value, keys);
	}

	public void insert(Map<String, Object> value) {
		log.debug(SQL_INSERT + " : " + value);
		executeUpdate(SQL_INSERT, value);
	}

	public void deleteAll() {
		try {
			conn.createStatement().execute(builder.builderDeleteAll());
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		}			
	}

	public List<Map<String, Object>> getAll() {
		return executeQuery(SQL_LIST);
	}

	public void delete(Map<String, Object> value) {
		log.debug(SQL_DELETE + " : " + value);
		executeUpdate(SQL_DELETE, value);
	}

	public Map<String, Object> get(Object... keys) {
		log.debug(SQL_GET + " : " + keys);

		List<Map<String, Object>> list = executeQuery(SQL_GET, keys);
		if (list == null) {
			throw new RuntimeException("Can not find record key:" + keys);
		}

		if (list.size() != 1) {
			throw new RuntimeException("Can not find record key:" + keys);
		}
		return list.get(0);
	}

	private void executeUpdate(PreparedStatement pstmt, Map<String, Object> v, Object... keys) {
		ResultSet res = null;
		try {

			int pos = fromEntity(pstmt, v);

			for (int i = 0; i < keys.length; i++) {
				pstmt.setObject(pos + i + 1, keys[i]);
			}

			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private List<Map<String, Object>> executeQuery(PreparedStatement pstmt, Object... keys) {
		ResultSet res = null;
		try {
			int pos = 0;
			for (int i = 0; i < keys.length; i++) {
				pstmt.setObject(pos + i + 1, keys[i]);
			}

			res = pstmt.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			while (res.next()) {
				Map<String, Object> v = toEntity(res);
				list.add(v);
			}

			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (res != null) res.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	int fromEntity(PreparedStatement prepareStatement, Map<String, Object> v) throws SQLException {
		int pos = 0;
		for (DbColumn c : columns) {			
			c.datadealer.writeTo(1 + pos,  v.get(c.fieldName), prepareStatement);
			pos++;
		}
		return pos;
	}

	Map<String, Object> toEntity(ResultSet result) throws SQLException {
		Map<String, Object> v = new HashMap<String, Object>();

		int pos = 0;
		for (DbColumn c : columns) {
			v.put(c.fieldName, c.datadealer.readFrom(result, pos + 1));
			pos++;
		}
		return v;
	}
}

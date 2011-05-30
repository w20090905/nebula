package auto.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractBaseDao {

	private static String url = "jdbc:mysql://localhost:3306/bookstore";
	private static String user = "root";
	private static String password = "1234";
	private static String driver = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> List<T> transforResultSetToEntityList(ResultSet rs, Class<T> clazz) {

		List<T> l = new ArrayList<T>();
		if (rs != null) {
			try {
				while (rs.next()) {
					T t = clazz.newInstance();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						String columnName = rs.getMetaData().getColumnName(i);
						Field f = clazz.getDeclaredField(StringUtils.uncapitalize(columnName.substring(columnName.lastIndexOf("_") + 1)));
						f.setAccessible(true);
						f.set(t, rs.getObject(i));
					}
					l.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getCause());
			}
		}

		return l;
	}

	public <T> List<T> select(Class<T> clazz, String sql, Object... params) {
		Connection conn = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}
			}
			rs = st.executeQuery();
			return transforResultSetToEntityList(rs, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		} finally {
			free(rs, st, conn);
		}
    }
	
	public <T> T selectOne(Class<T> clazz, String sql, Object... params) {
		List<T> l = select(clazz, sql, params);
		return l.isEmpty() ? null : l.get(0);
    }

	public int update(String sql, Object... params) {
		Connection conn = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}
			}
			return st.executeUpdate();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1.getCause());
			}
			throw new RuntimeException(e.getCause());
		} finally {
			free(rs, st, conn);
		}
    }

}

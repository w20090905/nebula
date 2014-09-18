package nebula.data.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	void setConn(Connection conn);
	List<T> query() throws SQLException;
	T get(int id) throws SQLException;
}

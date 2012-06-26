package nebula.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.db.derby.DerbyConfiguration;
import nebula.db.oracle.OracleConfiguration;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DbConfiguration {

	private static final Log log = LogFactory.getLog(DbConfiguration.class);
	protected final String driverClass;
	protected final String url;
	protected final String userName;
	protected final String userPassword;
	protected Connection conn = null;

	public DbConfiguration(String driverClass, String url, String userName, String password) {
		this.driverClass = driverClass;
		this.url = url;
		this.userName = userName;
		this.userPassword = password;
	}

	public void init() {
		try {
			Class.forName(driverClass).newInstance();
			log.debug("== Load " + driverClass);
			conn = DriverManager.getConnection(this.url, this.userName, this.userPassword);
			log.debug("== create and connect to " + this.url);
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static DbConfiguration getEngine(String driverClass, String url, String userName, String password) {
		String dbms = url.split(":")[1].toUpperCase();
		DbConfiguration dbEngine = null;
		if ("DERBY".equals(dbms)) {
			dbEngine = new DerbyConfiguration(driverClass, url, userName, password);
		} else if ("ORACLE".equals(dbms)) {
			dbEngine = new OracleConfiguration(driverClass, url, userName, password);
		} else {
			throw new UnsupportedOperationException();
		}
		dbEngine.init();
		return dbEngine;
	}

//	public abstract <T extends HasID> Persistence<T> getPersister(Class<T> t, Type type);

	public abstract DBExec getPersister(Type type);

	public void shutdown() {
		try {
			if (conn != null) {
				conn.commit();
				conn.close();
			}
			log.debug("== Database disconnect");
		} catch (SQLException e) {
			log.debug("Exception When destroy db");
			log.debug(e);
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
	}

}

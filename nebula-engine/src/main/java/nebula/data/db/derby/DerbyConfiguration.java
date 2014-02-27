package nebula.data.db.derby;

import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbSqlHelper;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DerbyConfiguration extends DbConfiguration {
	private static final Log log = LogFactory.getLog(DerbyConfiguration.class);

	public DerbyConfiguration(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password);
		if (log.isTraceEnabled()) {
			log.trace("init OracleConfiguration");
		}
	}

	@Override
	public void shutdown() {
		super.shutdown();

		try { // perform a clean shutdown
			String shutdownUrl = this.url.replaceAll(";create=true", ";shutdown=true");
			DriverManager.getConnection(shutdownUrl);
			log.info("== shut down database s- " + shutdownUrl);
		} catch (SQLException se) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
	}

	@Override
	public DbSqlHelper builderSQLHelper(Type type) {
		return new DerbySQLHelper(this, type);
	}

}

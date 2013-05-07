package nebula.data.db.derby;

import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbDataExecutor;
import nebula.data.db.DbMasterDataExecutor;
import nebula.data.db.DbTransactionDataExecutor;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DerbyConfiguration extends DbConfiguration {
	private static final Log log = LogFactory.getLog(DerbyConfiguration.class);

	public DerbyConfiguration(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password);
	}

	@Override
	public DbDataExecutor getPersister(Type type) {
		log.debug("== getPersister : " + type.getName() + " conn : " + conn);
		DbDataExecutor executor = null;

		switch (type.getStandalone()) {
		case Transaction:
			executor = new DbTransactionDataExecutor(this, conn, type, new DerbySQLHelper(this, type));
			break;

		default:
			executor = new DbMasterDataExecutor(this, conn, type, new DerbySQLHelper(this, type));
			break;
		}

		return executor;
	}

	@Override
	public void shutdown() {
		super.shutdown();

		try { // perform a clean shutdown
			DriverManager.getConnection(this.url.replaceAll(";create=true", ";shutdown=true"));
			log.debug("== Database shut down normally");
		} catch (SQLException se) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
	}

}

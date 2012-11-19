package nebula.data.db.derby;

import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.data.db.DBExec;
import nebula.data.db.DbConfiguration;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DerbyConfiguration extends DbConfiguration {
	private static final Log log = LogFactory.getLog(DerbyConfiguration.class);

	public DerbyConfiguration(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password);
	}

	@Override
	public DBExec getPersister(Type type) {
		log.debug("== getPersister : " + type.getName());
		return new DBExec(this, conn, type, new DerbySQLHelper(this,type));
	}

	@Override
	public void shutdown() {
		super.shutdown();

		try { // perform a clean shutdown
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
			log.debug("== Database shut down normally");
		} catch (SQLException se) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
	}

}

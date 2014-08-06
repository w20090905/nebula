package nebula.data.db;

import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.data.db.DbConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DerbyConfiguration extends DbConfiguration {
	private static final Log log = LogFactory.getLog(DerbyConfiguration.class);

	public DerbyConfiguration(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password);
	}

	@Override
	protected void finalize() {
		try { // perform a clean shutdown
			String shutdownUrl = this.dbURL.replaceAll(";create=true", ";shutdown=true");
			DriverManager.getConnection(shutdownUrl);
			if(log.isDebugEnabled()){
				log.info("** shut down database - " + shutdownUrl + " ** ");				
			}
		} catch (SQLException se) {
		}
	}

}

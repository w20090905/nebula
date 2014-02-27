package nebula.data.db.oracle;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbSqlHelper;
import nebula.data.db.derby.DerbySQLHelper;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OracleConfiguration extends DbConfiguration {
	private static final Log log = LogFactory.getLog(OracleConfiguration.class);

	public OracleConfiguration(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password);
		if (log.isTraceEnabled()) {
			log.trace("init OracleConfiguration");
		}
	}

	@Override
	public DbSqlHelper builderSQLHelper(Type type) {
		return new DerbySQLHelper(this, type);
	}
}

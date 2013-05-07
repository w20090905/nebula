package nebula.data.db.oracle;

import nebula.data.db.DbMasterDataExecutor;
import nebula.data.db.DbConfiguration;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OracleConfiguration extends DbConfiguration {
    private static final Log log = LogFactory.getLog(OracleConfiguration.class);

    public OracleConfiguration(String driverClass, String url, String userName, String password) {
        super(driverClass, url, userName, password);
    }

    @Override
    public DbMasterDataExecutor getPersister(Type type) {
        log.debug("== getPersister : " + type.getName());
        return new DbMasterDataExecutor(this,conn, type, new OracleSQLHelper(this,type));
    }
}

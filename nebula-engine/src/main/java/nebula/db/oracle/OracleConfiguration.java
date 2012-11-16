package nebula.db.oracle;

import nebula.db.DBExec;
import nebula.db.DbConfiguration;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OracleConfiguration extends DbConfiguration {
    private static final Log log = LogFactory.getLog(OracleConfiguration.class);

    public OracleConfiguration(String driverClass, String url, String userName, String password) {
        super(driverClass, url, userName, password);
    }

    @Override
    public DBExec getPersister(Type type) {
        log.debug("== getPersister : " + type.getName());
        return new DBExec(this,conn, type, new OracleSQLHelper(this,type));
    }
}

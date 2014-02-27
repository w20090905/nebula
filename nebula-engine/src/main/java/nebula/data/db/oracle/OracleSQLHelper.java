package nebula.data.db.oracle;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbSqlHelper;
import nebula.lang.Type;

public class OracleSQLHelper extends DbSqlHelper {

    OracleSQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }
}

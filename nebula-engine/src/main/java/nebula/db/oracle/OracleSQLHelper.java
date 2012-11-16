package nebula.db.oracle;

import nebula.db.DbConfiguration;
import nebula.db.SqlHelper;
import nebula.lang.Type;

public class OracleSQLHelper extends SqlHelper {

    OracleSQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }
}

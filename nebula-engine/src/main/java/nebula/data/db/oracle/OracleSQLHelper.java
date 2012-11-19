package nebula.data.db.oracle;

import nebula.data.db.DbConfiguration;
import nebula.data.db.SqlHelper;
import nebula.lang.Type;

public class OracleSQLHelper extends SqlHelper {

    OracleSQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }
}

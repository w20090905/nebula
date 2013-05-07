package nebula.data.db.oracle;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbMasterDataSqlHelper;
import nebula.lang.Type;

public class OracleSQLHelper extends DbMasterDataSqlHelper {

    OracleSQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }
}

package nebula.data.db.derby;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbMasterDataSqlHelper;
import nebula.lang.Type;

public class DerbySQLHelper extends DbMasterDataSqlHelper {
    DerbySQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }

}

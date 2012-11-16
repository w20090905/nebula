package nebula.db.derby;

import nebula.db.DbConfiguration;
import nebula.db.SqlHelper;
import nebula.lang.Type;

public class DerbySQLHelper extends SqlHelper {
    DerbySQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }

}

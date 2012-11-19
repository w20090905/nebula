package nebula.data.db.derby;

import nebula.data.db.DbConfiguration;
import nebula.data.db.SqlHelper;
import nebula.lang.Type;

public class DerbySQLHelper extends SqlHelper {
    DerbySQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }

}

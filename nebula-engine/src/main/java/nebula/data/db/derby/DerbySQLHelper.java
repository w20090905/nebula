package nebula.data.db.derby;

import nebula.data.db.DbConfiguration;
import nebula.data.db.DbSqlHelper;
import nebula.lang.Type;

public class DerbySQLHelper extends DbSqlHelper {
	public DerbySQLHelper(final DbConfiguration config, final Type type) {
        super(config,type);
    }

}

package nebula.data.n;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nebula.lang.system.Type;
import nebula.lang.system.Type.Field;
import nebula.persistor.NebulaContext;

public class PHelper {
    Type type;

    PHelper(Type type) {
        this.type = type;
    }

    OpRead[] read() {

        for (Field f : type.getFields()) {
            if (f.getType().getName().equals(String.class.getName())) {

            }
        }

        return null;
    }

    static abstract class OpRead {
        final int iO;
        final int iDB;

        public OpRead(int iO, int iDB) {
            this.iO = iO;
            this.iDB = iDB;
        }

        abstract void reader(NebulaContext context,Object[] data, ResultSet result) throws SQLException;

        abstract void write(NebulaContext context,Object[] data, PreparedStatement p) throws SQLException;
    }

    // // v.name
    // al[1] = r.getString(++i);
    // // v.fullName
    // al[2] = r.getString(++i);
    // // v.lastModified
    // al[3] = r.getLong(++i);

    // String
    static public class OpString extends OpRead {
        public OpString(int iO, int iDB) {
            super(iO, iDB);
        }

        @Override
        public void reader(NebulaContext context,Object[] data, ResultSet result) throws SQLException {
            data[iO] = result.getString(iDB);
        }

        @Override
        public void write(NebulaContext context,Object[] data, PreparedStatement p) throws SQLException {
            p.setString(iDB, (String) data[iO]);
        }
    }

    // Long
    static public class OpLong extends OpRead {
        public OpLong(int iO, int iDB) {
            super(iO, iDB);
        }

        @Override
        public void reader(NebulaContext context,Object[] data, ResultSet result) throws SQLException {
            data[iO] = result.getLong(iDB);
        }

        @Override
        public void write(NebulaContext context,Object[] data, PreparedStatement p) throws SQLException {
            p.setLong(iDB, (Long) data[iO]);
        }
    }

    // Long
    static public class OpTimestamp extends OpRead {
        public OpTimestamp(int iO, int iDB) {
            super(iO, iDB);
        }

        @Override
        public void reader(NebulaContext context,Object[] data, ResultSet result) throws SQLException {
            data[iO] = result.getLong(iDB);
        }

        @Override
        public void write(NebulaContext context,Object[] data, PreparedStatement p) throws SQLException {
            p.setLong(iDB, context.getTime());
        }
    }
}

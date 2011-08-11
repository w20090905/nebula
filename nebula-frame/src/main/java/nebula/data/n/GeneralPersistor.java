package nebula.data.n;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nebula.persistor.NebulaContext;
import nebula.persistor.Persistor;
import nebula.persistor.PersistorException;
import nebula.persistor.db.ConnectionProvider;

import com.google.inject.Inject;

public class GeneralPersistor implements Persistor<VV> {
    final NebulaContext context;
    final ConnectionProvider cp;

    @Inject
    protected GeneralPersistor(ConnectionProvider cp, NebulaContext context) {
        this.context = context;
        this.cp = cp;
    }

    @Override
    public VV get(String... keys) {
        try {
            PreparedStatement p;
            ResultSet r;
            int i;

            p = cp.get().prepareStatement("select name,fullName,lastModified from company where name = ?");
            p.setString(1, keys[0]);

            r = p.executeQuery();
            if (!r.next()) {
                throw new PersistorException();
            }

            // ------ start

            Object[] al = new Object[10];
            i = 0;

            // v.name
            al[1] = r.getString(++i);
            // v.fullName
            al[2] = r.getString(++i);
            // v.lastModified
            al[3] = r.getLong(++i);

            // ------ end

            r.close();

            VV v = new VV(al);

            return v;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public VV merge(VV v) {
        return v;
    }

    @Override
    public void persist(VV v) {
        try {
            int i = 0;
            PreparedStatement p;

            p = cp.get().prepareStatement("insert into company(name,fullName,lastModified) values(?,?,?)");

            i = 0;

            Object[] data = v.data;

            p.setString(++i, (String) data[1]);// .name);
            p.setString(++i, (String) data[2]);// .fullName);
            p.setLong(++i, context.getTime());

            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

            int updateCount = p.getUpdateCount();
            if (updateCount != 1) {
                throw new PersistorException();
            }

        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public void remove(VV v) {
        try {

            PreparedStatement p;
            p = cp.get().prepareStatement("delete from company where company.name = ?");
            p.setString(1, (String) v.data[0]);

            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public void removeAll() {
        try {

            PreparedStatement p;

            p = cp.get().prepareStatement("delete from company");

            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public List<VV> list() {
        try {
            int i = 0;
            Statement p = cp.get().createStatement();
            ResultSet r = p.executeQuery("select name,fullname,lastModified "
                    + "from company p order by p.lastModified desc \n");

            ArrayList<VV> ps = new ArrayList<VV>();

            while (r.next()) {
                // ------ start
                Object[] al = new Object[10];
                i = 0;

                // v.name
                al[1] = r.getString(++i);
                // v.fullName
                al[2] = r.getString(++i);
                // v.lastModified
                al[3] = r.getLong(++i);

                r.close();

                // ------ end
                ps.add(new VV(al));
            }
            r.close();

            return ps;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public List<VV> query(String cause, Object... params) {
        // TODO Auto-generated method stub
        return null;
    }
}

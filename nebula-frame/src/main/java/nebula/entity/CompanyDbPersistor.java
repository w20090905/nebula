package nebula.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nebula.persistor.Manageable;
import nebula.persistor.NebulaContext;
import nebula.persistor.Persistor;
import nebula.persistor.PersistorException;
import nebula.persistor.db.ConnectionProvider;

import com.google.inject.Inject;

public class CompanyDbPersistor implements Persistor<Company> {
    final NebulaContext context;
    final ConnectionProvider cp;

    @Inject
    protected CompanyDbPersistor(ConnectionProvider cp, NebulaContext context) {
        this.context = context;
        this.cp = cp;
    }

    @Override
    public Company get(String... keys) {
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

            InnerCompany v;
            v = new InnerCompany();
            i = 0;

            v.name = r.getString(++i);
            v.fullName = r.getString(++i);
            v.lastModified = r.getLong(++i);

            r.close();

            return v;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public Company merge(Company v) {
        return v;
    }

    @Override
    public void persist(Company v) {
        try {
            int i = 0;
            PreparedStatement p;

            p = cp.get().prepareStatement("insert into company(name,fullName,lastModified) values(?,?,?)");

            i = 0;
            p.setString(++i, v.name);
            p.setString(++i, v.fullName);
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
    public void remove(Company v) {
        try {

            PreparedStatement p;
            p = cp.get().prepareStatement("delete from company where company.name = ?");
            p.setString(1, v.name);

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
    public List<Company> list() {
        try {
            int i = 0;
            Statement p = cp.get().createStatement();
            ResultSet r = p.executeQuery("select name,fullname,lastModified "
                    + "from company p order by p.lastModified desc \n");

            ArrayList<Company> ps = new ArrayList<Company>();

            while (r.next()) {

                InnerCompany v = new InnerCompany();
                i = 0;
                v.name = r.getString(++i);
                v.fullName = r.getString(++i);
                v.lastModified = r.getLong(++i);
                ps.add(v);
            }
            r.close();

            return ps;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    public class InnerCompany extends Company implements Manageable {
        long lastModified;

        @Override
        public long getLastModified() {
            // TODO Auto-generated method stub
            return lastModified;
        }
    }

    @Override
    public List<Company> query(String cause, Object... params) {
        // TODO Auto-generated method stub
        return null;
    }
}

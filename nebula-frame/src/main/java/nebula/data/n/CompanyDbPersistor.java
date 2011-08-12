package nebula.data.n;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nebula.data.n.PHelper.OpRead;
import nebula.persistor.NebulaContext;
import nebula.persistor.Persistor;
import nebula.persistor.PersistorException;
import nebula.persistor.db.ConnectionProvider;

import com.google.inject.Inject;

public class CompanyDbPersistor implements Persistor<VV> {
    final NebulaContext context;
    final ConnectionProvider cp;

    final OpRead[] o;
    
    @Inject
    protected CompanyDbPersistor(ConnectionProvider cp, NebulaContext context) {
        this.context = context;
        this.cp = cp;

//        String name;
//        String fullName;
//        String since;
        int i=0;
        ArrayList<PHelper.OpRead> ol = new ArrayList<PHelper.OpRead>();
      
        ol.add(new PHelper.OpString(i++,i));//name
        ol.add(new PHelper.OpString(i++,i));//fullName
        ol.add(new PHelper.OpTimestamp(i++,i));//Timestamp
        o = ol.toArray(new OpRead[0]);
    }
    

    @Override
    public VV get(String... keys) {
        try {
            PreparedStatement p;
            ResultSet r;
            //int i;

            p = cp.get().prepareStatement("select name,fullName,lastModified from company where name = ?");
            p.setString(1, keys[0]);

            r = p.executeQuery();
            if (!r.next()) {
                throw new PersistorException();
            }
            
            Object[] al = new Object[10];            
            for(int i=0;i<o.length;i++){
                o[i].reader(context,al, r);              
            }

            r.close();
            
            return new VV(al);
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
            PreparedStatement p = cp.get().prepareStatement("insert into company(name,fullName,lastModified) values(?,?,?)");

            Object[] data = v.data;
         
            for(int i=0;i<o.length;i++){
                o[i].write(context,data, p);              
            }
            // .name .fullName context.getTime()

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
            Statement p = cp.get().createStatement();
            ResultSet r = p.executeQuery("select name,fullname,lastModified "
                    + "from company p order by p.lastModified desc \n");

            ArrayList<VV> ps = new ArrayList<VV>();

            while (r.next()) {
                // ------ start
                Object[] al = new Object[10];            
                for(int i=0;i<o.length;i++){
                    o[i].reader(context,al, r);              
                }
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

package nebula.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nebula.entity.PersonImp.WorkExperienceImp;
import nebula.entity.PersonDbPersistor.InnerPerson.InnerWorkExperience;
import nebula.persistor.Manageable;
import nebula.persistor.NebulaContext;
import nebula.persistor.PersistorException;
import nebula.persistor.db.ConnectionProvider;

import com.google.inject.Inject;

public class PersonDbPersistor implements PersonPersistor {
    final NebulaContext context;
    final ConnectionProvider cp;

    @Inject
    protected PersonDbPersistor(ConnectionProvider cp, NebulaContext context) {
        this.context = context;
        this.cp = cp;
    }

    @Override
    public PersonImp get(String... keys) {
        try {
            PreparedStatement p;
            ResultSet r;
            int i;

            p = cp.get().prepareStatement(
                    "select name,sex,height,birthday,company_name,lastModified " + "from person where name = ?");
            p.setString(1, keys[0]);

            r = p.executeQuery();
            if (!r.next()) {
                throw new PersistorException();
            }

            InnerPerson v;
            v = new InnerPerson();
            i = 0;

            v.name = r.getString(++i);
            v.sex = r.getString(++i);
            v.height = r.getLong(++i);
            v.birthday = r.getString(++i);
            v.company_name = r.getString(++i);
            v.lastModified = r.getLong(++i);

            // WorkExperience
            p = cp.get().prepareStatement(
                    "select person_name,index,from_,to_,company_name,lastModified"
                            + " from WorkExperience w where w.person_name = ?");
            p.setString(1, keys[0]);

            r = p.executeQuery();

            InnerWorkExperience w;
            while (r.next()) {
                w = v.new InnerWorkExperience();
                i = 0;
                ++i;
                w.index = r.getLong(++i);
                w.from = r.getString(++i);
                w.to = r.getString(++i);
                w.company_name = r.getString(++i);
                w.lastModified = r.getLong(++i);
                v.workExperiences.add(w);
            }

            return v;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public PersonImp merge(PersonImp v) {
        return v;
    }

    @Override
    public void persist(PersonImp v) {
        try {
            int i = 0;
            PreparedStatement p;

            p = cp.get().prepareStatement(
                    "insert into person(name,sex,height,birthday,company_name,lastModified) values(?,?,?,?,?,?)");

            i = 0;
            p.setString(++i, v.name);
            p.setString(++i, v.sex);
            p.setLong(++i, v.height);
            p.setString(++i, v.birthday);
            p.setString(++i, v.company_name);
            p.setLong(++i, context.getTime());

            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

            int updateCount = p.getUpdateCount();
            if (updateCount != 1) {
                throw new PersistorException();
            }

            if (v.workExperiences.size() > 0) {

                p = cp.get()
                        .prepareStatement(
                                "insert into WorkExperience(person_name,index,from_,to_,company_name,lastModified) values(?,?,?,?,?,?)");

                for (WorkExperienceImp w : v.workExperiences) {
                    i = 0;
                    p.setString(++i, v.name);
                    p.setLong(++i, w.index);
                    p.setString(++i, w.from);
                    p.setString(++i, w.to);
                    p.setString(++i, w.company_name);
                    p.setLong(++i, context.getTime());
                    p.addBatch();
                }

                int[] resultCount = p.executeBatch();
                if (resultCount[0] < 1) {// if is result then error
                    throw new PersistorException();
                }

                updateCount = p.getUpdateCount();
                if (updateCount != 1) {
                    throw new PersistorException();
                }
            }
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public void remove(PersonImp v) {
        try {

            PreparedStatement p;
            p = cp.get().prepareStatement("delete from WorkExperience w where w.person_name = ? ");
            p.setString(1, v.name);
            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

            p = cp.get().prepareStatement("delete from person where person.name = ?");
            p.setString(1, v.name);

            result = p.execute();
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
            p = cp.get().prepareStatement("delete from WorkExperience");
            boolean result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

            p = cp.get().prepareStatement("delete from person");

            result = p.execute();
            if (result) {// if is result then error
                throw new PersistorException();
            }

        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    @Override
    public List<PersonImp> list() {
        try {
            int i = 0;
            Statement p = cp.get().createStatement();
            ResultSet r = p.executeQuery("select name,sex,birthday,company_name,lastModified "
                    + "from person p order by p.lastModified desc \n");

            ArrayList<PersonImp> ps = new ArrayList<PersonImp>();

            while (r.next()) {

                InnerPerson v = new InnerPerson();
                i = 0;
                v.name = r.getString(++i);
                v.sex = r.getString(++i);
                v.birthday = r.getString(++i);
                v.lastModified = r.getLong(++i);
                ps.add(v);
            }
            r.close();

            r = p.executeQuery("select w.person_name,w.index,w.from_,w.to_,w.company_name,w.lastModified "
                    + "from person p join WorkExperience w on p.name=w.person_name order by p.lastModified desc");

            int j = 0;
            while (r.next()) {
                String name = r.getString(1);
                for (; j < ps.size() && !name.equals(ps.get(j).getName()); j++)
                    ;

                InnerPerson person = (InnerPerson) ps.get(j);

                InnerWorkExperience w = person.new InnerWorkExperience();
                i = 0;
                ++i;
                w.index = r.getLong(++i);
                w.from = r.getString(++i);
                w.to = r.getString(++i);
                w.company_name = r.getString(++i);
                w.lastModified = r.getLong(++i);
                person.workExperiences.add(w);
            }
            r.close();

            return ps;
        } catch (SQLException e) {
            throw new PersistorException(e);
        }
    }

    public class InnerPerson extends PersonImp implements Manageable {
        long lastModified;

        @Override
        public long getLastModified() {
            return lastModified;
        }

        class InnerWorkExperience extends WorkExperienceImp implements Manageable {
            long lastModified;

            @Override
            public long getLastModified() {
                return lastModified;
            }
        }
    }

    @Override
    public List<PersonImp> query(String cause, Object... params) {
        // TODO Auto-generated method stub
        return null;
    }
}

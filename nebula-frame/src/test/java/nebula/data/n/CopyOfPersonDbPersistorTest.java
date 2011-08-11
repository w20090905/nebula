package nebula.data.n;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;
import nebula.entity.Person;
import nebula.entity.PersonImp;
import nebula.entity.PersonDbPersistor;
import nebula.persistor.NebulaContext;
import nebula.persistor.db.ConnectionProvider;
import nebula.persistor.db.DbConfiguration;
import nebula.persistor.db.DerbyConfiguration;
import util.PrintObejct;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CopyOfPersonDbPersistorTest extends TestCase {
    Injector injector;
    PersonDbPersistor persistor;

    public CopyOfPersonDbPersistorTest() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                DerbyConfiguration cfg = new DerbyConfiguration("debula-test", "user", "password");

                this.bind(DbConfiguration.class).toInstance(cfg);
                this.bind(Connection.class).toProvider(ConnectionProvider.class);
                this.bind(PersonDbPersistor.class);
                this.bind(NebulaContext.class);
            }
        });
        persistor = injector.getInstance(PersonDbPersistor.class);
        Connection conn = injector.getInstance(Connection.class);

        boolean done = false;
        // try {
        // conn.createStatement().execute("drop table person");
        // done = true;
        // } catch (Exception e) {
        // }

        try {
            conn.createStatement().execute("select * from person");
            conn.createStatement().execute("select * from WorkExperience");
            done = true;
        } catch (Exception e) {
        }

        if (!done) {
            try {
                conn.createStatement().execute("drop table person");
            } catch (Exception e) {
            }
            try {
                conn.createStatement().execute("drop table WorkExperience");
            } catch (Exception e) {
            }
            try {
                conn.createStatement().execute(
                        "create table person (" + " name            varchar(40) NOT NULL"
                                + ",sex             varchar(40) " 
                                + ",birthday        varchar(40)"
                                + ",height          BIGINT" 
                                + ",company_name    varchar(40)"
                                + ",lastModified    BIGINT" 
                                + ",PRIMARY KEY(name) " + ")");
                conn.createStatement().execute(
                        "create table WorkExperience (" + " person_name     varchar(40)  NOT NULL"
                                + ",index           BIGINT       NOT NULL" + ",from_           varchar(40)"
                                + ",to_             varchar(40) " + ",company_name    varchar(40)"
                                + ",lastModified    BIGINT" + ",PRIMARY KEY(person_name,index) " + ")");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setUp() throws Exception {

    }

    public void testNop() {

    }

    public void testInsert() {
        PersonImp person = new PersonImp();

        person.setName("wangshilian");
        PersonImp.WorkExperienceImp w = person.new WorkExperienceImp();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperienceImp();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        persistor.persist(person);

         person = persistor.get("wangshilian");
         assertNotNull(person);
    }

    public void testGetPerson() {
        Person person = persistor.get("wangshilian");
        assertNotNull(person);
        // PrintObejct.print(person.getClass(), person);
    }

    public void testListPerson() {
        PersonImp person = new PersonImp();

        person.setName("houyihong");
        PersonImp.WorkExperienceImp w = person.new WorkExperienceImp();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperienceImp();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        persistor.persist(person);
        person = new PersonImp();

        person.setName("wangsl");
        w = person.new WorkExperienceImp();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperienceImp();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        persistor.persist(person);

        List<PersonImp> ps = persistor.list();

        assertEquals(3, ps.size());

        assertNotNull(ps.get(0));
        PrintObejct.print(ps.getClass(), ps);
    }

    public void testRemove() {
        PersonImp person = persistor.get("wangshilian");
        persistor.remove(person);
    }

    public void testRemoveAll() {
        persistor.removeAll();
    }

    @Override
    public void tearDown() {
    }
}

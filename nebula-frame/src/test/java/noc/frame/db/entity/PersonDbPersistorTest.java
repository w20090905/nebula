package noc.frame.db.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;
import nebula.entity.Person;
import nebula.entity.PersonDbPersistor;
import nebula.persistor.NebulaContext;
import nebula.persistor.db.ConnectionProvider;
import nebula.persistor.db.DbConfiguration;
import nebula.persistor.db.DerbyConfiguration;
import util.PrintObejct;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PersonDbPersistorTest extends TestCase {
    protected final static String driverClass = "org.apache.derby.jdbc.EmbeddedDriver";
    protected final static String url = "jdbc:derby:db/kao_testrun;create = true";
    protected final static String userName = "user";
    protected final static String userPassword = "password";
    Injector injector;
    PersonDbPersistor p;

    public PersonDbPersistorTest() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                DerbyConfiguration cfg = new DerbyConfiguration(driverClass, url, userName, userPassword);

                this.bind(DbConfiguration.class).toInstance(cfg);
                this.bind(ConnectionProvider.class);
                this.bind(Connection.class).toProvider(ConnectionProvider.class);
                this.bind(PersonDbPersistor.class);
                this.bind(NebulaContext.class);
            }
        });
        p = injector.getInstance(PersonDbPersistor.class);
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
                                + ",sex             varchar(40) " + ",birthday        varchar(40)"
                                + ",height          BIGINT" + ",company_name    varchar(40)"
                                + ",lastModified    BIGINT" + ",PRIMARY KEY(name) " + ")");
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
        Person person = new Person();

        person.setName("wangshilian");
        Person.WorkExperience w = person.new WorkExperience();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperience();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        p.persist(person);

         person = p.get("wangshilian");
         assertNotNull(person);
    }

    public void testGetPerson() {
        Person person = p.get("wangshilian");
        assertNotNull(person);
        // PrintObejct.print(person.getClass(), person);
    }

    public void testListPerson() {
        Person person = new Person();

        person.setName("houyihong");
        Person.WorkExperience w = person.new WorkExperience();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperience();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        p.persist(person);
        person = new Person();

        person.setName("wangsl");
        w = person.new WorkExperience();
        w.setIndex(10L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);
        w = person.new WorkExperience();
        w.setIndex(20L);
        w.setFrom("from_value");
        w.setTo("to_value");
        person.getWorkExperiences().add(w);

        p.persist(person);

        List<Person> ps = p.list();

        assertEquals(3, ps.size());

        assertNotNull(ps.get(0));
        PrintObejct.print(ps.getClass(), ps);
    }

    public void testRemove() {
        Person person = p.get("wangshilian");
        p.remove(person);
    }

    public void testRemoveAll() {
        p.removeAll();
    }

    @Override
    public void tearDown() {
    }
}

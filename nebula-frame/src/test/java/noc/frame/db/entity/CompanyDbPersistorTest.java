package noc.frame.db.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;
import nebula.entity.Company;
import nebula.entity.CompanyDbPersistor;
import nebula.persistor.NebulaContext;
import nebula.persistor.db.ConnectionProvider;
import nebula.persistor.db.DbConfiguration;
import nebula.persistor.db.DerbyConfiguration;
import util.PrintObejct;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CompanyDbPersistorTest extends TestCase {
    protected final static String driverClass = "org.apache.derby.jdbc.EmbeddedDriver";
    protected final static String url = "jdbc:derby:db/kao_testrun;create = true";
    protected final static String userName = "user";
    protected final static String userPassword = "password";
    Injector injector;
    CompanyDbPersistor p;

    public CompanyDbPersistorTest() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                DerbyConfiguration cfg = new DerbyConfiguration(driverClass, url, userName, userPassword);

                this.bind(DbConfiguration.class).toInstance(cfg);
                this.bind(ConnectionProvider.class);
                this.bind(Connection.class).toProvider(ConnectionProvider.class);
                this.bind(CompanyDbPersistor.class);
                this.bind(NebulaContext.class);
            }
        });
        p = injector.getInstance(CompanyDbPersistor.class);
        Connection conn = injector.getInstance(Connection.class);

        boolean done = false;
        // try {
        // conn.createStatement().execute("drop table company");
        // done = true;
        // } catch (Exception e) {
        // }

        try {
            conn.createStatement().execute("select * from company");
            done = true;
        } catch (Exception e) {
        }

        if (!done) {
            try {
                conn.createStatement().execute("drop table company");
            } catch (Exception e) {
            }
            try {
                conn.createStatement().execute(
                        "create table company (" 
                        + " name            varchar(40) NOT NULL"                                
                        + ",fullname        varchar(40) " 
                        + ",lastModified    BIGINT"                                
                        + ",PRIMARY KEY(name) " + ")");
            } catch (SQLException e) {
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
        Company company = new Company();

        company.setName("jixian");

        p.persist(company);

        company = p.get("jixian");
        assertNotNull(company);
    }

    public void testGetCompany() {
        Company company = p.get("jixian");
        assertNotNull(company);
        // PrintObejct.print(company.getClass(), company);
    }

    public void testListCompany() {
        Company company = new Company();
        company.setName("linzhi");

        p.persist(company);
        company = new Company();

        company.setName("linweishen");
        p.persist(company);

        List<Company> ps = p.list();

        assertEquals(3, ps.size());

        assertNotNull(ps.get(0));
        PrintObejct.print(ps.getClass(), ps);
        assertEquals("linweishen", ps.get(0).getName());
        assertEquals("linzhi", ps.get(1).getName());
        assertEquals("jixian", ps.get(2).getName());
    }

    public void testRemove() {
        Company company = p.get("jixian");
        p.remove(company);
    }

    public void testRemoveAll() {
        p.removeAll();
    }

    @Override
    public void tearDown() {
    }
}

package nebula.persistor.db;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DerbyConfiguration extends DbConfiguration {
    public DerbyConfiguration(String driverClass, String url, String userName, String password) {
        super(driverClass, url, userName, password);
    }

    public DerbyConfiguration(String dbName, String userName, String password) {
        super("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:db/" + dbName + ";create = true", userName, password);
    }

    @Override
    public void shutdown() {
        super.shutdown();
        try { // perform a clean shutdown
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
        }
    }

}

package nebula.persistor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfiguration {
    protected final String driverClass;
    protected final String url;
    protected final String userName;
    protected final String userPassword;
    protected Connection conn = null;

    public DbConfiguration(String driverClass, String url, String userName, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.userPassword = password;

    }

    
    private void openConnection(){
        try {
            Class.forName(driverClass).newInstance();
            conn = DriverManager.getConnection(this.url, this.userName, this.userPassword);
            //conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // public static DbConfiguration getEngine(String driverClass, String url,
    // String userName, String password) {
    // String dbms = url.split(":")[1].toUpperCase();
    // DbConfiguration dbEngine = null;
    // if ("DERBY".equals(dbms)) {
    // dbEngine = new DerbyConfiguration(driverClass, url, userName, password);
    // } else if ("ORACLE".equals(dbms)) {
    // dbEngine = new OracleConfiguration(driverClass, url, userName, password);
    // } else {
    // throw new UnsupportedOperationException();
    // }
    // dbEngine.init();
    // return dbEngine;
    // }

    public void shutdown() {
        try {
            if (conn != null) {
                conn.commit();
                conn.close();
            }
        } catch (SQLException e) {
        }
    }

    public Connection getConnection() {
        if(this.conn==null){
            openConnection();
        }
        return this.conn;
    }

    @Override
    protected void finalize() throws Throwable {
        this.shutdown();
    }

}

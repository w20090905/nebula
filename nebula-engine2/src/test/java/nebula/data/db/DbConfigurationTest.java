package nebula.data.db;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

public class DbConfigurationTest extends TestCase {

	String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
	String url = "jdbc:derby:memory:eh;create = true";
	String username = "user";
	String password = "password";

	static class SubDbConfiguration extends DbConfiguration {
		public SubDbConfiguration(String driverClass, String dbUrl, String userName, String password) {
			super(driverClass, dbUrl, userName, password);
		}
	}

	SubDbConfiguration c;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testFinalize() {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();
		c.finalize();
	}

	public final void testDbConfiguration() {
		c = new SubDbConfiguration(driverclass, url, username, password);
	}

	public final void testInitDefaultMapping() {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.initDefaultMapping();
	}

	public final void testOpenConnection() {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();
		c.finalize();
	}

	public final void testGetConnection() throws SQLException {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();

		Connection conn = c.getConnection();

		conn.createStatement().execute("create table usr(id INT,name varchar(1000))");

		c.finalize();
	}

	public final void testRegisterColumnType() {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.initDefaultMapping();
	}

}

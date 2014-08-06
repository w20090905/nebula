package nebula.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nebula.data.Order;
import nebula.data.OrderDetail;

import junit.framework.TestCase;

public class DAOTest extends TestCase {

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
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();

		Connection conn = c.getConnection();

		conn.createStatement().execute("create table NOrder(id INT)");
		conn.createStatement().execute("create table NOrder_Detail(order_id INT,seq INT,price INT,count INT,amount INT)");

		c.finalize();
	}

	protected void tearDown() throws Exception {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();

		Connection conn = c.getConnection();

		conn.createStatement().execute("drop table NOrder_Detail");
		conn.createStatement().execute("drop table NOrder");

		c.finalize();
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

	public final void testInsertIntoOrder() throws SQLException {
		c = new SubDbConfiguration(driverclass, url, username, password);
		c.openConnection();

		Connection conn = c.getConnection();

		conn.createStatement().execute("insert into NOrder values(1)");
		conn.createStatement().execute("insert into NOrder values(2)");
		conn.createStatement().execute("insert into NOrder values(3)");
		conn.createStatement().execute("insert into NOrder values(4)");
		conn.createStatement().execute("insert into NOrder values(5)");
		conn.createStatement().execute("insert into NOrder values(6)");
		conn.createStatement().execute("insert into NOrder_Detail values(1,1,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(1,2,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(1,3,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(1,4,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(3,1,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(4,1,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(5,1,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(6,1,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(4,2,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(5,2,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(4,3,100,10,1000)");
		conn.createStatement().execute("insert into NOrder_Detail values(1,5,100,10,1000)");

		ResultSet res = conn.createStatement().executeQuery("select * from NOrder order by id");

		List<Order> os = new ArrayList<Order>();

		while (res.next()) {
			Order order = new Order();
			order.setId(res.getInt(1));
			os.add(order);
		}

		res = conn.createStatement().executeQuery("select * from NOrder_Detail order by order_id,seq");


		if (res.next()) {
			int id = res.getInt(1);
			
			for (Order o:os) {
				
				while(id==o.getId()){
					OrderDetail od = new OrderDetail();
					od.setSeq(res.getInt(2));
					od.setPrice(res.getInt(3));
					od.setCount(res.getInt(4));
					od.setAmount(res.getInt(5));
					o.getDetails().add(od);
					
					if(!res.next())break;
					id = res.getInt(1);
				}
			}
		}

		System.out.println(os);
	}
}

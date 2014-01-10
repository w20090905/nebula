 package test.db.h2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDerbyMemory {
	public static void main(String[] a) throws Exception {
		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:memory:eh;create = true";
		String username = "user";
		String password = "password";
		
		Class.forName(driverclass);
		Connection conn = DriverManager.getConnection(url, username,password);
		Statement s;
		conn.setAutoCommit(false);
		s = conn.createStatement();
		try {
			s.execute("drop table test");
			conn.commit();
		} catch (Exception e) {
		}
		
		int MAX = 100;
		{ // warm
			// setUp
			conn.setAutoCommit(true);
			s.execute("create table test(id VARCHAR(255),name  VARCHAR(255))");
			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();

			conn.setAutoCommit(false);
			for (int i = 0; i < 1; i++) {
				s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\')");
			}			
			conn.commit();
			
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;


			conn.setAutoCommit(true);
			s = conn.createStatement();
			try {
				s.execute("drop table test");
				conn.commit();
			} catch (Exception e) {
			}
		}
		{
			String desc = "DerbyMemory 手工提交 无Key";
			// setUp
			conn.setAutoCommit(true);
			s.execute("create table test(id VARCHAR(255),name  VARCHAR(255))");
			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();

			conn.setAutoCommit(false);
			for (int i = 0; i < MAX; i++) {
				s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\')");
			}			
			conn.commit();
			
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);

			conn.setAutoCommit(true);
			s = conn.createStatement();
			try {
				s.execute("drop table test");
				conn.commit();
			} catch (Exception e) {
			}
		}

		{
			String desc = "DerbyMemory 自动提交 无Key";
			// setUp
			conn.setAutoCommit(true);
			s.execute("create table test(id VARCHAR(255),name  VARCHAR(255))");
			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();

			conn.setAutoCommit(true);
			for (int i = 0; i < MAX; i++) {
				s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\')");
			}
			
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);

			conn.setAutoCommit(true);
			s = conn.createStatement();
			try {
				s.execute("drop table test");
				conn.commit();
			} catch (Exception e) {
			}
		}
		
		
//		s= conn.createStatement();
//		ResultSet r = s.executeQuery("select * from test;");
//		int cnt = 0;
//		while (r.next()) {
//			//System.out.println(r.getString("id"));
//			cnt ++;
//		}
//		System.out.println(cnt);
//
//		conn.setAutoCommit(false);
//		s = conn.createStatement();
//		try {
//			s.execute("drop table test");
//			conn.commit();
//		} catch (Exception e) {
//		}
		
		{
			String desc = "DerbyMemory 批量执行 无Key";
			// setUp
			conn.setAutoCommit(true);
			s.execute("create table test(id VARCHAR(255),name  VARCHAR(255))");
			conn.setAutoCommit(false);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();

			PreparedStatement p = conn.prepareStatement("insert into test(id,name) values(?,?)");
			for (int i = 0; i < MAX; i++) {
				p.setString(1, "wanshilian" + i + "");
				p.setString(2, "test");
				p.addBatch();
				
			}
			p.executeBatch();
			conn.commit();
			
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
			
			s= conn.createStatement();
			ResultSet r = s.executeQuery("select * from test");
			int cnt = 0;
			while (r.next()) {
				//System.out.println(r.getString("id"));
				cnt ++;
			}
			
			conn.setAutoCommit(true);
			s = conn.createStatement();
			try {
				s.execute("drop table test");
				conn.commit();
			} catch (Exception e) {
			}
			
			
		}
		


		conn.close();
	}
}

package test.db.h2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestH2 {
	public static void main(String[] a) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:test", "sa", "");
		Statement s;
		long start=0,end=0;
		
		conn.setAutoCommit(false);
		s = conn.createStatement();
		try {
			s.execute("drop table test");
			conn.commit();
		} catch (Exception e) {
		}
		
		s.execute("create table test(id VARCHAR(255),name  VARCHAR(255));");
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\');");
		}
		conn.commit();
		end = System.currentTimeMillis();
		System.out.println("manul commit " + (end - start));
		
		conn.setAutoCommit(true);
		s = conn.createStatement();
		try {
			s.execute("drop table test");
			conn.commit();
		} catch (Exception e) {
		}
		
		s.execute("create table test(id VARCHAR(255),name  VARCHAR(255));");
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\');");
		}
		end = System.currentTimeMillis();
		System.out.println("auto commit " + (end - start));
		
		conn.setAutoCommit(false);
		s = conn.createStatement();
		try {
			s.execute("drop table test");
			conn.commit();
		} catch (Exception e) {
		}
		
		s.execute("create table test(id VARCHAR(255),name  VARCHAR(255));");
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			s.execute("insert into test(id,name) values(\'wanshilian" + i + "\',\'test\');");
		}
		conn.commit();
		end = System.currentTimeMillis();
		System.out.println("manul commit " + (end - start));
		
		s= conn.createStatement();
		ResultSet r = s.executeQuery("select * from test;");
		int cnt = 0;
		while (r.next()) {
			//System.out.println(r.getString("id"));
			cnt ++;
		}
		System.out.println(cnt);

		conn.setAutoCommit(false);
		s = conn.createStatement();
		try {
			s.execute("drop table test");
			conn.commit();
		} catch (Exception e) {
		}
		
		s.execute("create table test(id VARCHAR(255),name  VARCHAR(255));");

		start = System.currentTimeMillis();
		PreparedStatement p = conn.prepareStatement("insert into test(id,name) values(?,?);");
		for (int i = 0; i < 10000; i++) {
			p.setString(1, "wanshilian" + i + "");
			p.setString(2, "test");
			p.addBatch();
		}
		p.executeBatch();
		conn.commit();
		end = System.currentTimeMillis();
		System.out.println("PreparedStatement " + (end - start));
		
		s= conn.createStatement();
		r = s.executeQuery("select * from test;");
		cnt = 0;
		while (r.next()) {
			//System.out.println(r.getString("id"));
			cnt ++;
		}
		System.out.println(cnt);

		conn.close();
	}
}

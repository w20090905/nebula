package nebula.data.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nebula.data.Order;
import nebula.data.OrderDetail;

public class OrderDAO implements DAO<Order> {
	Connection conn;

	public List<Order> query() throws SQLException {
		PreparedStatement psmt;
		ResultSet res;

		List<Order> os = new ArrayList<Order>();
		
		psmt = conn.prepareStatement("select * from NOrder order by id");
		res = psmt.executeQuery();

		while (res.next()) {
			Order order = new Order();
			order.setId(res.getInt(1));
			os.add(order);
		}

		psmt = conn.prepareStatement("select * from NOrder_Detail order by order_id,seq");
		res = psmt.executeQuery();
		
		if (res.next()) {
			int id = res.getInt(1);

			outerfor: for (Order o : os) {

				while (id == o.getId()) {
					OrderDetail od = new OrderDetail();
					od.setSeq(res.getInt(2));
					od.setPrice(res.getInt(3));
					od.setCount(res.getInt(4));
					od.setAmount(res.getInt(5));
					o.getDetails().add(od);

					if (!res.next()) break outerfor;
					id = res.getInt(1);
				}
			}
		}

		return os;

	}

	public Order get(int id) throws SQLException {
		PreparedStatement psmt;
		ResultSet res;

		Order o = new Order();

		psmt = conn.prepareStatement("select * from NOrder  where id=? order by id");
		psmt.setInt(1, id);
		res = psmt.executeQuery();

		if (res.next()) {
			System.out.println("header start");
			o.setId(res.getInt(1));
			System.out.println("header end");
		}

		psmt = conn.prepareStatement("select * from NOrder_Detail  where order_id=? order by order_id,seq");
		psmt.setInt(1, id);
		res = psmt.executeQuery();

		if (res.next()) {
			while (res.next()) {
				OrderDetail od = new OrderDetail();
				System.out.println("detail start");
				od.setSeq(res.getInt(2));
				od.setPrice(res.getInt(3));
				od.setCount(res.getInt(4));
				od.setAmount(res.getInt(5));
				System.out.println("detail end");
				o.getDetails().add(od);
			}
		}

		return o;
	}

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

}

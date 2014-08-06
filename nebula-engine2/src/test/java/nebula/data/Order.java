package nebula.data;

import java.util.ArrayList;
import java.util.List;

public class Order {
	int id;
	List<OrderDetail> details = new ArrayList<OrderDetail>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", details=" + details + "]\n";
	}
	
}

package nebula.data;


public class OrderDetail {
	int seq;
	int price;
	int count;
	int amount;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "OrderDetail [seq=" + seq + ", price=" + price + ", count=" + count + ", amount=" + amount + "]";
	}
}

package nebula.data2;

public interface Transaction {
	void commit();
	void rollback();
}

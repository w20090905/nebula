package nebula.data;


public interface Callback<V> {
	void exec(DataPersister<V> p);
}

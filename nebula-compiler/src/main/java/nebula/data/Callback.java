package nebula.data;


public interface Callback<V extends Timable> {
	void exec(DataPersister<V> p);
}

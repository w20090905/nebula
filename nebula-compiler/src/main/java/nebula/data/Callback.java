package nebula.data;


public interface Callback<V extends Identifiable> {
	void exec(DataPersister<V> p);
}

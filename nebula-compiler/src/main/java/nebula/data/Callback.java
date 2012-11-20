package nebula.data;

public interface Callback<V extends HasID> {
	void exec(DataPersister<V> p);
}

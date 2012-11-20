package nebula.data;

public interface DataHolder<T> {
	void set(T newData,T oldData);
	T get();
	void addListener(DataListener<T> listener);
}

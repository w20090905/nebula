package nebula.data;

public interface Holder<T> {
	T get();
	void update(T oldData,T newData);
	void addListener(HolderListener<T> listener);
}

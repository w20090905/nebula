package nebula.data;

public interface Persistence<V extends HasID>{
	Store<V> define(Class<V> clz,String name);
	void add(V v);
	void remove(V v);
	void flush();
	void clearChanges();
	void transaction(Callback<V> callback);
	void markChanged(V v);
}

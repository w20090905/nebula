package nebula.data;


public interface DataPersister<V extends HasID>{
	void load();
	void unload();
	
	DataStore<V> define(Class<V> clz,String name);
	DataStore<V> reload(Class<V> clz,String name);
	void add(V v);
	void remove(V v);
	void flush();
	void clearChanges();
	void transaction(Callback<V> callback);
	void markChanged(V v);
}

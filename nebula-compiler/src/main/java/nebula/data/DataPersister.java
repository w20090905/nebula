package nebula.data;


public interface DataPersister<V extends HasID>{
	void load();
	void unload();
	
	DataHolder<DataStore<V>> define(Class<V> clz,String name);
	void reload(Class<V> clz,String name);
	
	void define(Class<V> clz,String name,DataListener<DataStore<V>> listener);
	
	void add(V v);
	void remove(V v);
	void flush();
	void clearChanges();
	void transaction(Callback<V> callback);
	void markChanged(V v);
}

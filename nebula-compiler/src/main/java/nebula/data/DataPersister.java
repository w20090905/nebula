package nebula.data;


public interface DataPersister<V extends Timable> {
	void load();

	void unload();

	// 取得指定类型对应的store
	<I> Broker<DataStore<V>> define(Class<I> clzIndex, Class<V> clz, String name);

	// 在指定类型变更后，重新装载指定类型
	<I> Broker<DataStore<V>> reload(Class<I> clzIndex, Class<V> clz, String name);
//
//	// 定义DataStore监听器
//	<I> void define(Class<I> clzIndex, Class<V> clz, String name, HolderListener<DataStore<V>> listener);

	void add(V v);

	void remove(V v);

	void flush();

	void clearChanges();

	void transaction(Callback<V> callback);

	void markChanged(V v);
}

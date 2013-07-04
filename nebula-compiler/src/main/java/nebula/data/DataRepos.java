package nebula.data;

public interface DataRepos {
	// 取得指定类型对应的store
	<V extends Timable, I> Broker<DataStore<V>> define(Class<I> clzIndex, Class<V> clz, String name);
}

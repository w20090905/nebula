package nebula.data2;

public interface DataRepos {
	<T> DataStore<T> get(String name);
	<T> DataStore<T> get(Class<T> clz, String name);
}

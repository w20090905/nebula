package nebula.data2;

public interface Datatstore {
	<V> Kind<V> get(String kindName, Class<V> clz);

	<T> T put(KindEx<T> kind, T data);
}

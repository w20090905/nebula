package nebula.data;

import java.util.List;

public interface Store<V extends HasID> {
	V createNew();
	V load(String key);
	List<V> findBy(String key);

	void add(V v);
	void remove(V v);
	void flush();
	List<V> all();
}

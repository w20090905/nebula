package nebula.data;

import java.util.List;

public interface Store<V extends HasID> extends HasID {
	V createNew();

	V load(String key);

	List<V> findBy(String key);

	void markChanged(V v);

	void apply(V newV);

	void add(V v);

	void remove(V v);

	void flush();

	List<V> all();
}

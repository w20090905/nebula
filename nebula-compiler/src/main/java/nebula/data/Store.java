package nebula.data;

import java.util.List;

import nebula.Filter;
import nebula.lang.Type;

public interface Store<V extends HasID> extends HasID {
	V createNew();

	V load(String key);

	List<V> query(Filter<V> filter);
	
	Type getType();

	void markChanged(V v);

	void apply(V newV);

	void add(V v);

	void remove(V v);

	void flush();

	List<V> all();
}

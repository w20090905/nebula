package nebula.data;

import java.util.List;

import nebula.Filter;
import nebula.lang.Type;

public interface DataStore<V extends HasID> extends HasID {
	void load();
	void unload();
	
	V createNew();	
	V get(String key);
	
	List<V> query(Filter<V> filter);	
	Type getType();
	
	void markChanged(V v);
	void apply(V newV);
	void add(V v);
	void remove(V v);
	void flush();
	
	void clear();

	List<V> all();
}

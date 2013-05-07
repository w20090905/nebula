package nebula.data;

import java.util.List;

import nebula.lang.Type;

public interface DataStore<V extends Identifiable> extends Identifiable {
	void load();
	void unload();
	
	V get(String key);
	
	List<V> query(DataFilter<V> filter);	
	Type getType();
	
	void markChanged(V v);
	void apply(V newV);
	void add(V v);
	void remove(V v);
	void flush();
	
	void clear();

	List<V> all();
}

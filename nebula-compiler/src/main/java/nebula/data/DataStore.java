package nebula.data;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ListMultimap;

public interface DataStore<V> {
	void load();
	void unload();
	
	V get(String key);
	
	List<V> query(Predicate<V> filter);	

	void addIndex(Index<V, String> key);
	void removeIndex(Index<V, String> key);
	ListMultimap<String, V> getByIndex(Index<V, String> key);
	
	void markChanged(V v);
	void apply(V newV);
	void add(V v);
	void remove(V v);
	void flush();
	
	void clear();

	List<V> all();
}

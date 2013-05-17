package nebula.data;

import java.util.List;
import java.util.Map;

public interface DataStore<V extends Timable> extends Timable {
	// Function<V, Object> getIdMaker();

	void load();

	void unload();

	V get(Object key);

	Classificator<String, V> getClassificator(String name);

	Map<String, Classificator<String, Entity>> getClassificatores();

	// public <K> Classificator<K, V> classify(Function<V, K> indexerFunction);
	//
	// public <K> Classificator<K, V> liveClassify(Function<V, K>
	// indexerFunction);
	//
	// public List<V> filter(Predicate<V> filterFunction);
	//
	// public ClassifiableFilter<V> liveFilter(Predicate<V> filterFunction);

	void markChanged(V v);

	void apply(V newV);

	void add(V v);

	void remove(V v);

	void flush();

	void clear();

	List<V> all();
}

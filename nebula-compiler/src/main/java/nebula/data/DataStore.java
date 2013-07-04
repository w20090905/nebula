package nebula.data;

import java.util.List;
import java.util.Map;

public interface DataStore<V extends Timable> extends Timable {
	V get(Object key);

	Classificator<String, V> getClassificator(String name);

	Map<String, Classificator<String, Entity>> getClassificatores();

	void add(V v);

	void remove(V v);

	void flush();

	void clearChanges();

	List<V> listAll();
}

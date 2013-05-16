package nebula.data;

import java.util.List;
import java.util.Set;

public interface Classificator<K, V> extends DataListener<V>{
	Set<K> getClassifications();

	List<V> getData(K classification);
}

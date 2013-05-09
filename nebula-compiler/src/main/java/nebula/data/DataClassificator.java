package nebula.data;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

class DataClassificator<K, V> implements DataListener<V>, Classificator<K, V> {

	final ListMultimap<K, V> values = ArrayListMultimap.create();
	final Function<V, K> indexFunction;

	DataClassificator(Function<V, K> indexFunction) {
		this.indexFunction = indexFunction;
	}

	DataClassificator(Iterable<V> values, Function<V, K> indexFunction) {
		this.indexFunction = indexFunction;
		for (V value : values) {
			Preconditions.checkNotNull(value, values);
			this.values.put(indexFunction.apply(value), value);
		}
	}

	public void add(V v) {
		K classification = indexFunction.apply(v);
		if (classification != null) this.values.get(classification).add(v);
	}

	public void update(V oldData, V newData) {
		if (Objects.equal(oldData, newData)) {
			return;
		}
		K oldClassification = indexFunction.apply(oldData);
		K newClassification = indexFunction.apply(newData);

		if (oldClassification != null) this.values.get(oldClassification).remove(oldData);
		if (newClassification != null) this.values.get(newClassification).add(newData);
	}

	public void remove(V v) {
		K classification = indexFunction.apply(v);
		if (classification != null) this.values.get(classification).remove(v);
	}

	public Set<K> getClassifications() {
		return this.values.keySet();
	}

	public List<V> getData(K classification) {
		return this.values.get(classification);
	}
}

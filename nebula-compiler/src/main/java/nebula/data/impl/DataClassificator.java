package nebula.data.impl;

import java.util.List;
import java.util.Set;

import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.Timable;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class DataClassificator<K, V extends Timable> implements DataListener<V>, Classificator<K, V> {

	final ListMultimap<K, V> values = ArrayListMultimap.create();
	final Function<V, K> indexFunction;

	public DataClassificator(Function<V, K> indexFunction) {
		this.indexFunction = indexFunction;
	}

	public  DataClassificator(Iterable<V> values, Function<V, K> indexFunction) {
		this.indexFunction = indexFunction;
		for (V value : values) {
			Preconditions.checkNotNull(value, values);
			this.values.put(indexFunction.apply(value), value);
		}
	}

	public void onAdd(V v) {
		K classification = indexFunction.apply(v);
		if (classification != null) this.values.get(classification).add(v);
	}

	public void onUpdate(V oldData, V newData) {
		if (Objects.equal(oldData, newData)) {
			return;
		}
		K oldClassification = indexFunction.apply(oldData);
		K newClassification = indexFunction.apply(newData);

		if (oldClassification != null) this.values.get(oldClassification).remove(oldData);
		if (newClassification != null) this.values.get(newClassification).add(newData);
	}

	public void onRemove(V v) {
		K classification = indexFunction.apply(v);
		if (classification != null) this.values.get(classification).remove(v);
	}

	public Set<K> getClassifications() {
		return this.values.keySet();
	}

	public List<V> getData(K classification) {
		return this.values.get(classification);
	}

	@Override
	public List<V> getData(K classification, K classification2) {
		// TODO Not realized getData(K classification, K classification2) 
		return null;
	}
}

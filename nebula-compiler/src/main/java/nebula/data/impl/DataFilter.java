package nebula.data.impl;

import java.util.List;

import nebula.data.ClassifiableFilter;
import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.Filter;
import nebula.data.Timable;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

@Deprecated
class DataFilter<V extends Timable> implements ClassifiableFilter<V>, Filter<V> {
	final List<V> values = Lists.newArrayList();
	final List<DataListener<V>> listeneres;
	final Predicate<V> filterFunction;

	public DataFilter(Predicate<V> filterFunction) {
		this.filterFunction = filterFunction;
		this.listeneres = Lists.newArrayList();
	}

	DataFilter(Iterable<V> values, Predicate<V> filterFunction) {
		this(filterFunction);
		for (V value : values) {
			Preconditions.checkNotNull(value, values);
			if (filterFunction.apply(value)) this.values.add(value);
		}
	}

	@Override
	public List<V> get() {
		return values;
	}

	@Override
	public <K> Classificator<K, V> classify(Function<V, K> indexFunction) {
		DataClassificator<K, V> classificator = new DataClassificator<K, V>(values, indexFunction);
		listeneres.add(classificator);
		return classificator;
	}

	@Override
	public void onAdd(V v) {
		if (filterFunction.apply(v)) this.values.add(v);
		for (DataListener<V> listener : this.listeneres) {
			listener.onAdd(v);
		}
	}

	@Override
	public void onUpdate(V oldData, V newData) {
		boolean hasOld = filterFunction.apply(oldData);
		boolean hasNew = filterFunction.apply(newData);
		if (hasOld && hasNew) {
			this.values.remove(oldData);
			this.values.add(newData);
			for (DataListener<V> listener : this.listeneres) {
				listener.onUpdate(oldData, newData);
			}
		} else if (hasNew) {
			this.values.add(newData);
			for (DataListener<V> listener : this.listeneres) {
				listener.onAdd(newData);
			}
		} else if (hasOld) {
			this.values.remove(oldData);
			for (DataListener<V> listener : this.listeneres) {
				listener.onRemove(oldData);
			}
		}
	}

	@Override
	public void onRemove(V v) {
		if (filterFunction.apply(v)) {
			this.values.remove(v);
			for (DataListener<V> listener : this.listeneres) {
				listener.onRemove(v);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return obj != null ? filterFunction.equals(((DataFilter<V>) obj).filterFunction) : false;
	}

	@Override
	public int hashCode() {
		return filterFunction.hashCode();
	}

}

package nebula.data;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

class DataFilter<V> implements ClassifiableFilter<V>, Filter<V>, DataListener<V> {
	final List<V> values = Lists.newArrayList();
	final List<DataListener<V>> listeneres;
	final Predicate<V> filterFunction;

	DataFilter(Predicate<V> filterFunction) {
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
	public void add(V v) {
		if (filterFunction.apply(v)) this.values.add(v);
		for (DataListener<V> listener : this.listeneres) {
			listener.add(v);
		}
	}

	@Override
	public void update(V oldData, V newData) {
		boolean hasOld = filterFunction.apply(oldData);
		boolean hasNew = filterFunction.apply(newData);
		if (hasOld && hasNew) {
			this.values.remove(oldData);
			this.values.add(newData);
			for (DataListener<V> listener : this.listeneres) {
				listener.update(oldData, newData);
			}
		} else if (hasNew) {
			this.values.add(newData);
			for (DataListener<V> listener : this.listeneres) {
				listener.add(newData);
			}
		} else if (hasOld) {
			this.values.remove(oldData);
			for (DataListener<V> listener : this.listeneres) {
				listener.remove(oldData);
			}
		}
	}

	@Override
	public void remove(V v) {
		if (filterFunction.apply(v)) {
			this.values.remove(v);
			for (DataListener<V> listener : this.listeneres) {
				listener.remove(v);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return filterFunction.equals(((DataFilter<V>) obj).filterFunction);
	}

	@Override
	public int hashCode() {
		return filterFunction.hashCode();
	}

}

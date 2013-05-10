package nebula.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SmartList<V extends Timable> extends ForwardingList<V> implements Cloneable {
	final List<V> values;
	final Map<String, V> indexedValues;
	final Map<String, Integer> indexedIndex;
	final List<DataListener<V>> listeneres;
	final Function<V, String> indexFunction;

	@Override
	protected List<V> delegate() {
		return values;
	}

	public SmartList(Function<V, String> indexFunction) {
		this.indexFunction = indexFunction;
		this.values = new CopyOnWriteArrayList<V>();
		this.indexedValues = Maps.newHashMap();
		this.indexedIndex = Maps.newHashMap();
		this.listeneres = Lists.newArrayList();
	}

	public <K> Classificator<K, V> classify(Function<V, K> indexerFunction) {
		return new DataClassificator<K, V>(values, indexerFunction);
	}

	public <K> Classificator<K, V> liveClassify(Function<V, K> indexerFunction) {
		DataClassificator<K, V> classificator = new DataClassificator<K, V>(values, indexerFunction);
		listeneres.add(classificator);
		return classificator;
	}

	public List<V> filter(Predicate<V> filterFunction) {
		return new DataFilter<V>(values, filterFunction).get();
	}

	public ClassifiableFilter<V> liveFilter(Predicate<V> filterFunction) {
		DataFilter<V> filter = new DataFilter<V>(values, filterFunction);
		listeneres.add(filter);
		return filter;
	}

	public V get(String key) {
		return this.indexedValues.get(key);
	}

	@Override
	public void add(int index, V v) {
		for (DataListener<V> listener : this.listeneres) {
			listener.add(v);
		}
		String key = indexFunction.apply(v);
		this.indexedValues.put(key, v);
		this.indexedIndex.put(key, index);
		super.add(index, v);
	}

	@Override
	public boolean add(V v) {
		String key = indexFunction.apply(v);
		if (!this.indexedValues.containsKey(key)) {
			for (DataListener<V> listener : this.listeneres) {
				listener.add(v);
			}
			boolean result = super.add(v);
			this.indexedValues.put(key, v);
			this.indexedIndex.put(key, values.size() - 1);
			return result;
		} else {
			V oldData = this.indexedValues.get(key);
			int index = this.indexedIndex.get(key);
			this.values.set(index, v);
			this.indexedValues.put(key, v);
			
			for (DataListener<V> listener : this.listeneres) {
				listener.update(oldData, v);
			}
		}
		return true;
	}

	@Override
	public V set(int index, V element) {
		return super.set(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends V> c) {
		boolean status = false;
		for (V v : c) {
			status = this.add(v) && status;
		}
		return status;
	}

	@Override
	public V remove(int index) {
		for (DataListener<V> listener : this.listeneres) {
			listener.remove(this.get(index));
		}
		String key = indexFunction.apply(this.get(index));
		this.indexedValues.remove(key);
		this.indexedIndex.remove(key);
		return super.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException("Cann't remove all");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object v) {
		for (DataListener<V> listener : this.listeneres) {
			listener.remove((V) v);
		}
		String key = indexFunction.apply((V)v);
		this.indexedValues.remove(key);
		this.indexedIndex.remove(key);
		return super.remove(v);
	}

	@Override
	public SmartList<V> clone() {
		SmartList<V> list = new SmartList<V>(indexFunction);
		list.addAll(values);
		return list;
	}

}

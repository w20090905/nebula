package nebula.data;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class InheritSmartList<I, V extends Timable> extends SmartList<I, V> {
	private final SmartList<I, V> parent;

	public InheritSmartList(SmartList<I, V> parent, Function<V, I> indexFunction) {
		super(indexFunction);
		this.parent = parent;
	}

	@Override
	public <T extends DataListener<V>> T addListener(T listener) {
		parent.addListener(listener);
		super.addListener(listener);
		return listener;
	}
	
	private Iterable<V> getAllValues() {
		if (parent instanceof InheritSmartList) {
			return Iterables.concat(values, ((InheritSmartList<I, V>) parent).getAllValues());
		} else {
			return Iterables.concat(values, parent.values);
		}
	}

	public V get(String key) {
		return this.indexedValues.containsKey(key) ? (V) this.indexedValues.get(key) : (V) parent.indexedValues
				.get(key);
	}

	@Override
	public InheritSmartList<I, V> clone() {
		InheritSmartList<I, V> list = new InheritSmartList<I, V>(this.parent, indexFunction);
		list.addAll(values);
		return list;
	}

}

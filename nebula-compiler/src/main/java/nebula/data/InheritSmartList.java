package nebula.data;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class InheritSmartList<V extends Timable> extends SmartList<V> {
	private final SmartList<V> parent;

	public InheritSmartList(SmartList<V> parent, Function<V, String> indexFunction) {
		super(indexFunction);
		this.parent = parent;
	}

	public <K> Classificator<K, V> liveClassify(Function<V, K> indexerFunction) {
		DataClassificator<K, V> classificator = new DataClassificator<K, V>(values, indexerFunction);
		listeneres.add(classificator);
		return classificator;
	}	
	
	private<K> void addListener(DataClassificator<K, V> classificator){
		if(parent instanceof InheritSmartList){
			listeneres.add(classificator);
			((InheritSmartList<V>) parent).addListener(classificator);
		}else{
			listeneres.add(classificator);
			parent.listeneres.add(classificator);						
		}		
	}

	public ClassifiableFilter<V> liveFilter(Predicate<V> filterFunction) {
		DataFilter<V> filter = new DataFilter<V>(getAllValues(), filterFunction);
		listeneres.add(filter);
		parent.listeneres.add(filter);
		return filter;
	}
	
	private void addListener(DataFilter<V> filter){
		if(parent instanceof InheritSmartList){
			listeneres.add(filter);
			((InheritSmartList<V>) parent).addListener(filter);
		}else{
			listeneres.add(filter);
			parent.listeneres.add(filter);						
		}		
	}
	
	private Iterable<V> getAllValues(){
		if(parent instanceof InheritSmartList){
			return Iterables.concat(values, ((InheritSmartList<V>) parent).getAllValues());
		}else{
			return Iterables.concat(values, parent.values);			
		}
	}

	public V get(String key) {
		return this.indexedValues.containsKey(key) ? (V) this.indexedValues.get(key) : (V) parent.indexedValues
				.get(key);
	}

	@Override
	public InheritSmartList<V> clone() {
		InheritSmartList<V> list = new InheritSmartList<V>(this.parent, indexFunction);
		list.addAll(values);
		return list;
	}

}
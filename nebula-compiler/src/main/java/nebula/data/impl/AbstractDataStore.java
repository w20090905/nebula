package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.ClassifiableFilter;
import nebula.data.Classificator;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.SmartList;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class AbstractDataStore<V> implements DataStore<V> {
	public static final String KEY_NAME = "ID";

	final DataPersister<V> persistence;
	final SmartList<V> values;
	final Map<String, V> quickIndex;

	ReentrantLock lock = new ReentrantLock();
	final Function<V, String> keyMaker;

	AbstractDataStore(Function<V, String> keyMaker,DataPersister<V> persistence) {
		this.persistence = persistence;
		values = new SmartList<V>(keyMaker);
		quickIndex = new HashMap<String, V>();
		this.keyMaker = keyMaker;
	}

	@Override
	public V get(String key) {
		return quickIndex.get(key);
	}

	@Override
	public void add(V v) {
//		if (v.isTransient()) {
//			EditableK V = (EditableK) v;
//			V.store = this;
//		}
		persistence.add(v);
	}

	@Override
	public void remove(V v) {
	}

	@Override
	public void flush() {
		persistence.flush();
	}

	@Override
	public void markChanged(V v) {
		persistence.markChanged(v);
	}

	@Override
	public void apply(V newV) {
//		EditableK newK = (EditableK) newV;
//		if (newK.source != null) {
//			Map<String, Object> newData = new HashMap<String, Object>(newK.data);
//			newData.putAll(newK.newData);
//
//			lock.lock();
//			lock.lock();
//			try {
//				KImp source = newK.source;
//				if (newK.data == source.data) {
//					source.data = newData;
//					newK.resetWith(source);
//				} else {
//					throw new RuntimeException("V update by some others");
//				}
//			} finally {
//				lock.unlock();
//			}
//		} else {
//			Map<String, Object> newData = new HashMap<String, Object>(newK.newData);
//
//			String id = "";
//			for (Field f : type.getFields()) {
//				if (f.isKey()) {
//					id += newData.get(f.getName());
//				}
//			}
//			newData.put("ID", id);
//
//			lock.lock();
//			try {
//				KImp source = new KImp(this, newData);
//				newK.resetWith(source);
//				this.datas.add(source);
//				this.quickIndex.put(source.getID(), source);
//			} finally {
//				lock.unlock();
//			}
//		}
	}

	@Override
	public List<V> all() {
		return this.values;
	}

	public void clear() {
		values.clear();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub

	}

	@Override
	public <K> Classificator<K, V> classify(Function<V, K> indexerFunction) {
		return values.classify(indexerFunction);
	}

	@Override
	public <K> Classificator<K, V> liveClassify(Function<V, K> indexerFunction) {
		return values.liveClassify(indexerFunction);
	}

	@Override
	public List<V> filter(Predicate<V> filterFunction) {
		return values.filter(filterFunction);
	}

	@Override
	public ClassifiableFilter<V> liveFilter(Predicate<V> filterFunction) {
		return values.liveFilter(filterFunction);
	}

	@Override
	public Function<V, String> getIdMaker() {
		return this.keyMaker;
	}

}

package nebula.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Index;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class AbstractDataStore<K> implements DataStore<K> {
	public static final String KEY_NAME = "ID";

	final DataPersister<K> persistence;
	final List<K> datas;
	final Map<String, K> quickIndex;

	ReentrantLock lock = new ReentrantLock();

	AbstractDataStore(DataPersister<K> persistence) {
		this.persistence = persistence;
		datas = new CopyOnWriteArrayList<K>();
		quickIndex = new HashMap<String, K>();
	}

	@Override
	public K get(String key) {
		return quickIndex.get(key);
	}

	@Override
	public List<K> query(Predicate<K> filter) {
		List<K> newList = new ArrayList<K>();
		for (K item : datas) {
			if (filter.apply(item)) newList.add(item);
		}
		return newList;
	}

	@Override
	public void add(K v) {
//		if (v.isTransient()) {
//			EditableK K = (EditableK) v;
//			K.store = this;
//		}
		persistence.add(v);
	}

	@Override
	public void remove(K v) {
	}

	@Override
	public void flush() {
		persistence.flush();
	}

	@Override
	public void markChanged(K v) {
		persistence.markChanged(v);
	}

	@Override
	public void apply(K newV) {
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
//					throw new RuntimeException("K update by some others");
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
	public List<K> all() {
		return this.datas;
	}

	public void clear() {
		datas.clear();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub

	}

	Map<Index<K, String>, ListMultimap<String, K>> quickClassificator;

	@Override
	public void addIndex(Index<K, String> key) {
		ListMultimap<String, K> data = ArrayListMultimap.create();
		quickClassificator.put(key, data);
	}

	@Override
	public ListMultimap<String, K> getByIndex(Index<K, String> key) {
		return quickClassificator.get(key);
	}

	@Override
	public void removeIndex(Index<K, String> key) {
		quickClassificator.remove(key);
	}


}

package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.ClassifiableFilter;
import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Filter;
import nebula.data.LiveList;
import nebula.lang.Type;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class EntityDataStore implements DataStore<Entity> {
	public static final String KEY_NAME = "ID";

	final DataPersister<Entity> persistence;
	final LiveList<Object, Entity> values;
	final ReentrantLock lock = new ReentrantLock();
	final Function<Entity, Object> idMaker;
	long lastModified;

	EntityDataStore(Function<Entity, Object> keyMaker, DataPersister<Entity> persistence, Type type) {
		this.persistence = persistence;
		this.values = new LiveList<Object, Entity>(keyMaker);
		this.idMaker = keyMaker;
	}

	@Override
	public Entity get(Object key) {
		return this.values.get(key);
	}

	@Override
	public void add(Entity v) {
		if (v.isTransient()) {
			EditableEntity entity = (EditableEntity) v;
			entity.store = this;
		}
		persistence.add(v);
	}

	@Override
	public void remove(Entity v) {
	}

	@Override
	public void flush() {
		persistence.flush();
	}

	@Override
	public void markChanged(Entity v) {
		persistence.markChanged(v);
	}

	@Override
	public void apply(Entity newV) {
		EditableEntity newEntity = (EditableEntity) newV;
		if (newEntity.source != null) {
			Map<String, Object> newData = new HashMap<String, Object>(newEntity.data);
			newData.putAll(newEntity.newData);

			lock.lock();
			try {
				EntityImp source = newEntity.source;
				if (newEntity.data == source.data) {
					source.data = newData;
					newEntity.resetWith(source);
				} else {
					throw new RuntimeException("entity update by some others");
				}
			} finally {
				lock.unlock();
			}
		} else {
			Object id = idMaker.apply(newEntity);
			newEntity.put(KEY_NAME, id);
			Map<String, Object> newData = new HashMap<String, Object>(newEntity.newData);
			lock.lock();
			try {
				EntityImp source = new EntityImp(this, newData);
				newEntity.resetWith(source);
				this.values.add(source);
			} finally {
				lock.unlock();
			}
		}
	}

	@Override
	public List<Entity> all() {
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
	public <K> Classificator<K, Entity> classify(Function<Entity, K> indexerFunction) {
		DataClassificator<K, Entity> classificator = new DataClassificator<K, Entity>(this.values, indexerFunction);
		return classificator;
	}

	@Override
	public <K> Classificator<K, Entity> liveClassify(Function<Entity, K> indexerFunction) {
		DataClassificator<K, Entity> classificator = new DataClassificator<K, Entity>(indexerFunction);
		values.addListener(classificator);
		return classificator;
	}

	@Override
	public List<Entity> filter(Predicate<Entity> filterFunction) {
		Filter<Entity> filter = Entities.filter(filterFunction);
		this.values.addListener((DataListener<Entity>) filter);
		return filter.get();
	}

	@Override
	public ClassifiableFilter<Entity> liveFilter(Predicate<Entity> filterFunction) {
		ClassifiableFilter<Entity> filter = Entities.filter(filterFunction);
		this.values.addListener((DataListener<Entity>) filter);
		return filter;
	}

	// public <K> Classificator<K, V> classify(Function<V, K> indexerFunction) {
	// return new DataClassificator<K, V>(values, indexerFunction);
	// }
	//
	// public <K> Classificator<K, V> liveClassify(Function<V, K>
	// indexerFunction) {
	// DataClassificator<K, V> classificator = new DataClassificator<K,
	// V>(values, indexerFunction);
	// listeneres.add(classificator);
	// return classificator;
	// }
	//
	// public List<V> filter(Predicate<V> filterFunction) {
	// return new DataFilter<V>(values, filterFunction).get();
	// }
	//
	// public ClassifiableFilter<V> liveFilter(Predicate<V> filterFunction) {
	// DataFilter<V> filter = new DataFilter<V>(values, filterFunction);
	// listeneres.add(filter);
	// return filter;
	// }

	// @Override
	// public Function<Entity, Object> getIdMaker() {
	// return this.idMaker;
	// }

	@Override
	public long getLastModified() {
		return lastModified;
	}
}

package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.ClassifiableFilter;
import nebula.data.Classificator;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.SmartList;
import nebula.lang.Type;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class EntityDataStore implements DataStore<Entity> {
	public static final String KEY_NAME = "ID";

	final DataPersister<Entity> persistence;
	final SmartList<Entity> values;
	final ReentrantLock lock = new ReentrantLock();
	final Function<Entity, String> idMaker;
	long lastModified;

	EntityDataStore(Function<Entity, String> keyMaker, DataPersister<Entity> persistence, Type type) {
		this.persistence = persistence;
		this.values = new SmartList<Entity>(keyMaker);
		this.idMaker = keyMaker;
	}

	@Override
	public Entity get(String key) {
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
			String id = idMaker.apply(newEntity);
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
		return this.values.classify(indexerFunction);
	}

	@Override
	public <K> Classificator<K, Entity> liveClassify(Function<Entity, K> indexerFunction) {
		return this.values.liveClassify(indexerFunction);
	}

	@Override
	public List<Entity> filter(Predicate<Entity> filterFunction) {
		return this.values.filter(filterFunction);
	}

	@Override
	public ClassifiableFilter<Entity> liveFilter(Predicate<Entity> filterFunction) {
		return this.values.liveFilter(filterFunction);
	}

	@Override
	public Function<Entity, String> getIdMaker() {
		return this.idMaker;
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}
}

package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Classificator;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.SmartList;
import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class EntityDataStore implements DataStore<Entity> {
	private static Log log = LogFactory.getLog(EntityDataStore.class);

	final DataPersister<Entity> persistence;
	final SmartList<Object, Entity> values;
	final ReentrantLock lock = new ReentrantLock();
	final Function<Entity, Object> idMaker;
	long lastModified;
	final Map<String, Classificator<String, Entity>> classificatores;

	EntityDataStore(Function<Entity, Object> keyMaker, DataPersister<Entity> persistence, Type type) {
		this.persistence = persistence;
		this.values = new SmartList<Object, Entity>(keyMaker);
		this.idMaker = keyMaker;
		classificatores = Maps.newHashMap();

		for (Field f : type.getFields()) {
			if (f.getAttrs().containsKey("GroupBy")) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					String name = f.getName();
					Classificator<String, Entity> classificator = Entities.classify(f.getName());
					this.values.addListener(classificator);
					classificatores.put(name, classificator);
					if (log.isDebugEnabled()) {
						log.debug(type.getName() + " add classificator " + name);
					}
				} else if (f.getRefer() == Reference.ByRef) {
					for (Field inf : f.getType().getFields()) {
						if (inf.isKey() && inf.getType().getStandalone() == TypeStandalone.Basic) {
							String name = f.getName() + inf.getName();
							Classificator<String, Entity> classificator = Entities.classify(name);
							this.values.addListener(classificator);
							classificatores.put(name, classificator);
							if (log.isDebugEnabled()) {
								log.debug(type.getName() + " add classificator " + name);
							}
						}
					}
				}
			}
		}
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
			newEntity.put(Entity.PRIMARY_KEY, id);
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

	}

	@Override
	public void unload() {
	}

	//
	// @Override
	// public <K> Classificator<K, Entity> classify(Function<Entity, K>
	// indexerFunction) {
	// DataClassificator<K, Entity> classificator = new DataClassificator<K,
	// Entity>(this.values, indexerFunction);
	// return classificator;
	// }
	//
	// @Override
	// public <K> Classificator<K, Entity> liveClassify(Function<Entity, K>
	// indexerFunction) {
	// DataClassificator<K, Entity> classificator = new DataClassificator<K,
	// Entity>(indexerFunction);
	// values.addListener(classificator);
	// return classificator;
	// }
	//
	// @Override
	// public List<Entity> filter(Predicate<Entity> filterFunction) {
	// Filter<Entity> filter = Entities.filter(filterFunction);
	// this.values.addListener((DataListener<Entity>) filter);
	// return filter.get();
	// }
	//
	// @Override
	// public ClassifiableFilter<Entity> liveFilter(Predicate<Entity>
	// filterFunction) {
	// ClassifiableFilter<Entity> filter = Entities.filter(filterFunction);
	// this.values.addListener((DataListener<Entity>) filter);
	// return filter;
	// }

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public Classificator<String, Entity> getClassificator(String name) {
		return this.classificatores.get(name);
	}

	@Override
	public Map<String, Classificator<String, Entity>> getClassificatores() {
		return this.classificatores;
	}
}

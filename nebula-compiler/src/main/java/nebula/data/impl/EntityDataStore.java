package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Classificator;
import nebula.data.Editable;
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

public class EntityDataStore implements DataStoreEx<Entity> {
	private static Log log = LogFactory.getLog(EntityDataStore.class);

	final DataReposEx persistence;
	final SmartList<Object, Entity> values;
	final ReentrantLock lock = new ReentrantLock();
	final Function<Entity, Object> idMaker;
	long lastModified;
	final Map<String, Classificator<String, Entity>> classificatores;

	EntityDataStore(Function<Entity, Object> keyMaker, DataReposEx persistence, Type type) {
		this.persistence = persistence;
		this.values = new SmartList<Object, Entity>(keyMaker);
		this.idMaker = keyMaker;
		classificatores = Maps.newHashMap();

		for (Field f : type.getFields()) {
			if (f.getAttrs().containsKey("GroupBy")) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					final String name = f.getName();
					DataClassificator<String, Entity> classificator = new DataClassificator<String, Entity>( new Function<Entity, String>() {
						@Override
						public String apply(Entity from) {
							return  String.valueOf(from.get(name));
						}
					});
					this.values.addListener(classificator);
					classificatores.put(name, classificator);
					if (log.isDebugEnabled()) {
						log.debug(type.getName() + " add classificator " + name);
					}
				} else if (f.getRefer() == Reference.ByRef) {
					for (Field inf : f.getType().getFields()) {
						if (inf.isKey() && inf.getType().getStandalone() == TypeStandalone.Basic) {
							final String name = f.getName() + inf.getName();
							DataClassificator<String, Entity> classificator = new DataClassificator<String, Entity>( new Function<Entity, String>() {
								@Override
								public String apply(Entity from) {
									return  String.valueOf(from.get(name));
								}
							});
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
		EditableEntity entity = (EditableEntity) v;
		if (v.isTransient()) {
			entity.store = this;
		}
		persistence.markChanged(entity);
	}

	@Override
	public void remove(Entity v) {
	}

	@Override
	public void flush() {
		persistence.flush();
	}

	@Override
	public void markChanged(Editable v) {
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
	public List<Entity> listAll() {
		return this.values;
	}

	public void clearChanges() {
		persistence.clearChanges();
	}

	@Override
	public void load() {

	}

	@Override
	public void unload() {
	}

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

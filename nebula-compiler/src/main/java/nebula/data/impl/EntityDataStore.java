package nebula.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.Filter;
import nebula.data.Entity;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.lang.Field;
import nebula.lang.Type;

public class EntityDataStore implements DataStore<Entity> {
	final DataPersister<Entity> persistence;
	final Type type;

	final List<Entity> datas;
	final Map<String, Entity> quickIndex;

	ReentrantLock lock = new ReentrantLock();

	EntityDataStore(DataPersister<Entity> persistence, Type type) {
		this.persistence = persistence;
		this.type = type;
		datas = new CopyOnWriteArrayList<Entity>();
		quickIndex = new HashMap<String, Entity>();
	}

	@Override
	public Entity createNew() {
		EditableEntity agent = new EditableEntity(this);
		return agent;
	}

	@Override
	public Entity load(String key) {
		return quickIndex.get(key);
	}

	@Override
	public List<Entity> query(Filter<Entity> filter) {
		List<Entity> newList = new ArrayList<Entity>();
		for (Entity item : datas) {
			if (filter.match(item)) newList.add(item);
		}
		return newList;
	}

	@Override
	public void add(Entity v) {
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
			EntityImp source = newEntity.source;
			if (newEntity.data == source.data) {
				source.data = newData;
				newEntity.resetWith(source);
			} else {
				throw new RuntimeException("entity update by some others");
			}
			lock.unlock();
		} else {
			Map<String, Object> newData = new HashMap<String, Object>(newEntity.newData);

			String id = "";
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					id += newData.get(f.getName());
				}
			}
			newData.put("ID", id);

			lock.lock();
			EntityImp source = new EntityImp(this, newData);
			newEntity.resetWith(source);
			this.datas.add(source);
			this.quickIndex.put(source.getID(), source);
			lock.unlock();
		}
	}

	@Override
	public List<Entity> all() {
		return this.datas;
	}

	@Override
	public String getID() {
		return type.getName();
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}

}

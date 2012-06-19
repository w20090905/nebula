package nebula.data.mem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Entity;
import nebula.data.Store;
import nebula.lang.Field;
import nebula.lang.Type;

public class EntityStore implements Store<Entity> {
	final PersistenceMem persistence;
	final Type type;

	final List<Entity> datas;
	final Map<String, Entity> quickIndex;

	ReentrantLock lock = new ReentrantLock();

	EntityStore(PersistenceMem persistence, Type type) {
		this.persistence = persistence;
		this.type = type;
		datas = new CopyOnWriteArrayList<>();
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
	public List<Entity> findBy(String key) {
		return null;
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

	void markChanged(EditableEntity v) {
		persistence.markChanged(v);
	}

	void apply(EditableEntity newEntity) {
		if (newEntity.source != null) {
			Map<String, Object> newData = new HashMap<>(newEntity.data);
			newData.putAll(newEntity.newData);

			lock.lock();
			EntityImp source = newEntity.source;
			if (newEntity.data == source.data) {
				source.data = newData;
				newEntity.reset(source);
			} else {
				throw new RuntimeException("entity update by some others");
			}
			lock.unlock();
		} else {
			Map<String, Object> newData = new HashMap<>(newEntity.newData);
			
			String id = "";
			for (Field f : type.getFields()) {
				if(Field.KEY ==  f.getImportance()){
					id += newData.get(f.getName());
				}
			}
			newData.put("ID", id);
			
			lock.lock();
			EntityImp source = new EntityImp(this, newData);
			newEntity.reset(source);
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

}

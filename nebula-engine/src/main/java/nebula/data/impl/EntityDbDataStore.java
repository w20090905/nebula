package nebula.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.data.Entity;
import nebula.data.db.DBExec;
import nebula.lang.Field;
import nebula.lang.Type;

public class EntityDbDataStore extends EntityDataStore {

	final DBExec db;

	EntityDbDataStore(final DbDataPersister persistence, Type type, final DBExec exec) {
		super(persistence, type);
		this.db = exec;
		exec.init();
		List<Map<String, Object>> list = exec.getAll();
		for (Map<String, Object> data : list) {
			String id = "";
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					id += data.get(f.getName());
				}
			}
			data.put("ID", id);
			EntityImp entity = new EntityImp(this, data);
			datas.add(entity);
			quickIndex.put(entity.getID(), entity);
		}
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

				// DB
				db.update(source.data, source.getID());

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

			// DB
			db.insert(source.data);
			source.data = db.get(id);
			source.data.put("ID", id);

			newEntity.resetWith(source);

			this.datas.add(source);
			this.quickIndex.put(source.getID(), source);
			lock.unlock();
		}
	}

	void clear() {
		db.deleteAll();
	}

	@Override
	public void load() {
//		db.init();
	}

	@Override
	public void unload() {
		db.close();
	}
}

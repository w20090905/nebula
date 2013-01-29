package nebula.data.impl;

import java.util.List;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DBExec;
import nebula.lang.Field;
import nebula.lang.Type;

public class EntityDbDataStore extends EntityDataStore {

	final DBExec db;

	EntityDbDataStore(final EntityDbDataPersister persistence, Type type, final DBExec exec) {
		super(persistence, type);
		this.db = exec;

		List<EditableEntity> list = exec.getAll();
		for (EditableEntity data : list) {
			String id = "";
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					id += data.get(f.getName());
				}
			}
			data.put("ID", id);

			loadin(data, id);
		}
	}

	@Override
	public void apply(Entity newV) {
		EditableEntity newEntity = (EditableEntity) newV;
		if (newEntity.source != null) {// update
			assert newEntity.source instanceof DbEntity;
			DbEntity sourceEntity = (DbEntity) newEntity.source;

			assert sourceEntity == datas.get(sourceEntity.index);
			lock.lock();
			try {
				// DB
				String id = sourceEntity.getID();
				db.update(newEntity, id);

				EntityImp newSource = loadin(sourceEntity, db.get(id));

				newEntity.resetWith(newSource);

			} finally {
				lock.unlock();
			}
		} else { // insert
			String id = "";
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					id += newEntity.get(f.getName());
				}
			}
			newEntity.put("ID", id);

			lock.lock();

			// DB
			db.insert(newEntity);
			EntityImp newSource = loadin(db.get(id), id);

			newEntity.resetWith(newSource);

			lock.unlock();
		}
	}

	class DbEntity extends EntityImp {
		final int index;

		DbEntity(DataStore<Entity> store, Map<String, Object> data, int index) {
			super(store, data);
			this.index = index;
		}
	}

	private EntityImp loadin(EditableEntity entity, String id) {
		entity.put(KEY_NAME, id);
		DbEntity inner = new DbEntity(this, entity.newData, datas.size());
		this.datas.add(inner);
		this.quickIndex.put(id, inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(KEY_NAME, sourceEntity.getID());
		DbEntity inner = new DbEntity(this, newEntity.newData, sourceEntity.index);
		this.datas.set(sourceEntity.index, inner);
		this.quickIndex.put(sourceEntity.getID(), inner);
		return inner;
	}

	public void clear() {
		db.deleteAll();
	}

	@Override
	public void load() {
		// db.init();
	}

	@Override
	public void unload() {
		db.close();
	}
}

package nebula.data.impl;

import java.util.List;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbDataExecutor;
import nebula.lang.Type;

public class DbTransactionEntityDataStore extends EntityDataStore {

	final DbDataExecutor db;
	final IDSetter idSetter;

	DbTransactionEntityDataStore(final DbEntityDataPersister persistence, Type type, final DbDataExecutor exec) {
		super(persistence, type);
		this.db = exec;
		idSetter = IDSetterBuilder.getIDSetter(type);

		List<EditableEntity> list = exec.getAll();
		for (EditableEntity data : list) {
			String id = idSetter.getID(data);
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
			String id =idSetter.getID(newEntity);
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

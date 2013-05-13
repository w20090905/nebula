package nebula.data.impl;

import java.util.List;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbDataExecutor;
import nebula.lang.Type;

public class DbMasterEntityDataStore extends EntityDataStore {

	final DbDataExecutor db;

	DbMasterEntityDataStore(final DbEntityDataPersister persistence, Type type, final DbDataExecutor exec) {
		super(IdMakerBuilder.getIDSetter(type), persistence, type);
		this.db = exec;

		List<EditableEntity> list = exec.getAll();
		for (EditableEntity data : list) {
			loadin(data);
		}
	}

	@Override
	public void apply(Entity newV) {
		EditableEntity newEntity = (EditableEntity) newV;
		if (newEntity.source != null) {// update
			assert newEntity.source instanceof DbEntity;
			DbEntity sourceEntity = (DbEntity) newEntity.source;

			assert sourceEntity == this.values.get(sourceEntity.index);
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
			String id = (String)this.idMaker.apply(newEntity);

			newEntity.put("ID", id);

			lock.lock();

			// DB
			db.insert(newEntity);
			EntityImp newSource = loadin(db.get(id));

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

	private EntityImp loadin(EditableEntity entity) {
		entity.put(KEY_NAME, idMaker.apply(entity));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(KEY_NAME, sourceEntity.getID());
		DbEntity inner = new DbEntity(this, newEntity.newData, sourceEntity.index);
		this.values.add(inner);
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

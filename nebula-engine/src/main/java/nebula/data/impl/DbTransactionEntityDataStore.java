package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import nebula.data.Entity;
import nebula.data.db.DbTxDataExecutor;
import nebula.lang.Field;
import nebula.lang.NebulaNative;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

class DbTransactionEntityDataStore extends EntityDataStore {

	final DbTxDataExecutor db;

	final IDGenerator idGenerator;
	final String key;

	DbTransactionEntityDataStore(final DbDataRepos dataRepos, Type type, final DbTxDataExecutor exec) {
		super(IdMakerBuilder.getIDReader(type), dataRepos, type);
		this.db = exec;

		Field localKey = null;
		for (Field f : type.getFields()) {
			if (f.isKey()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					localKey = f;
					break;
				}
			}
		}

		key = checkNotNull(localKey).getName();

		idGenerator = IDGenerators.build(type);
		idGenerator.init(exec.getCurrentMaxID());
		idGenerator.setSeed((long) type.getName().hashCode() % (1 << 8));

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
			Object id = null;

			lock.lock();
			try {
				// DB
				id = (Long) sourceEntity.get(key);

				NebulaNative.onSave(newEntity, type, dataRepos);
				db.update(newEntity, id);
				EntityImp newSource = loadin(sourceEntity, db.get(id));

				newEntity.resetWith(newSource);
			} finally {
				lock.unlock();
			}
		} else { // insert
			Object id = null;

			lock.lock();
			try {
				id = idGenerator.nextValue();
				newEntity.put(key, id);

				NebulaNative.onSave(newEntity, type, dataRepos);

				// DB
				db.insert(newEntity);
				EntityImp newSource = loadin(db.get(id));
				newEntity.resetWith(newSource);
			} finally {
				lock.unlock();
			}
		}
	}

	class DbEntity extends EntityImp {
		final int index;

		DbEntity(DataStoreEx<Entity> store, Map<String, Object> data, int index) {
			super(store, data);
			this.index = index;
		}
	}

	private EntityImp loadin(EditableEntity entity) {
		entity.put(Entity.PRIMARY_KEY, String.valueOf((Long) entity.get(key)));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		NebulaNative.onLoad(entity, this.type, dataRepos);
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(Entity.PRIMARY_KEY, String.valueOf((Long) newEntity.get(key)));
		DbEntity inner = new DbEntity(this, newEntity.newData, sourceEntity.index);
		NebulaNative.onLoad(newEntity, super.type, dataRepos);
		this.values.add(inner);
		return inner;
	}

	public void clearChanges() {
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

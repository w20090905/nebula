package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.IDGenerator;
import nebula.data.db.DbDataExecutor;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

public class DbMasterEntityDataStore extends EntityDataStore {

	final DbDataExecutor db;

	final IDGenerator idGenerator;
	final String key;
	boolean withID = false;

	DbMasterEntityDataStore(final DbEntityDataPersister persistence, Type type, final DbDataExecutor exec) {
		super(IdMakerBuilder.getIDReader(type), persistence, type);
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

		if (localKey.getType().getName().equals("ID")) {
			idGenerator = IDGenerators.build(type);
			idGenerator.init(exec.getCurrentMaxID());
			idGenerator.setSeed((long) type.getName().hashCode() % (1 << 8));
			withID = true;
		} else {
			idGenerator = null;
		}

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
			if (withID) {
				lock.lock();
				try {
					// DB
					long id = (Long)sourceEntity.get(key);
					db.update(newEntity, id);

					EntityImp newSource = loadin(sourceEntity, db.get(id));

					newEntity.resetWith(newSource);

				} finally {
					lock.unlock();
				}
			} else {
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
			}
		} else { // insert

			if (withID) {
				Long id = idGenerator.nextValue();
				newEntity.put(key, id);

				lock.lock();
				// DB
				db.insert(newEntity);
				EntityImp newSource = loadin(db.get(id));
				newEntity.resetWith(newSource);
				lock.unlock();

			} else {
				String referKey = (String) this.idMaker.apply(newEntity);
				newEntity.put(Entity.PRIMARY_KEY, referKey);

				lock.lock();

				// DB
				db.insert(newEntity);
				EntityImp newSource;

				newSource = loadin(db.get(referKey));

				newEntity.resetWith(newSource);

				lock.unlock();
			}
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
		entity.put(Entity.PRIMARY_KEY, idMaker.apply(entity));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(Entity.PRIMARY_KEY, sourceEntity.getID());
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

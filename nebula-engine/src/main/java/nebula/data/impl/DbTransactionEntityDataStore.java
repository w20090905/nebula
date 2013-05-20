package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.IDGenerator;
import nebula.data.db.DbTxDataExecutor;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

public class DbTransactionEntityDataStore extends EntityDataStore {

	final DbTxDataExecutor db;

	final IDGenerator idGenerator;
	final String key;

	DbTransactionEntityDataStore(final DbEntityDataPersister persistence, Type type, final DbTxDataExecutor exec) {
		super(IdMakerBuilder.getIDReader(type), persistence, type);
		this.db = exec;

		String localKey = null;

		for (Field f : type.getFields()) {
			if (f.isKey()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					localKey = f.getName();
					break;
				}
			}
		}
		key = checkNotNull(localKey);

		idGenerator = IDGenerators.build(type);

		List<EditableEntity> list = exec.getAll();
		for (EditableEntity data : list) {
			loadin(data);
		}
		idGenerator.init(exec.getCurrentMaxID());
		idGenerator.setSeed((long) type.getName().hashCode() % (1 << 8));
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
				Long id = (Long)sourceEntity.get(key);
				db.update(newEntity, id);

				EntityImp newSource = loadin(sourceEntity, db.get(id));

				newEntity.resetWith(newSource);

			} finally {
				lock.unlock();
			}
		} else { // insert
			Long id = idGenerator.nextValue();
			newEntity.put(key, id);
			newEntity.put("ID", String.valueOf(id));

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
		entity.put(KEY_NAME,  String.valueOf((Long)entity.get(key)));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(KEY_NAME, String.valueOf((Long)newEntity.get(key)));
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

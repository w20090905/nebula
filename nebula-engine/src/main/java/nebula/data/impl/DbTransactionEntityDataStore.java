package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import nebula.data.Entity;
import nebula.data.db.DbTxDataExecutor;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

class DbTransactionEntityDataStore extends EntityDataStore {

	final DbTxDataExecutor db;

	final IDGenerator idGenerator;
	final String key;

	final Field[] derivedFields;
	final Field[] defaultOnSaveFields;

	DbTransactionEntityDataStore(final DbDataRepos persistence, Type type, final DbTxDataExecutor exec) {
		super(IdMakerBuilder.getIDReader(type), persistence, type);
		this.db = exec;

		List<Field> derivedFields = Lists.newCopyOnWriteArrayList();
		List<Field> defaultOnSaveFields = Lists.newCopyOnWriteArrayList();
		Field localKey = null;
		for (Field f : type.getFields()) {
			if (f.isKey()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					localKey = f;
					break;
				}
			}
			if (f.isDerived()) {
				derivedFields.add(f);
			}
			if (f.isDefaultValue()) {
				defaultOnSaveFields.add(f);
			}
		}
		key = checkNotNull(localKey).getName();

		this.derivedFields = derivedFields.toArray(new Field[0]);
		this.defaultOnSaveFields = defaultOnSaveFields.toArray(new Field[0]);

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
			lock.lock();
			try {
				// DB
				Long id = (Long) sourceEntity.get(key);
				db.update(newEntity, id);

				EntityImp newSource = loadin(sourceEntity, db.get(id));

				newEntity.resetWith(newSource);

			} finally {
				lock.unlock();
			}
		} else { // insert
			Long id = idGenerator.nextValue();
			newEntity.put(key, id);
			newEntity.put(Entity.PRIMARY_KEY, String.valueOf(id));

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

		DbEntity(DataStoreEx<Entity> store, Map<String, Object> data, int index) {
			super(store, data);
			this.index = index;
		}
	}

	private EntityImp loadin(EditableEntity entity) {
		entity.put(Entity.PRIMARY_KEY, String.valueOf((Long) entity.get(key)));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		for (Field f : derivedFields) {
			entity.put(f.getName(), f.getDerivedExpr().eval(entity));
		}
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(Entity.PRIMARY_KEY, String.valueOf((Long) newEntity.get(key)));
		DbEntity inner = new DbEntity(this, newEntity.newData, sourceEntity.index);
		for (Field f : derivedFields) {
			newEntity.put(f.getName(), f.getDerivedExpr().eval(newEntity));
		}
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

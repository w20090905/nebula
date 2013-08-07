package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import nebula.data.Entity;
import nebula.data.db.DbDataExecutor;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

class DbMasterEntityDataStore extends EntityDataStore {

	final DbDataExecutor db;

	final IDGenerator idGenerator;
	final String key;
	boolean withID = false;
	final Field[] derivedFields;
	final Field[] defaultOnSaveFields;

	DbMasterEntityDataStore(final DbDataRepos persistence, Type type, final DbDataExecutor exec) {
		this(IdMakerBuilder.getIDReader(type), persistence, type, exec);
	}

	DbMasterEntityDataStore(Function<Entity, Object> keyMaker, final DbDataRepos persistence, Type type, final DbDataExecutor exec) {
		super(keyMaker, persistence, type);
		this.db = exec;

		List<Field> derivedFields = Lists.newCopyOnWriteArrayList();
		List<Field> defaultOnSaveFields = Lists.newCopyOnWriteArrayList();

		Field localKey = null;
		for (Field f : type.getFields()) {
			if (localKey==null && f.isKey()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic) {
					localKey = f;
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
					long id = (Long) sourceEntity.get(key);
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
				
				for (Field f : defaultOnSaveFields) {
					newEntity.put(f.getName(), f.getDerivedExpr().eval(newEntity));
				}

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

		DbEntity(DataStoreEx<Entity> store, Map<String, Object> data, int index) {
			super(store, data);
			this.index = index;
		}
	}

	private EntityImp loadin(EditableEntity entity) {
		entity.put(Entity.PRIMARY_KEY, idMaker.apply(entity));
		DbEntity inner = new DbEntity(this, entity.newData, this.values.size());
		for (Field f : derivedFields) {
			entity.put(f.getName(), f.getDerivedExpr().eval(entity));
		}
		this.values.add(inner);
		return inner;
	}

	private EntityImp loadin(DbEntity sourceEntity, EditableEntity newEntity) {
		newEntity.put(Entity.PRIMARY_KEY, sourceEntity.getID());
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

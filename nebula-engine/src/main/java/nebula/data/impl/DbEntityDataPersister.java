package nebula.data.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;

import nebula.data.Callback;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Holder;
import nebula.data.db.DbConfiguration;
import nebula.data.db.DbTxDataExecutor;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.lang.TypeStandalone;

public class DbEntityDataPersister implements DataPersister<Entity> {

	final TypeLoader typeLoader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String, Holder<DataStore<Entity>>> storeHolders;
	final DbConfiguration dbConfig;

	@Inject
	public DbEntityDataPersister(TypeLoader loader, DbConfiguration dbConfig) {
		this.typeLoader = loader;
		this.storeHolders = new HashMap<String, Holder<DataStore<Entity>>>();
		this.dbConfig = dbConfig;
	}

	@Override
	public <I> Holder<DataStore<Entity>> define(Class<I> clzIndex, Class<Entity> clz, String name) {
		Holder<DataStore<Entity>> datastoreHolder = this.storeHolders.get(name);
		if (datastoreHolder == null) {
			Type type = typeLoader.findType(name);
			DataStore<Entity> datastore = null;
			if (type.getStandalone() == TypeStandalone.Transaction) {
				datastore = new DbTransactionEntityDataStore(this, type, (DbTxDataExecutor) dbConfig.getPersister(type));
			} else {
				datastore = new DbMasterEntityDataStore(this, type, dbConfig.getPersister(type));
			}
			datastoreHolder = Holder.of(datastore);
			storeHolders.put(name, datastoreHolder);
		}
		return datastoreHolder;
	}

	// @Override
	// public void define(Class<Entity> clz, String name,
	// HolderListener<DataStore<Entity>> listener) {
	// Holder<DataStore<Entity>> store = define(clz, name);
	// store.addListener(listener);
	// }

	@Override
	public <I> Holder<DataStore<Entity>> reload(Class<I> clzIndex, Class<Entity> clz, String name) {
		Holder<DataStore<Entity>> datastoreHolder = this.storeHolders.get(name);

		if (datastoreHolder != null) {
			DataStore<Entity> oldData = datastoreHolder.get();
			oldData.unload();

			Type type = typeLoader.findType(name);
			DataStore<Entity> newData = new DbMasterEntityDataStore(this, type, dbConfig.getPersister(type));
			datastoreHolder.update(newData);
			return datastoreHolder;
		} else {
			throw new UnsupportedOperationException("DataStore<Entity> reload(Class<Entity> clz, String name) ");
		}
	}

	@Override
	public void add(Entity v) {
		assert v instanceof EditableEntity;

		EditableEntity vv = (EditableEntity) v;
		this.markChanged(vv);
	}

	@Override
	public void remove(Entity v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		lock.lock();
		try {
			Iterator<EditableEntity> i = changes.listIterator();
			// changes.clear();
			while (i.hasNext()) {
				EditableEntity e = i.next();
				e.store.apply(e);
			}
		} finally {
			changes.clear();
			lock.unlock();
		}
	}

	@Override
	public void clearChanges() {
		lock.lock();
		changes.clear();
		lock.unlock();
	}

	@Override
	public void transaction(Callback<Entity> callback) {
		// TODO Auto-generated method stub
	}

	CopyOnWriteArrayList<EditableEntity> changes = new CopyOnWriteArrayList<EditableEntity>();

	@Override
	public void markChanged(Entity v) {
		lock.lock();
		changes.add((EditableEntity) v);
		lock.unlock();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
	}

	@Override
	public void unload() {
		this.clearChanges();
	}

}

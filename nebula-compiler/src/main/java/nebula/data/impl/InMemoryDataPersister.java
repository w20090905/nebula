package nebula.data.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Callback;
import nebula.data.DataHolder;
import nebula.data.DataListener;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class InMemoryDataPersister implements DataPersister<Entity> {

	final TypeLoader loader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String, DataHolder<DataStore<Entity>>> stores;

	public InMemoryDataPersister(TypeLoader loader) {
		this.loader = loader;
		this.stores = new HashMap<String, DataHolder<DataStore<Entity>>>();
	}

	@Override
	public DataHolder<DataStore<Entity>> define(Class<Entity> clz, String name) {
		DataHolder<DataStore<Entity>> store = this.stores.get(name);
		if (store == null) {
			Type type = loader.findType(name);
			DataStore<Entity> newData = new EntityDataStore(this, type);
			store = new AbstractDataHolder<DataStore<Entity>>(newData);
			stores.put(name, store);
		}
		return store;
	}

	@Override
	public void define(Class<Entity> clz, String name, DataListener<DataStore<Entity>> listener) {
		DataHolder<DataStore<Entity>> store = define(clz, name);
		store.addListener(listener);
	}

	@Override
	public DataHolder<DataStore<Entity>> reload(Class<Entity> clz, String name) {
		DataHolder<DataStore<Entity>> datastoreHolder = this.stores.get(name);
		if (datastoreHolder != null) {
			DataStore<Entity> oldData = datastoreHolder.get();
			oldData.unload();

			Type type = loader.findType(name);
			DataStore<Entity> newData = new EntityDataStore(this, type);
			datastoreHolder.set(newData, oldData);
			return datastoreHolder;
		} else {
			throw new UnsupportedOperationException("DataStore<Entity> reload(Class<Entity> clz, String name) ");
		}
	}

	@Override
	public void add(Entity v) {
		if (v instanceof EditableEntity) {
			EditableEntity vv = (EditableEntity) v;
			this.markChanged(vv);
		}
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

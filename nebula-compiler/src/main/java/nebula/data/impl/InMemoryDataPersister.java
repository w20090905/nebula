package nebula.data.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Callback;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.Holder;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class InMemoryDataPersister implements DataPersister<Entity> {

	final TypeLoader loader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String, Holder<DataStore<Entity>>> stores;

	public InMemoryDataPersister(TypeLoader loader) {
		this.loader = loader;
		this.stores = new HashMap<String, Holder<DataStore<Entity>>>();
	}

	@Override
	public <I> Holder<DataStore<Entity>> define(Class<I> clzIndex, Class<Entity> clz, String name) {
		Holder<DataStore<Entity>> store = this.stores.get(name);
		if (store == null) {
			Type type = loader.findType(name);
			DataStore<Entity> newData = new EntityDataStore(IdMakerBuilder.getIDReader(type), this, type);
			store = Holder.of(newData);
			stores.put(name, store);
		}
		return store;
	}

	// @Override
	// public <I> void define(Class<I> clzIndex, Class<Entity> clz, String name,
	// HolderListener<DataStore<Entity>> listener) {
	// Holder<DataStore<Entity>> store = define(clzIndex, clz, name);
	// store.addListener(listener);
	// }

	// TODO
	@Override
	public <I> Holder<DataStore<Entity>> reload(Class<I> clzIndex, Class<Entity> clz, String name) {
		Holder<DataStore<Entity>> datastoreHolder = this.stores.get(name);
		if (datastoreHolder != null) {
			EntityDataStore oldData = (EntityDataStore) datastoreHolder.get();
			oldData.unload();

			Type type = loader.findType(name);
			DataStore<Entity> newData = new EntityDataStore(IdMakerBuilder.getIDReader(type), this, type);
			datastoreHolder.update(newData);
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
			changes.clear();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void clearChanges() {
		lock.lock();
		try {
			changes.clear();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void transaction(Callback<Entity> callback) {
		// TODO Auto-generated method stub
	}

	CopyOnWriteArrayList<EditableEntity> changes = new CopyOnWriteArrayList<EditableEntity>();

	@Override
	public void markChanged(Entity v) {
		lock.lock();
		try {
			changes.add((EditableEntity) v);
		} finally {
			lock.unlock();
		}

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

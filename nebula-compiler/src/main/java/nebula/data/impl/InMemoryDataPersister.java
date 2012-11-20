package nebula.data.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Callback;
import nebula.data.Entity;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.lang.TypeLoader;

public class InMemoryDataPersister implements DataPersister<Entity> {

	final TypeLoader loader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String, EntityDataStore> stores;

	public InMemoryDataPersister(TypeLoader loader) {
		this.loader = loader;
		this.stores = new HashMap<String, EntityDataStore>();
	}

	@Override
	public DataStore<Entity> define(Class<Entity> clz, String name) {
		EntityDataStore store = this.stores.get(name);
		if (store == null) {
			store = new EntityDataStore(this, loader.findType(name));
			stores.put(name, store);
		}
		return store;
	}

	@Override
	public DataStore<Entity> reload(Class<Entity> clz, String name) {
		EntityDataStore store = this.stores.get(name);
		if (store != null) {
			store.unload();
			stores.put(name, null);
		}
		store = new EntityDataStore(this, loader.findType(name));
		stores.put(name, store);
		return store;
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
		Iterator<EditableEntity> i = changes.listIterator();
		// changes.clear();
		while (i.hasNext()) {
			EditableEntity e = i.next();
			e.store.apply(e);
		}
		changes.clear();
		lock.unlock();
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

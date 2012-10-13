package nebula.data.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;

import nebula.data.Callback;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.data.impl.EditableEntity;
import nebula.db.DbConfiguration;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class PersistenceDB implements Persistence<Entity> {

	final TypeLoader loader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String, EntityStoreDB> stores;
	final DbConfiguration dbConfig;

	@Inject
	public PersistenceDB(TypeLoader loader, DbConfiguration dbConfig) {
		this.loader = loader;
		this.stores = new HashMap<String, EntityStoreDB>();
		this.dbConfig = dbConfig;
	}

	@Override
	public Store<Entity> define(Class<Entity> clz, String name) {
		EntityStoreDB store = this.stores.get(name);
		if (store == null) {
			Type type = loader.findType(name);
			store = new EntityStoreDB(this, type, dbConfig.getPersister(type));
			stores.put(name, store);
		}
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

}

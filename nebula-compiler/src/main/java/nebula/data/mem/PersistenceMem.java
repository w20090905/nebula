package nebula.data.mem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Callback;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.lang.TypeLoader;

public class PersistenceMem implements Persistence<Entity> {

	final TypeLoader loader;
	ReentrantLock lock = new ReentrantLock();
	final Map<String,EntityStore> stores;
	
	public PersistenceMem(TypeLoader loader){
		this.loader = loader;
		this.stores = new HashMap<String, EntityStore>();
	}

	@Override
	public Store<Entity> define(Class<Entity> clz, String name) {
		EntityStore store = this.stores.get(name);
		if(store==null){
			store =new EntityStore(this, loader.findType(name));
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
		Iterator<EditableEntity> i = changes.listIterator();
		changes.clear();
		while(i.hasNext()){
			EditableEntity e = i.next();
			e.store.apply(e);			
		}
		lock.unlock();
		changes.clear();
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

	CopyOnWriteArrayList<EditableEntity> changes = new CopyOnWriteArrayList<>();

	public void markChanged(EditableEntity v) {
		lock.lock();
		changes.add(v);
		lock.unlock();
	}

}

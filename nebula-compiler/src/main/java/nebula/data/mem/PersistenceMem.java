package nebula.data.mem;

import java.util.ArrayList;
import java.util.List;

import nebula.data.Callback;
import nebula.data.Persistence;
import nebula.data.Store;

public class PersistenceMem implements Persistence<Entity> {

	@Override
	public Store<Entity> define(Class<Entity> clz, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Entity v) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Entity v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		for (EditableEntity e : changes) {
			e.store.apply(e);
		}
		changes.clear();
	}
	

	@Override
	public void clearChanges() {
		changes.clear();
	}

	@Override
	public void transaction(Callback<Entity> callback) {
		// TODO Auto-generated method stub
	}
	
	List<EditableEntity> changes  = new ArrayList<>(); 
	public void markChanged(EditableEntity v){
		changes.add(v);
	}
	
}

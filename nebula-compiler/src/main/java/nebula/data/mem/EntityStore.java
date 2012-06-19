package nebula.data.mem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.data.HasID;
import nebula.data.Store;

public class EntityStore implements Store<Entity> {
	PersistenceMem persistence;
	Map<String,Entity> quickIndex = new HashMap<String, Entity>();
	
	EntityStore(PersistenceMem persistence){
		this.persistence = persistence;
	}
	
	List<Entity> datas = new ArrayList<>();
	
	@Override
	public Entity createNew() {
		Entity newEntity = new Entity(this);
		EditableEntity agent = new EditableEntity(this, newEntity);
		return agent;
	}

	@Override
	public Entity load(String key) {
		return quickIndex.get(key);
	}

	@Override
	public List<Entity> findBy(String key) {
		return null;
	}

	@Override
	public void add(Entity v) {
		this.datas.add(v);
		this.quickIndex.put(v.getID(), v);
	}

	@Override
	public void remove(Entity v) {
	}

	@Override
	public void flush() {
		persistence.flush();
	}

	void markChanged(EditableEntity v){
		persistence.markChanged(v);
	}
	
	void apply(EditableEntity entity){
		
	}
	
	@Override
	public List<Entity> all() {
		// TODO Auto-generated method stub
		return null;
	}

}

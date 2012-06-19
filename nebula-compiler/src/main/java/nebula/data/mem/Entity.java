package nebula.data.mem;

import java.util.HashMap;
import java.util.Map;

import nebula.data.HasID;

public class Entity implements HasID {
	Map<String, Object> data;
	EntityStore store;

	Entity(EntityStore store) {
		this.store = store;
		this.data = new HashMap<String, Object>();
	}
	
	@Override
	public String getID() {
		return (String)this.get("ID");
	}
	
	public Object get(String name){
		return data.get(name);
	}
	
	public EditableEntity editable(){
		return new EditableEntity(store, this);
	}
}

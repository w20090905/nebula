package nebula.data.mem;

import java.util.HashMap;
import java.util.Map;

import nebula.data.Entity;
import nebula.data.HasID;

public class EntityImp implements HasID, Entity {
	Map<String, Object> data;
	EntityStore store;

	EntityImp(EntityStore store, Map<String, Object> data) {
		this.store = store;
		this.data = data;
	}

	EntityImp(EntityStore store) {
		this.store = store;
		this.data = new HashMap<String, Object>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nebula.data.mem.Entity#getID()
	 */
	@Override
	public String getID() {
		return (String) this.get("ID");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nebula.data.mem.Entity#get(java.lang.String)
	 */
	@Override
	public Object get(String name) {
		return data.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nebula.data.mem.Entity#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String name, Object v) {
		throw new UnsupportedOperationException("public void put(String name, Object v)");
	}

	@Override
	public EditableEntity editable() {
		return new EditableEntity(store, this);
	}

	@Override
	public boolean isDirty() {
		return false;
	}
}
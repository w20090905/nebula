package nebula.data.impl;

import java.util.HashMap;
import java.util.Map;

import nebula.data.DataStore;
import nebula.data.Entity;

public class EntityImp implements Entity {
	Map<String, Object> data;
	DataStore<Entity> store;
	long lastModified;

	EntityImp(DataStore<Entity> store, Map<String, Object> data) {
		this.store = store;
		this.data = data;
	}

	EntityImp(DataStore<Entity> store) {
		this(store, new HashMap<String, Object>());
	}

	@Override
	public String getID() {
		return (String) this.get(PRIMARY_KEY);
	}

	@Override
	public Object get(String name) {
		return data.get(name);
	}

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

	@Override
	public String toString() {
		return "EntityImp [" + data.toString() + "]";
	}

	@Override
	public void extend(Entity sub) {
		throw new UnsupportedOperationException("public void extend(Entity sub)");
	}

	@Override
	public boolean isTransient() {
		return this.store == null;
	}

	@Override
	public Entity getEntity(String name) {
		return (Entity) this.get(name);
	}

	public void setLastModified(long value) {
		this.lastModified = value;
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

}

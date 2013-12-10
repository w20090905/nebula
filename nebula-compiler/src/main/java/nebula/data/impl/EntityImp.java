package nebula.data.impl;

import java.util.HashMap;
import java.util.Map;

import nebula.data.Entity;

public class EntityImp implements Entity {
	Map<String, Object> data;
	DataStoreEx<Entity> store;
	long lastModified;

	EntityImp(DataStoreEx<Entity> store, Map<String, Object> data) {
		this.store = store;
		this.data = data;
	}

	EntityImp(DataStoreEx<Entity> store) {
		this(store, new HashMap<String, Object>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getID() {
		return (T)this.get(PRIMARY_KEY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String name) {
		return (T)data.get(name);
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

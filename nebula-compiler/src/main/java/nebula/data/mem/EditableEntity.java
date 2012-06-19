package nebula.data.mem;

import java.util.HashMap;
import java.util.Map;

class EditableEntity extends EntityImp {
	final Map<String, Object> newData;
	EntityImp source;

	boolean dirty = false;// <->pristine
	boolean valid = true;// <->invalid

	EditableEntity(EntityStore store) {
		super(store, null);
		this.source = null;
		this.newData = new HashMap<String, Object>();
	}

	EditableEntity(EntityStore store, EntityImp source) {
		super(store, source.data);
		this.source = source;
		this.newData = new HashMap<String, Object>();
	}

	void reset(EntityImp source){
		this.newData.clear();
		this.dirty=false;
		this.source = source;
		this.data = source.data;
	}
	
	@Override
	public Object get(String name) {
		return newData.containsKey(name) || data == null ? newData.get(name) : data.get(name);
	}

	public void put(String name, Object v) {
		newData.put(name, v);
		if (!this.dirty) {
			if (data != null) {
				store.markChanged(this);
			}
			this.dirty = true;
		}
	}

	public boolean isDirty() {
		return dirty;
	}
}

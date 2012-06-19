package nebula.data.mem;

import java.util.HashMap;
import java.util.Map;

public class EditableEntity extends Entity {
	Map<String, Object> data;
	Map<String, Object> newData;
	Entity source;

	boolean dirty = false;//<->pristine
	boolean valid = true;//<->invalid

	EditableEntity(EntityStore store, Entity source) {
		super(store);
		this.source = source;
		this.data = source.data;
		this.newData = new HashMap<String, Object>();
	}

	@Override
	public Object get(String name) {
		return newData.containsKey(name) ? newData.get(name) : data.get(name);
	}

	public void put(String name, Object v) {
		newData.put(name, v);
		if (!this.dirty) {
			store.markChanged(this);
			this.dirty = true;
		}
	}
}

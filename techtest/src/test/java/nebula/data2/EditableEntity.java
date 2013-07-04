package nebula.data2;

import java.util.Map;

import com.google.common.collect.Maps;

public class EditableEntity extends Entity implements Editable<Entity> {
	final Kind<Entity> kind;
	Map<String, Object> properties = Maps.newHashMap();

	public EditableEntity(Kind<Entity> kind) {
		this.kind = kind;
	}

	@Override
	public String getKey() {
		return (String) properties.get("name");
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void put(String name, Object value) {
		properties.put(name, value);
	}

	@Override
	public Kind<Entity> getKind() {
		return this.kind;
	}

	@Override
	Object get(String name) {
		return properties.get(name);
	}
}

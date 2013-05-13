package nebula.data;

public interface Entity extends Timable {
	Object getID();

	Object get(String name);

	Entity getEntity(String name);

	void put(String name, Object v);

	void extend(Entity sub);

	Entity editable();

	boolean isDirty();

	boolean isTransient();
}

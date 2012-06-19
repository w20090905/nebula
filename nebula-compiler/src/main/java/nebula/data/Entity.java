package nebula.data;

public interface Entity extends HasID {
	String getID();

	Object get(String name);

	void put(String name, Object v);

	Entity editable();

	boolean isDirty();
}
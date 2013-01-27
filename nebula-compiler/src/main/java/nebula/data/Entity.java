package nebula.data;


public interface Entity extends Identifiable {
	String getID();

	Object get(String name);

	void put(String name, Object v);
	
	void extend(Entity sub);

	Entity editable();

	boolean isDirty();
}
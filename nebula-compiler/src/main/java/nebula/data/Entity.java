package nebula.data;

public interface Entity extends Timable {
	public final String PRIMARY_KEY="PrimaryKey";
	<T> T getID();

	<T> T get(String name);

	Entity getEntity(String name);

	void put(String name, Object v);

	void extend(Entity sub);

	Entity editable();

	boolean isDirty();

	boolean isTransient();
}

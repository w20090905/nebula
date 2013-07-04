package nebula.data2;

public abstract class Entity {
	public abstract String getKey();

	long getId() {
		return 0;
	}

	abstract void put(String name, Object value);

	abstract Object get(String name);
}

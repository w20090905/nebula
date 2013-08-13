package nebula.lang;

public class NebulaClassLoader extends ClassLoader {

	static NebulaClassLoader instance;

	static final Class<?> defineClass(String name, byte[] b) {
		if (instance == null) instance = new NebulaClassLoader();
		return instance.defineClass(name, b, 0, b.length);
	}

	static ClassLoader getInstance() {
		if (instance == null) instance = new NebulaClassLoader();
		return instance;
	}
}
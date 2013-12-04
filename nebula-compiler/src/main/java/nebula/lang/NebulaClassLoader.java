package nebula.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NebulaClassLoader extends ClassLoader {
	static Log log = LogFactory.getLog(NebulaClassLoader.class);

	public NebulaClassLoader() {
		super(NebulaClassLoader.class.getClassLoader());
	}

	static NebulaClassLoader instance;

	public static final Class<?> defineClass(String name, byte[] b) {
		if(log.isDebugEnabled()){
			log.debug("defineClass [ " + name + " ]");
		}
		if (instance == null) instance = new NebulaClassLoader();
		return instance.defineClass(name, b, 0, b.length);
	}

	static ClassLoader getInstance() {
		if (instance == null) instance = new NebulaClassLoader();
		return instance;
	}

	public static void doResolveClass(Class<?> clzBroker) {
		if (instance == null) instance = new NebulaClassLoader();
		instance.resolveClass(clzBroker);
	}
}

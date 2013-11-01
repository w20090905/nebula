package nebula.simpletemplate;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.objectweb.asm.Type;

import com.google.common.collect.Maps;

public class CompilerContext {
	Map<String, Method> properties = null;

	public CompilerContext(Class<?> clz) {
		this.clz = clz;
		this.isMap = Map.class.isAssignableFrom(clz);
		this.isList = Collection.class.isAssignableFrom(clz);
		if (!isMap && !isList) {
			properties = Maps.newHashMap();
			for (Method m : clz.getMethods()) {
				if (m.getParameterTypes().length == 0) {
					String methodName = m.getName();
					if (methodName.startsWith("get") && methodName.length() > 3 && Character.isUpperCase(methodName.charAt(3))) {
						String name = methodName.substring(3, 4).toLowerCase();
						if (methodName.length() > 4) name += methodName.substring(4);
						properties.put(name, m);
					} else if (methodName.startsWith("has") && methodName.length() > 3 && Character.isUpperCase(methodName.charAt(3))
							&& Boolean.class.equals(m.getReturnType())) {
						String name = methodName.substring(3, 4).toLowerCase();
						if (methodName.length() > 4) name += methodName.substring(4);
						properties.put(name, m);

					} else if (methodName.startsWith("is") && methodName.length() > 2 && Character.isUpperCase(methodName.charAt(2))
							&& Boolean.class.equals(m.getReturnType())) {
						String name = methodName.substring(2, 3).toLowerCase();
						if (methodName.length() > 3) name += methodName.substring(3);
						properties.put(name, m);
					}
				}
			}
		}

	}

	public Method getProp(String name) {
		return this.properties.get(name);
	}

	public Class<?> getDataClass() {
		return clz;
	}

	private Class<?> clz;
	String getInternalName(){
		return Type.getType(clz).getInternalName();
	}
	boolean isMap;
	boolean isList;
}

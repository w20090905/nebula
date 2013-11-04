package nebula.simpletemplate;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CompilerContext {

	public CompilerContext(Object... argv) {
		this.argesByClassName = Maps.newHashMap();
		this.arges = Lists.newArrayList();

		for (Object obj : argv) {
			Arg arg = new Arg();

			if (obj != null) {
				Class<?> clz = obj.getClass();
				arg.clz = clz;

				arg.map = Map.class.isAssignableFrom(clz);
				arg.list = Collection.class.isAssignableFrom(clz);

				if (!arg.map && !arg.list) {
					Map<String, Method> properties = Maps.newHashMap();
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
									&& boolean.class.equals(m.getReturnType())) {
								String name = methodName.substring(2, 3).toLowerCase();
								if (methodName.length() > 3) name += methodName.substring(3);
								properties.put(name, m);
							}
						}
					}
					arg.properties = properties;
					this.argesByClassName.put(clz.getName(), arg);
					this.arges.add(arg);
				} else {
					this.arges.add(null);
				}
			}
		}
	}

	public Arg getArg(int index) {
		return arges.get(index);
	}

	public Method getProp(String className, String name) {
		return argesByClassName.get(className).properties.get(name);
	}

	class Arg {
		Class<?> clz;
		Map<String, Method> properties;
		boolean map;
		boolean list;
	}

	final private Map<String, Arg> argesByClassName;
	final private List<Arg> arges;

}

package nebula.simpletemplate;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CompilerContext {
	static ReentrantLock lock = new ReentrantLock();
	static Map<String, Arg> bytecodeWithKnownClass = ImmutableMap.of();

	static public Arg get(String className) {

		Arg builder = bytecodeWithKnownClass.get(className);

		if (builder != null) return builder;

		lock.lock();
		try {

			builder = bytecodeWithKnownClass.get(className);
			if (builder != null) return builder;
			builder = fromClass(Class.forName(className));

			ImmutableMap.Builder<String, Arg> mapBuilder = ImmutableMap.builder();
			bytecodeWithKnownClass = mapBuilder.putAll(bytecodeWithKnownClass).put(className, builder).build();

			return builder;

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	static public Arg get(Class<?> clz) {

		Arg builder = bytecodeWithKnownClass.get(clz.getName());

		if (builder != null) return builder;

		lock.lock();
		try {

			builder = bytecodeWithKnownClass.get(clz.getName());
			if (builder != null) return builder;
			builder = fromClass(clz);

			ImmutableMap.Builder<String, Arg> mapBuilder = ImmutableMap.builder();
			bytecodeWithKnownClass = mapBuilder.putAll(bytecodeWithKnownClass).put(clz.getName(), builder).build();

			return builder;

		} finally {
			lock.unlock();
		}
	}

	private static Arg fromClass(Class<?> clz) {
		Arg arg = new Arg();
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
		}
		return arg;
	}

	public CompilerContext(Object target) {
		this.arges = Lists.newArrayList();
		if (target != null) {
			Arg arg = get(target.getClass());
			this.arges.add(arg);
		} else {
			this.arges.add(null);
		}
	}

	public CompilerContext(Object target, Object... argv) {
		this.arges = Lists.newArrayList();
		if (target != null) {
			Arg arg = get(target.getClass());
			this.arges.add(arg);
		} else {
			this.arges.add(null);
		}

		for (Object o : argv) {
			if (o != null) {
				Arg arg = get(o.getClass());
				this.arges.add(arg);
			} else {
				this.arges.add(null);
			}
		}
	}

	public CompilerContext(Object... argv) {
		this.arges = Lists.newArrayList();
		for (Object o : argv) {
			if (o != null) {
				Arg arg = get(o.getClass());
				this.arges.add(arg);
			} else {
				this.arges.add(null);
			}
		}
	}

	public static Method getProp(String className, String name) {
		return get(className).properties.get(name);
	}

	public static Method getProp(Class<?> clz, String name) {
		return get(clz).properties.get(name);
	}

	public Arg getArg(int index) {
		return arges.get(index);
	}

	static class Arg {
		Class<?> clz;
		Map<String, Method> properties;
		boolean map;
		boolean list;
	}

	final List<Arg> arges;

}

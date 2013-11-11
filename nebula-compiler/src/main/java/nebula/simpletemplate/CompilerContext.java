package nebula.simpletemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CompilerContext {
	static class Arg {
		Class<?> clz;
		Map<String, Field> fields;
		boolean list;
		boolean map;
		Map<String, Method> properties;
	}

	static Map<String, Arg> bytecodeWithKnownClass = ImmutableMap.of();

	private static int DEFAULT_LOCALS = getMethod(Action.class, "exec").getParameterTypes().length + 1;

	static ReentrantLock lock = new ReentrantLock();

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
							&& Boolean.TYPE == m.getReturnType()) {
						String name = methodName.substring(3, 4).toLowerCase();
						if (methodName.length() > 4) name += methodName.substring(4);
						properties.put(name, m);

					} else if (methodName.startsWith("is") && methodName.length() > 2 && Character.isUpperCase(methodName.charAt(2))
							&& Boolean.TYPE == m.getReturnType()) {
						String name = methodName.substring(2, 3).toLowerCase();
						if (methodName.length() > 3) name += methodName.substring(3);
						properties.put(name, m);
					}
				}

			}

			Map<String, Field> fields = Maps.newHashMap();
			for (Field m : clz.getFields()) {
				if (Modifier.isPublic(m.getModifiers())) {
					fields.put(m.getName(), m);
				}
			}
			arg.properties = properties;
			arg.fields = fields;
		}
		return arg;
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

	public static Field getField(Class<?> clz, String name) {
		return get(clz).fields.get(name);
	}

	static Method getMethod(Class<?> clz, String name) {
		for (Method method : clz.getMethods()) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

	public static Method getProp(Class<?> clz, String name) {
		return get(clz).properties.get(name);
	}

	public static Method getProp(String className, String name) {
		return get(className).properties.get(name);
	}

	final List<Arg> arges;
	final TemplateImpl impl;
	int locals;
	public CompilerContext(TemplateImpl impl,List<Class<?>> clzes) {
		this.impl = impl;
		this.arges = Lists.newArrayList();
		for (Class<?> clz : clzes) {
			if (clz != null) {
				Arg arg = get(clz);
				this.arges.add(arg);
			} else {
				this.arges.add(null);
			}
		}
		this.locals = DEFAULT_LOCALS;
	}

	public CompilerContext(TemplateImpl impl,Object target) {
		this.impl = impl;
		this.arges = Lists.newArrayList();
		if (target != null) {
			Arg arg = get(target.getClass());
			this.arges.add(arg);
		} else {
			this.arges.add(null);
		}
		this.locals = DEFAULT_LOCALS;
	}

	public CompilerContext(TemplateImpl impl,Object... argv) {
		this.impl = impl;
		this.arges = Lists.newArrayList();
		for (Object o : argv) {
			if (o != null) {
				Arg arg = get(o.getClass());
				this.arges.add(arg);
			} else {
				this.arges.add(null);
			}
		}
		this.locals = DEFAULT_LOCALS;
	}

	public CompilerContext(TemplateImpl impl,Object target, Object... argv) {
		this.impl = impl;
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
		this.locals = DEFAULT_LOCALS;
	}

	public Arg getArg(int index) {
		return arges.get(index);
	}

}

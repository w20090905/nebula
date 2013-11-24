package nebula.lang;

import java.util.List;

import nebula.data.DataRepos;
import nebula.data.Entity;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class NebulaNative {
	public static <V> List<V> filter(List<V> list, Clause<V> func, Object... params) {
		List<V> out = Lists.newArrayList();
		for (V v : list) {
			if (func.apply(null, null, v, params)) out.add(v);
		}
		return out;
	}

	public static <V> List<V> filter(List<V> list, Predicate<V> func) {
		List<V> out = Lists.newArrayList();
		for (V v : list) {
			if (func.apply(v)) out.add(v);
		}
		return out;
	}

	// TODO need refact
	public static <V> List<V> filter(List<V> list, Range... ranges) {
		List<V> out = Lists.newArrayList();
		for (Range range : ranges) {
			out = range.filter(list, out);
		}
		return out;
	}

	public static void execMethod(RuntimeContext context, DataRepos dataRepos, Entity entity, Type type, String methodName) {
		if (type.getSuperType() != null) execMethod(context, dataRepos, entity, type.getSuperType(), methodName);
		// TODO
		Field sysInitAction = type.getActionByName(methodName);
		if (sysInitAction != null) sysInitAction.actionAsm.exec(context, dataRepos, entity);
	}

	public static void ctor(RuntimeContext context, DataRepos dataRepos, Entity entity, Type type) {
		execMethod(context, dataRepos, entity, type, Type.CTOR);
	}

	public static void onSave(RuntimeContext context, DataRepos dataRepos, Entity entity, Type type) {
		execMethod(context, dataRepos, entity, type, Type.ONSAVE);
	}

	public static void onLoad(RuntimeContext context, DataRepos dataRepos, Entity entity, Type type) {
		execMethod(context, dataRepos, entity, type, Type.ONLOAD);
	}
}

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
			if (func.apply(v, params)) out.add(v);
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

	public static void ctor(Entity entity, Type type, DataRepos dataRepos) {
		if (type.superType != null) ctor(entity, type.superType, dataRepos);

		Field sysInitAction = type.getActionByName(Type.CTOR);
		if (sysInitAction == null) return;
		sysInitAction.getCode().exec(entity, dataRepos);
	}

	public static void onSave(Entity entity, Type type, DataRepos dataRepos) {
		if (type.superType != null) onSave(entity, type.superType, dataRepos);

		Field sysInitAction = type.getActionByName(Type.ONSAVE);
		if (sysInitAction == null) return;
		sysInitAction.getCode().exec(entity, dataRepos);
	}

	public static void onLoad(Entity entity, Type type, DataRepos dataRepos) {
		if (type.superType != null) onLoad(entity, type.superType, dataRepos);

		Field sysInitAction = type.getActionByName(Type.ONLOAD);
		if (sysInitAction == null) return;
		sysInitAction.getCode().exec(entity, dataRepos);
	}
}

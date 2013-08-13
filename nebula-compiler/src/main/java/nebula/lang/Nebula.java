package nebula.lang;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Nebula {
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
}

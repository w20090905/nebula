package nebula.lang;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

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

	public static <V> List<V> filter(List<V> list, Range<Integer>... ranges) {
		List<V> out = Lists.newArrayList();
		for (int i = 0; i < list.size(); i++) {
			for (Range<Integer> range : ranges) {
				if (range.contains(i)) {
					out.add(list.get(i));
					break;
				}
			}
		}
		return out;
	}
}

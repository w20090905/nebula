package nebula.data;

import com.google.common.base.Function;

public interface KeyMaker<K> extends Function<K, String> {
	String apply(K v);
}

package nebula.data;

import com.google.common.base.Function;

@Deprecated
public interface ClassifiableFilter<V  extends Timable> extends Filter<V> {
	<K> Classificator<K, V> classify(Function<V, K> indexFunction);
}
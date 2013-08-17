package nebula.lang;

import nebula.data.DataRepos;


public interface Clause<V> {
	boolean apply(RuntimeContext context, DataRepos repos, V entity, Object... params);
}

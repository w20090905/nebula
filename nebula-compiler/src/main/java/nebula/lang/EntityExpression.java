package nebula.lang;

import nebula.data.DataRepos;
import nebula.data.Entity;

public interface EntityExpression {
	Object eval(RuntimeContext context, DataRepos repos, Entity entity);
}

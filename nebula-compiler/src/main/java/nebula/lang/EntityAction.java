package nebula.lang;

import nebula.data.DataRepos;
import nebula.data.Entity;

public interface EntityAction {
	void exec(RuntimeContext context, DataRepos repos, Entity entity);
}

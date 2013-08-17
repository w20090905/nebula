package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.lang.Clause;
import nebula.lang.RuntimeContext;

public class EntityClauseSample implements Clause<Entity> {

	@Override
	public boolean apply(RuntimeContext context, DataRepos repos, Entity entity, Object... params) {
		long Age = 10;
		return Age > 10L && Age < 40L;
	}
}

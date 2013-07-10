package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.lang.EntityAction;

public class EntityActionSample implements EntityAction {

	@Override
	public void exec(Entity entity, DataRepos repos) {
		entity.put("Age", 10);
		return;		
	}
}

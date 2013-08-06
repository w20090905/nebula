package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.EntityAction;

public class EntityActionSample implements EntityAction {

	@Override
	public void exec(Entity entity, DataRepos paramDataRepos) {
		entity.put("Height", Integer.valueOf(((Integer) ((DataStore<Entity>) paramDataRepos.define(String.class, Entity.class, "Person").get()).listAll()
				.get(0).get("Age")).intValue()));
		return;
	}
}

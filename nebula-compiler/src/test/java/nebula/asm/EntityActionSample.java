package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.EntityAction;
import nebula.lang.RuntimeContext;

public class EntityActionSample implements EntityAction {

	@Override
	public void exec(RuntimeContext context, DataRepos paramDataRepos, Entity paramEntity) {
		paramEntity.put("Height", Integer.valueOf(((Integer) ((DataStore<Entity>) paramDataRepos.define(String.class, Entity.class, "Person")).listAll().get(0)
				.get("Age")).intValue()));
		long l2 = 10000;

		paramEntity.put("Height", Long.valueOf(((Long) ((Entity) (((DataStore<Entity>) paramDataRepos.define(String.class, Entity.class, "Person")).listAll())
				.get((int) l2)).get("Age")).longValue() + 10L));
		return;
	}
}

package nebula.asm;

import java.util.List;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.EntityAction;

public class EntityActionSample implements EntityAction {

	@Override
	public void exec(Entity paramEntity, DataRepos paramDataRepos) {
		paramEntity.put("Height", Integer.valueOf(((Integer) ((DataStore<Entity>) paramDataRepos.define(String.class, Entity.class, "Person").get()).listAll()
				.get(0).get("Age")).intValue()));
		long l1 = 100;
		long l2 = 10000;
		boolean ss = l1>l2;
		
		
	    paramEntity.put("Height", Long.valueOf(((Long)((Entity)(((DataStore<Entity>)paramDataRepos.define(String.class, Entity.class, "Person").get()).listAll()).get((int)l2)).get("Age")).longValue() + 10L));
		return;
	}
}

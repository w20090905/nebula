package nebula.asm;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.lang.EntityAction;

public class EntityActionSample implements EntityAction {

	@Override
	public void exec(Entity entity, DataRepos repos) {
		entity.put("Age", 10);
		

		DateTime dt = new DateTime(1000L);
		entity.put("dd", dt);
		return;		
	}
}

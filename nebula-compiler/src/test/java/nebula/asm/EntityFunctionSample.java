package nebula.asm;

import nebula.data.Entity;
import nebula.lang.Clause;

public class EntityFunctionSample implements Clause<Entity> {

	@Override
	public boolean apply(Entity entity, Object... params) {
		long Age = 10;
		return Age > 10L && Age < 40L;
	}
}

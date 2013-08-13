package nebula.asm;

import nebula.data.Entity;

import com.google.common.base.Function;

public class EntityFunctionSample implements Function<Entity, Boolean> {

	@Override
	public Boolean apply(Entity input) {
		return false;
	}
}

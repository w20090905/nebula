package nebula.asm;

import nebula.data.Entity;
import nebula.lang.EntityExpression;

public class ASMSampleBoolean implements EntityExpression<Boolean> {
	int i = 100;
	int j = 100;

	@Override
	public Boolean eval(Entity entity) {
		return i>j;
	}
}

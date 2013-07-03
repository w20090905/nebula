package nebula.asm;

import nebula.data.Entity;
import nebula.lang.EntityExpression;

public class ASMSample implements EntityExpression<Integer> {
	int i = 100;
	int j = 100;

	@Override
	public Integer eval(Entity entity) {
		return i * j;
	}
}

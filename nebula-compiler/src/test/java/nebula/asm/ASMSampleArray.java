package nebula.asm;

import nebula.data.Entity;
import nebula.lang.EntityExpression;

public class ASMSampleArray implements EntityExpression{
	int i = 100;
	int j = 100;

	@Override
	public Boolean eval(Entity entity) {
		int[] aa  = new int[10];
		aa[0] = 10;
		int i = aa[2];
		return i>j;
	}
}

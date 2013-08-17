package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.lang.EntityExpression;
import nebula.lang.RuntimeContext;

public class ASMSampleArray implements EntityExpression{
	int i = 100;
	int j = 100;

	@Override
	public Object eval(RuntimeContext context, DataRepos repos, Entity entity) {
		int[] aa  = new int[10];
		aa[0] = 10;
		int i = aa[2];
		return i>j;
	}
}

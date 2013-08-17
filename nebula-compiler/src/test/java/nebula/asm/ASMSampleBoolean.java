package nebula.asm;

import nebula.data.DataRepos;
import nebula.data.Entity;
import nebula.lang.EntityExpression;
import nebula.lang.RuntimeContext;

public class ASMSampleBoolean implements EntityExpression{
	int i = 100;
	int j = 100;

	@Override
	public Object eval(RuntimeContext context, DataRepos repos, Entity entity) {
		return i>j;
	}
}

package nebula.lang;

import nebula.data.Entity;

public class ASMSample  implements EntityExpression{

	@Override
	public int eval(Entity entity) {
		return ((Integer)entity.get("age")) + 1;
	}
	

}

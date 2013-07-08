package nebula.asm;

import java.math.BigDecimal;

import nebula.data.Entity;
import nebula.lang.EntityExpression;

public class ASMSampleBigDecimal implements EntityExpression{
	int i = 100;
	int j = 100;

	@Override
	public BigDecimal eval(Entity entity) {
		return new BigDecimal("1.3");
	}
}

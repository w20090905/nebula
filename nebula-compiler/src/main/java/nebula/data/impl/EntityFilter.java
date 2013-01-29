package nebula.data.impl;

import nebula.Filter;
import nebula.data.Entity;
import nebula.expr.LogicExp;
import nebula.expr.QuickExprBuilder;

public class EntityFilter extends QuickExprBuilder implements Filter<Entity> {

	private Entity v;

	@Override
	protected String getStringByName(String name) {
		return (String) v.get(name);
	}

	@Override
	protected Integer getIntegerByName(String name) {
		return (Integer) v.get(name);
	}

	public EntityFilter() {
		
	}
	
	@Override
	public boolean match(Entity v) {
		this.v = v;
		boolean result = ((LogicExp)exp).exec();
		v = null;
		return result;
	}

}

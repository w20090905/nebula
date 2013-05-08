package nebula.data.impl;

import nebula.data.Entity;
import nebula.expr.LogicExp;
import nebula.expr.QuickExprBuilder;

import com.google.common.base.Predicate;

public class EntityFilter extends QuickExprBuilder implements Predicate<Entity> {

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
	public boolean apply(Entity v) {
		this.v = v;
		boolean result = ((LogicExp)exp).exec();
		v = null;
		return result;
	}

}

package http.engine;

import java.util.Map;

import nebula.Filter;
import nebula.data.Entity;
import nebula.data.impl.EntityFilter;
import nebula.expr.LogicExp;
import nebula.lang.Type;

import org.simpleframework.http.Query;

public class EntityFilterBuilder {

	public Filter<Entity> buildFrom(Query query, Type type) {
		EntityFilter builder = new EntityFilter();
		LogicExp exp = builder.Nop();

		for (Map.Entry<String, String> entry : query.entrySet()) {
			exp = exp.and(builder.C(entry.getValue()).eqF(entry.getKey()));
		}
		exp.finish();

		return builder;
	}
}

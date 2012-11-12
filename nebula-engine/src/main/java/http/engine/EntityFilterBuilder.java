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
			String[] ks = entry.getKey().split(":");
			String key = ks[0];
			if(ks.length==1){
				exp = exp.and(builder.C(entry.getValue()).eqF(key));
			}else{
				String op = ks[1];
				if("from".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).leF(key));					
				}else if("to".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).geF(key));				
				}else if("gt".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).ltF(key));				
				}else if("ge".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).leF(key));				
				}else if("lt".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).gtF(key));				
				}else if("le".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).geF(key));			
				}else if("ne".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).neF(key));			
				}else if("eq".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).eqF(key));		
				}else if("has".equals(op)){
					exp = exp.and(builder.C(entry.getValue()).inF(key));				
				}else{
					throw new UnsupportedOperationException(op);
				}
			}
			
		}
		exp.finish();

		return builder;
	}
}

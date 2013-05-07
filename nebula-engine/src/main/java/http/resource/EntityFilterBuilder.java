package http.resource;

import java.util.Map;

import nebula.data.Entity;
import nebula.data.DataFilter;
import nebula.data.impl.EntityFilter;
import nebula.expr.LogicExp;
import nebula.lang.Type;

public class EntityFilterBuilder {

	public DataFilter<Entity> buildFrom(Map<String, String[]> query, Type type) {
		EntityFilter builder = new EntityFilter();
		LogicExp exp = builder.Nop();

		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			String[] ks = entry.getKey().split(":");
			String key = ks[0];
			if(ks.length==1){
				exp = exp.and(builder.C(entry.getValue()[0]).eqF(key));
			}else{
				String op = ks[1];
				if("from".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).leF(key));					
				}else if("to".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).geF(key));				
				}else if("gt".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).ltF(key));				
				}else if("ge".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).leF(key));				
				}else if("lt".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).gtF(key));				
				}else if("le".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).geF(key));			
				}else if("ne".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).neF(key));			
				}else if("eq".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).eqF(key));		
				}else if("has".equals(op)){
					exp = exp.and(builder.C(entry.getValue()[0]).inF(key));				
				}else{
					throw new UnsupportedOperationException(op);
				}
			}
			
		}
		exp.finish();

		return builder;
	}
}

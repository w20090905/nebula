package http.engine;

import java.util.HashMap;
import java.util.Map;

import nebula.Filter;
import nebula.expr.LogicExp;
import nebula.expr.QuickExprBilder;
import nebula.lang.Type;

import org.simpleframework.http.Query;

public class TypeFilterBuilder {
	
	
	public TypeFilterBuilder() {
		
	}

	public Filter<Type> buildFrom(Query query, Type type) {
		TypeFilter builder = new TypeFilter();
		LogicExp exp = builder.Nop();

		for (Map.Entry<String, String> entry : query.entrySet()) {
			exp = exp.and(builder.C(entry.getValue()).eqF(entry.getKey()));
		}
		exp.finish();

		return builder;
	}
	
	
	class TypeFilter extends QuickExprBilder  implements Filter<Type>{
		final Map<String, GetStringValue<Type>> strings;
		private Type v;
		
		public TypeFilter() {
			strings = new HashMap<String, GetStringValue<Type>>();
			strings.put("name", new GetStringValue<Type>() {			
				@Override
				public String getByName(Type obj) {
					// TODO Auto-generated method stub
					return obj.getName();
				}
			});

			strings.put("code", new GetStringValue<Type>() {			
				@Override
				public String getByName(Type obj) {
					return obj.getCode();
				}
			});
			
			strings.put("rawType", new GetStringValue<Type>() {			
				@Override
				public String getByName(Type obj) {
					return obj.getRawType();
				}
			});
			
			strings.put("standalone", new GetStringValue<Type>() {			
				@Override
				public String getByName(Type obj) {
					return obj.getStandalone().toString();
				}
			});
		}
		

		@Override
		protected String getStringByName(String name) {
			return strings.get(name).getByName(v);
		}

		@Override
		protected Integer getIntegerByName(String name) {
			return null;
		}
		
	
		@Override
		public boolean match(Type v) {
			this.v = v;
			boolean result = ((LogicExp)exp).exec();
			v = null;
			return result;
		}
		
	}
	
	interface GetStringValue<T>{
		String getByName(T obj);
	}
}

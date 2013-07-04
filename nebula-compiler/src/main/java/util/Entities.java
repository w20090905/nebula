package util;

import nebula.data.Classificator;
import nebula.data.Entity;
import nebula.data.impl.DataClassificator;

import com.google.common.base.Function;

public class Entities {
	
//	@Deprecated
//	public static ClassifiableFilter<Entity> filter(Predicate<Entity> filterFunction) {
//		return new DataFilter<Entity>(filterFunction);
//	}
	
	@Deprecated
	public static <K> Classificator<K, Entity> classify(Function<Entity, K> indexerFunction) {
		return new DataClassificator<K, Entity>(indexerFunction);
	}

	public static Classificator<String, Entity> classify(final String name) {
		return new DataClassificator<String, Entity>( new Function<Entity, String>() {
			@Override
			public String apply(Entity from) {
				return  String.valueOf(from.get(name));
			}
		});
	}
}

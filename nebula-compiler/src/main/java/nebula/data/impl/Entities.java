package nebula.data.impl;

import nebula.data.ClassifiableFilter;
import nebula.data.Classificator;
import nebula.data.Entity;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class Entities {
	public static ClassifiableFilter<Entity> filter(Predicate<Entity> filterFunction) {
		return new DataFilter<Entity>(filterFunction);
	}
	
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

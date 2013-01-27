package nebula.frame;

import java.util.Map;

import nebula.IDAdapter;

public interface AutoIdentifiable<E> extends IDAdapter<E> {
	public void set(Map<String, E> map, E data);
}
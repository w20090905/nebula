package nebula.frame;

import java.util.Map;

public interface AutoIdentifiable<E> extends Identifiable<E> {
	public void set(Map<String, E> map, E data);
}
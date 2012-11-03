package nebula;

public interface Filter<E> {
	boolean match(E v);
}

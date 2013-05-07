package nebula.data;

public interface DataFilter<E> {
	boolean match(E v);
}

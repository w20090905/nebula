package nebula.lang;


public interface Clause<V> {
	boolean apply(V entity, Object... params);
}

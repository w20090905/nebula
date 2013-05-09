package nebula.data;

public interface DataListener<V> {
	public void add(V v);
	public void update(V oldData, V newData);
	public void remove(V v);
}

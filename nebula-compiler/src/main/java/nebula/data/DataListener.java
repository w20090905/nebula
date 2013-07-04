package nebula.data;

public interface DataListener<V> {
	public void onAdd(V v);
	public void onUpdate(V oldData, V newData);
	public void onRemove(V v);
}

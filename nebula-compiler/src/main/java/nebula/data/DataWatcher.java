package nebula.data;

public interface DataWatcher<T> {
	boolean onUpdate(T newData, T oldData);
}

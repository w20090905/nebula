package nebula.data;

public interface HolderListener<T> {
	boolean arrive(T newData, T oldData);
}

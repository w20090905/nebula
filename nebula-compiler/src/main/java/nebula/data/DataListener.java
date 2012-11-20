package nebula.data;

public interface DataListener<T> {
	boolean arrive(T newData, T oldData);
}

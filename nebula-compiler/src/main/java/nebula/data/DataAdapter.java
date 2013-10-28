package nebula.data;

public interface DataAdapter<T,R> {
	R watch(T newData,T oldData);
}

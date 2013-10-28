package nebula.data2;

public interface DataAdapter<T,R> {
	R watch(T newData,T oldData);
}

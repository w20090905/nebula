package nebula.data;

public interface BrokerListener<T> {
	boolean arrive(T newData, T oldData);
}

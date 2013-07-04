package nebula.data;

public interface Broker<T> extends Timable {
	void addWatcher(DataWatcher<T> listener);

	T get();
}

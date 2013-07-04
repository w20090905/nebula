package nebula.data;

public interface Broker<T> extends Timable {
	void addListener(DataWatcher<T> listener);
	T get();
}

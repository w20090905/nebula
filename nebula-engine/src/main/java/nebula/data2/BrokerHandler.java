package nebula.data2;

import nebula.data.DataWatcher;

public interface BrokerHandler<T> {
	void addWatcher(DataWatcher<T> listener);
	void setNewValue(T newValue);
	T getActualValue();
	T get();
}

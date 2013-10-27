package nebula.data2;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.DataWatcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Preconditions;

public abstract class Broker<T> {
	protected Broker() {
	}

	static Log log = LogFactory.getLog(Broker.class);

	protected List<DataWatcher<T>> listeners;
	protected T actualValue;

	void addWatcher(DataWatcher<T> listener) {
		if (listeners == null) {
			listeners = new CopyOnWriteArrayList<DataWatcher<T>>();
		}
		listeners.add(listener);
		listener.onUpdate(actualValue, null);
	}

	public void setNewValue(T newValue) {
		boolean hasLostReference = false;
		for (DataWatcher<T> listener : listeners) {
			if (listener != null) {
				boolean result = listener.onUpdate(newValue, this.actualValue);
				if (result) break;
			} else {
				hasLostReference = true;
			}
		}
		if (hasLostReference) {
			for (int i = listeners.size() - 1; i >= 0; i--) {
				DataWatcher<T> listener = listeners.get(i);
				if (listener == null) {
					listeners.remove(i);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <R, T> R watch(T watch, final Watcher<R, T> listener) {
		Method m = listener.getClass().getMethods()[0];
		Preconditions.checkArgument("watch".equals(m.getName()));
		final Broker<R> r = agent(m.getReturnType().getClass());

		Preconditions.checkState(watch instanceof Broker);
		Broker<T> watchTo = (Broker<T>) watch;
		watchTo.addWatcher(new DataWatcher<T>() {
			@Override
			public boolean onUpdate(T newData, T oldData) {
				R newValue = listener.watch(newData, oldData);
				r.setNewValue(newValue);
				return false;
			}
		});
		return (R) r;
	}

	private static BrokerBuilder brokerBuilder = new BrokerBuilder();

	public static <R> Broker<R> agent(Class<?> clz) {
		return brokerBuilder.builder(clz);
	}

	public T getActualValue() {
		return this.actualValue;
	}

	public abstract T get();
}

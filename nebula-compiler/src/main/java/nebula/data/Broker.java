package nebula.data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.DataWatcher;
import nebula.data.impl.BrokerBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Preconditions;

public abstract class Broker<T> implements BrokerHandler<T> {
	protected Broker() {
	}

	final static Log log = LogFactory.getLog(Broker.class);
	protected List<DataWatcher<T>> listeners;
	protected T actualValue;

	public void addWatcher(DataWatcher<T> listener) {
		if (listeners == null) {
			listeners = new CopyOnWriteArrayList<DataWatcher<T>>();
		}
		listeners.add(listener);
		listener.onUpdate(actualValue, null);
	}

	@Override
	public void setNewValue(T newValue) {
		boolean hasLostReference = false;
		T oldValue = this.actualValue;
		this.actualValue = newValue;

		if (listeners == null) return;
		for (DataWatcher<T> listener : listeners) {
			if (listener != null) {
				boolean result = listener.onUpdate(this.actualValue, oldValue);
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

	@Override
	public T getActualValue() {
		return this.actualValue;
	}

	@Override
	public abstract T get();

	@SuppressWarnings("unchecked")
	final public static <T> BrokerHandler<T> brokerOf(T value) {
		return (BrokerHandler<T>) value;
	}

	final public static <T> BrokerHandler<T> broke(Class<T> clz, T value) {
		BrokerHandler<T> b = brokerBuilder.builder(clz);
		b.setNewValue(value);
		return b;
	}

	final public static <T> T update(T value, T newvalue) {
		if (!(value instanceof BrokerHandler)) throw new RuntimeException("Should be brokerhandle");
		@SuppressWarnings("unchecked")
		BrokerHandler<T> bt = (BrokerHandler<T>) value;
		bt.setNewValue(newvalue);
		return bt.get();
	}

	@SuppressWarnings("unchecked")
	final public static <R, T> R watch(T watch, final DataAdapter<T, R> listener) {
		Method m = listener.getClass().getMethods()[0];
		Preconditions.checkArgument("watch".equals(m.getName()));
		final BrokerHandler<R> r = brokerBuilder.builder(m.getReturnType());

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

	private final static BrokerBuilder brokerBuilder = new BrokerBuilder();
}

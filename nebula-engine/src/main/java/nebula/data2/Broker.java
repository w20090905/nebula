package nebula.data2;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.DataWatcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Preconditions;

public abstract class Broker<T> implements BrokerHandler<T> {
	protected Broker() {
	}

	static Log log = LogFactory.getLog(Broker.class);

	protected List<DataWatcher<T>> listeners;
	protected T actualValue;

	public void addWatcher(DataWatcher<T> listener) {
		if (listeners == null) {
			listeners = new CopyOnWriteArrayList<DataWatcher<T>>();
		}
		listeners.add(listener);
		listener.onUpdate(actualValue, null);
	}

	/* (non-Javadoc)
	 * @see nebula.data2.BrokerHandler#setNewValue(T)
	 */
	@Override
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
	
	public static <T> T of(T value){
		BrokerHandler<T> b = agent(value.getClass());
		b.setNewValue(value);
		return b.get();
	}
	
	public static <T> T update(T value, T newvalue){
		if(!(value instanceof BrokerHandler))throw new RuntimeException("Should be brokerhandle"); 
		@SuppressWarnings("unchecked")
		BrokerHandler<T> bt = (BrokerHandler<T>)value;
		bt.setNewValue(newvalue);
		return bt.get();
	}

	@SuppressWarnings("unchecked")
	public static <R, T> R watch(T watch, final Watcher<T,R> listener) {
		Method m = listener.getClass().getMethods()[0];
		Preconditions.checkArgument("watch".equals(m.getName()));
		final BrokerHandler<R> r = agent(m.getReturnType().getClass());

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

	public static <R> BrokerHandler<R> agent(Class<?> clz) {
		return brokerBuilder.builder(clz);
	}

	/* (non-Javadoc)
	 * @see nebula.data2.BrokerHandler#getActualValue()
	 */
	@Override
	public T getActualValue() {
		return this.actualValue;
	}

	/* (non-Javadoc)
	 * @see nebula.data2.BrokerHandler#get()
	 */
	@Override
	public abstract T get();
}

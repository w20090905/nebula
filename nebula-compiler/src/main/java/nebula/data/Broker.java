package nebula.data;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Broker<T> {

	public static <T> Broker<T> of(T data) {
		return new Broker<T>(data);
	}

	List<WeakReference<BrokerListener<T>>> listeners;
	protected T curData;

	public Broker(T newData) {
		listeners = new CopyOnWriteArrayList<WeakReference<BrokerListener<T>>>();
		this.curData = newData;
	}

	public void addListener(BrokerListener<T> listener) {
		listeners.add(new WeakReference<BrokerListener<T>>(listener));
	}

	public void update(T newData) {
		boolean hasLostReference = false;
		for (WeakReference<BrokerListener<T>> weakReference : listeners) {
			BrokerListener<T> listener = weakReference.get();
			if (listener != null) {
				boolean result = listener.arrive(newData, this.curData);
				if (!result) break;
			} else {
				hasLostReference = true;
			}
		}
		if (hasLostReference) {
			for (int i = listeners.size() - 1; i >= 0; i--) {
				BrokerListener<T> listener = listeners.get(i).get();
				if (listener == null) {
					listeners.remove(i);
				}
			}
		}
		this.curData = newData;
	}

	public T get() {
		return this.curData;
	}
}

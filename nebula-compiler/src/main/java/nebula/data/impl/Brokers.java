package nebula.data.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.DataWatcher;

class Brokers {

	public static <T> BrokerEx<T> of(T data) {
		return new BrokerImp<T>(data);
	}

	static class BrokerImp<T> implements  BrokerEx<T> {

		List<DataWatcher<T>> listeners;
		protected T lastData;

		public BrokerImp(T newData) {
			listeners = new CopyOnWriteArrayList<DataWatcher<T>>();
			this.lastData = newData;
		}

		public void addWatcher(DataWatcher<T> listener) {
			listeners.add(listener);
		}

		public void put(T newData) {
			boolean hasLostReference = false;
			for (DataWatcher<T> listener : listeners) {
				if (listener != null) {
					boolean result = listener.onUpdate(newData, this.lastData);
					if (!result) break;
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
			this.lastData = newData;
		}

		public T get() {
			return this.lastData;
		}

		@Override
		public long getLastModified() {
			return System.currentTimeMillis();
		}

	}
}

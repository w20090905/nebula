package nebula.data;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Holder<T> {

	public static <T> Holder<T> of(T data) {
		return new Holder<T>(data);
	}

	List<WeakReference<HolderListener<T>>> listeners;
	protected T curData;

	public Holder(T newData) {
		listeners = new CopyOnWriteArrayList<WeakReference<HolderListener<T>>>();
		this.curData = newData;
	}

	public void addListener(HolderListener<T> listener) {
		listeners.add(new WeakReference<HolderListener<T>>(listener));
	}

	public void update(T newData) {
		boolean hasLostReference = false;
		for (WeakReference<HolderListener<T>> weakReference : listeners) {
			HolderListener<T> listener = weakReference.get();
			if (listener != null) {
				boolean result = listener.arrive(newData, this.curData);
				if (!result) break;
			} else {
				hasLostReference = true;
			}
		}
		if (hasLostReference) {
			for (int i = listeners.size() - 1; i >= 0; i--) {
				HolderListener<T> listener = listeners.get(i).get();
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

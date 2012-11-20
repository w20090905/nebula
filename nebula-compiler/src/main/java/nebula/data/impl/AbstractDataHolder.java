package nebula.data.impl;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.DataHolder;
import nebula.data.DataListener;

public class AbstractDataHolder<T> implements DataHolder<T> {
	List<WeakReference<DataListener<T>>> listeners;
	T lastData;
	T curData;

	public AbstractDataHolder(T newData) {
		listeners = new CopyOnWriteArrayList<>();
		this.curData = newData;
	}

	@Override
	public void addListener(DataListener<T> listener) {
		listeners.add(new WeakReference<DataListener<T>>(listener));
	}

	@Override
	public void set(T newData, T oldData) {
		boolean hasLostReference = false;
		for (WeakReference<DataListener<T>> weakReference : listeners) {
			DataListener<T> listener = weakReference.get();
			if(listener!=null){
				boolean result = listener.arrive(newData, oldData);
				if (!result) break;				
			}else{
				hasLostReference=true;
			}
		}
		if(hasLostReference){
			for (int i=listeners.size()-1; i>=0; i--) {
				DataListener<T> listener = listeners.get(i).get();
				if(listener==null){
					listeners.remove(i);
				}
			}
		}
		this.curData = newData;
		this.lastData = oldData;
	}

	@Override
	public T get() {
		return this.curData;
	}

}

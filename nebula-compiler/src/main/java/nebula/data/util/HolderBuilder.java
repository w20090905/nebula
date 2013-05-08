package nebula.data.util;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.Holder;
import nebula.data.HolderListener;

public class HolderBuilder<T> implements Holder<T> {
	
	public static <T> Holder<T> of(T data){
		return new HolderBuilder<T>(data);
	}
	
	List<WeakReference<HolderListener<T>>> listeners;
	T lastData;
	T curData;

	private HolderBuilder(T newData) {
		listeners = new CopyOnWriteArrayList<WeakReference<HolderListener<T>>>();
		this.curData = newData;
	}

	@Override
	public void addListener(HolderListener<T> listener) {
		listeners.add(new WeakReference<HolderListener<T>>(listener));
	}

	@Override
	public void update(T oldData, T newData) {
		boolean hasLostReference = false;
		for (WeakReference<HolderListener<T>> weakReference : listeners) {
			HolderListener<T> listener = weakReference.get();
			if(listener!=null){
				boolean result = listener.arrive(newData, oldData);
				if (!result) break;				
			}else{
				hasLostReference=true;
			}
		}
		if(hasLostReference){
			for (int i=listeners.size()-1; i>=0; i--) {
				HolderListener<T> listener = listeners.get(i).get();
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

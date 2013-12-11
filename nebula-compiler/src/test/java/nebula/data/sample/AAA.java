package nebula.data.sample;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.BrokerHandler;
import nebula.data.DataWatcher;

public class AAA extends AA implements BrokerHandler<AA> {
	protected AA _actualValue;
	protected List<DataWatcher<AA>> _listeners;

	public AAA() {
	}

	@Override
	public AA get() {
		return this;
	}

	@Override
	public AA getActualValue() {
		return this._actualValue;
	}

	@Override
	public void addWatcher(DataWatcher<AA> listener) {
		_listeners = _addWatcher(_listeners, listener, _actualValue);
	}

	@Override
	public void setNewValue(AA newValue) {
		this._actualValue = newValue;
		_notify(_listeners, newValue, _actualValue);
	}

	@Override
	public String getName() {
		return this._actualValue.getName();
	}

	@Override
	public void setName(String name) {
		this._actualValue.setName(name);
	}

	private <TT> void _notify(List<DataWatcher<TT>> listeners, TT newValue, TT oldValue) {
		boolean hasLostReference = false;
		if (listeners == null) return;
		for (DataWatcher<TT> listener : listeners) {
			if (listener != null) {
				boolean result = listener.onUpdate(newValue, oldValue);
				if (result) break;
			} else {
				hasLostReference = true;
			}
		}
		if (hasLostReference) {
			for (int i = listeners.size() - 1; i >= 0; i--) {
				DataWatcher<TT> listener = listeners.get(i);
				if (listener == null) {
					listeners.remove(i);
				}
			}
		}
	}

	private <TT> List<DataWatcher<TT>> _addWatcher(List<DataWatcher<TT>> listeners, DataWatcher<TT> listener, TT actualValue) {
		if (listeners == null) {
			listeners = new CopyOnWriteArrayList<DataWatcher<TT>>();
		}
		listeners.add(listener);
		listener.onUpdate(actualValue, null);
		return listeners;
	}

}

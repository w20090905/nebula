package nebula.data.impl;

import nebula.data.DataStore;
import nebula.data.Editable;
import nebula.data.Timable;

public abstract class ForwardingDataReposEx implements DataReposEx {
	protected abstract DataReposEx delegate();

	@Override
	public <V extends Timable, I> DataStore<V> define(Class<I> clzIndex, Class<V> clz, String name) {
		return delegate().define(clzIndex, clz, name);
	}

	@Override
	public void load() {
		delegate().load();
	}

	@Override
	public void unload() {
		delegate().unload();
	}

	@Override
	public void flush() {
		delegate().flush();
	}

	@Override
	public void clearChanges() {
		delegate().clearChanges();
	}

	@Override
	public void markChanged(Editable v) {
		delegate().markChanged(v);
	}

}

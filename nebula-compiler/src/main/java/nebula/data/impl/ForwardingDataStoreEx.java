package nebula.data.impl;

import java.util.List;
import java.util.Map;

import nebula.data.Classificator;
import nebula.data.Editable;
import nebula.data.Entity;
import nebula.data.Timable;

public abstract class ForwardingDataStoreEx<T extends Timable> implements DataStoreEx<T> {
	protected abstract DataStoreEx<T> delegate();

	@Override
	public T get(Object key) {
		return delegate().get(key);
	}

	@Override
	public Classificator<String, T> getClassificator(String name) {
		return delegate().getClassificator(name);
	}

	@Override
	public Map<String, Classificator<String, Entity>> getClassificatores() {
		return delegate().getClassificatores();
	}

	@Override
	public void add(T v) {
		delegate().add(v);
	}

	@Override
	public void remove(T v) {
		delegate().remove(v);
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
	public List<T> listAll() {
		return delegate().listAll();
	}

	@Override
	public long getLastModified() {
		return delegate().getLastModified();
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
	public void markChanged(Editable v) {
		delegate().markChanged(v);
	}

	@Override
	public void apply(T newV) {
		delegate().apply(newV);
	}
}

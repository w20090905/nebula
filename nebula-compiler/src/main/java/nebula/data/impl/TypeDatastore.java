package nebula.data.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nebula.data.Broker;
import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TypeDatastore implements DataStore<Type>, DataListener<Type> {
	final TypeLoader typeLoader;

	LoadingCache<String, BrokerEx<Type>> cachedTypes;

	public TypeDatastore(TypeLoader typeLoader) {
		this.typeLoader = typeLoader;
		this.cachedTypes = CacheBuilder.newBuilder().build(new CacheLoader<String, BrokerEx<Type>>() {
			public BrokerEx<Type> load(final String path) throws Exception {
					return Brokers.of(TypeDatastore.this.typeLoader.findType(path));
			}
		});
		this.typeLoader.addListener(this);
	}

	public Type get(Object key) {
		return typeLoader.findType((String)key);
	}
	
	public Broker<Type> getBroker(Object key) {
		try {
			return cachedTypes.get((String)key);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Classificator<String, Type> getClassificator(String name) {
		// TODO Not realized getClassificator(String name) 
		return null;
	}

	@Override
	public Map<String, Classificator<String, Entity>> getClassificatores() {
		// TODO Not realized getClassificatores() 
		return null;
	}

	@Override
	public long getLastModified() {
		return System.currentTimeMillis();
	}

	@Override
	public void onAdd(Type v) {
		this.cachedTypes.put(v.getName(), Brokers.of(v));
	}

	@Override
	public void onUpdate(Type oldData, Type newData) {
		try {
			this.cachedTypes.get(oldData.getName()).put(newData);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onRemove(Type v) {
		this.cachedTypes.invalidate(v.getName());
	}

	@Override
	public void add(Type v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(Type v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clearChanges() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Type> listAll() {
		return this.listAll();
	}

}

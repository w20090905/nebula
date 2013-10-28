package nebula.data.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nebula.data.Broker;
import nebula.data.BrokerHandler;
import nebula.data.Classificator;
import nebula.data.DataListener;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TypeDatastore implements DataStore<Type> {
	final TypeLoader typeLoader;

	final LoadingCache<String, BrokerHandler<Type>> cachedTypes;

	public TypeDatastore(TypeLoader typeLoader) {
		this.typeLoader = typeLoader;
		this.cachedTypes = CacheBuilder.newBuilder().build(new CacheLoader<String, BrokerHandler<Type>>() {
			public BrokerHandler<Type> load(final String path) throws Exception {
				return Broker.broke(Type.class, TypeDatastore.this.typeLoader.findType(path));
			}
		});
		this.typeLoader.addListener(dataListener);
	}

	DataListener<Type> dataListener = new DataListener<Type>() {
		@Override
		public void onAdd(Type v) {
			cachedTypes.put(v.getName(), Broker.broke(Type.class, v));
		}

		@Override
		public void onUpdate(Type oldData, Type newData) {
			try {
				cachedTypes.get(oldData.getName()).setNewValue(newData);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void onRemove(Type v) {
			cachedTypes.invalidate(v.getName());
		}
	};

	public Type get(Object key) {
		return typeLoader.findType((String) key);
	}

	public Type getBroker(Object key) {
		try {
			return cachedTypes.get((String) key).get();
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

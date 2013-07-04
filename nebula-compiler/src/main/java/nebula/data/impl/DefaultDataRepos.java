package nebula.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Editable;
import nebula.data.Timable;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultDataRepos implements DataReposEx {
	private Log log = LogFactory.getLog(this.getClass());

	final TypeDatastore typeBrokers;
	LoadingCache<String, BrokerEx> cachedDatastore;

	ReentrantLock lock = new ReentrantLock();

	public DefaultDataRepos(TypeDatastore loader) {
		this.typeBrokers = loader;
		this.cachedDatastore = CacheBuilder.newBuilder().build(new CacheLoader<String, BrokerEx>() {
			public BrokerEx load(final String path) throws Exception {

				Broker<Type> typeBroker = DefaultDataRepos.this.typeBrokers.getBroker(path);

				DataStore datastore = DefaultDataRepos.this.loadDataStore(typeBroker.get().getName(),
						typeBroker.get());

				BrokerCascade<DataStore, Type> datastoreBroker = new BrokerCascade<DataStore, Type>(datastore) {
					@Override
					public boolean onUpdate(Type newData, Type oldData) {
						DataStore datastore = DefaultDataRepos.this.loadDataStore(newData.getName(), oldData);
						this.put(datastore);
						return false;
					}
				};
				typeBroker.addListener(datastoreBroker);

				return datastoreBroker;
			}
		});
	}

	@Override
	public <V extends Timable, I> Broker<DataStore<V>> define(Class<I> clzIndex, Class<V> clz, String name) {
		try {
			return (BrokerEx<DataStore<V>>) cachedDatastore.get(name);
		} catch (ExecutionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	protected DataStore loadDataStore(String name, Type type) {
		return new EntityDataStore(IdMakerBuilder.getIDReader(type), DefaultDataRepos.this, type);
	}


	List<Editable> changes = new ArrayList<Editable>();

	@Override
	public void markChanged(Editable v) {
		lock.lock();
		try {
			changes.add(v);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void flush() {
		lock.lock();
		try {
			for (Editable e : changes) {
				e.apply();
			}
		} finally {
			changes.clear();
			lock.unlock();
		}
	}

	@Override
	public void clearChanges() {
		lock.lock();
		try {
			changes.clear();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void load() {
	}

	@Override
	public void unload() {
		this.clearChanges();
	}
}

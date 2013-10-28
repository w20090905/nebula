package nebula.data2;

import java.util.Map;

import nebula.data.DataWatcher;
import nebula.data.Timable;
import nebula.data.impl.TypeDatastore;
import nebula.lang.Type;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

public class CachedDataRepos implements DataRepos {
	final TypeDatastore typeBrokers;
	final Map<String, DataRepos> reposList = Maps.newConcurrentMap();
	final LoadingCache<String, DataStore<?>> cachedDatastore;

	public CachedDataRepos(TypeDatastore loader) {
		this.typeBrokers = loader;
		
		this.cachedDatastore = CacheBuilder.newBuilder().build(new CacheLoader<String, DataStore<?>>() {
			public DataStore<?> load(final String path) throws Exception {
				BrokerHandler<Type> typeBroker = CachedDataRepos.this.typeBrokers.getBroker(path);
				
				Type type =null;
				DataStore<?> datastore = Broker.watch(type, new Watcher<Type, DataStore<?>>() {
					@Override
					public DataStore<?> watch(Type newData, Type oldData) {
						DataStore<?> datastore = CachedDataRepos.this.loadDataStore(newData.getName(), newData);
						return datastore;
					}				
				});
				return datastore;
			}
		});
	}

	public CachedDataRepos appendRepos(String name, DataRepos dataRepos) {
		reposList.put(name, dataRepos);
		return this;
	}

	@Override
	public <T> DataStore<T> get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> DataStore<T> get(Class<T> clz, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

package nebula.data2;

import java.util.Map;

import com.google.common.collect.Maps;

public abstract class AbstractKindExImp<T> implements KindEx<T> {

	protected Map<String, T> datas = Maps.newHashMap();

	final DatatstoreEx datastore;

	public AbstractKindExImp(DatatstoreEx datastore) {
		this.datastore = datastore;
	}

	@Override
	public T get(long id) {
		return null;
	}

	@Override
	public T get(String key) {
		return datas.get(key);
	}

	@Override
	public T put(T data) {
		return datastore.put(this, data);
	}
}

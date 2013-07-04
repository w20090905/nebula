package nebula.data2;

import java.util.List;

import com.google.common.collect.Lists;

public class DatatstoreExImp implements DatatstoreEx {
	List<KindEx<?>> kindChanged = Lists.newArrayList();

	@SuppressWarnings("unchecked")
	@Override
	public <V> Kind<V> get(String kindName, Class<V> clz) {
		return (Kind<V>)new EntityKindExImp(this);
	}

//	@Override
//	// @SuppressWarnings("unchecked")
//	public void commit() {
//		for (KindEx<?> kindex : kindChanged) {
//			kindex.applyAll();
//		}
//	}
//
//	@Override
//	public void rollback() {
//		kindChanged.clear();
//	}

	@Override
	public <T> T put(KindEx<T> kind, T data) {
		return kind.apply(data);
	}
}

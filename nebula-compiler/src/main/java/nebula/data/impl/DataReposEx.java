package nebula.data.impl;

import nebula.data.DataRepos;
import nebula.data.Editable;

public interface DataReposEx extends DataRepos {
	void load();

	void unload();

//	void add(V v);
//
//	void remove(V v);

	void flush();

	void clearChanges();

	// void transaction(Callback<V> callback);

	void markChanged(Editable v);
}

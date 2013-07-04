package nebula.data.impl;

import nebula.data.DataStore;
import nebula.data.Editable;
import nebula.data.Timable;

interface DataStoreEx<V extends Timable> extends DataStore<V>{

	void load();

	void unload();
	void markChanged(Editable v);

	void apply(V newV);
}

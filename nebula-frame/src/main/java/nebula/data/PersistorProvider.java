package nebula.data;

import nebula.persistor.Persistor;

public interface PersistorProvider {
    <T> Persistor<T> get(Class<T> clz);
}

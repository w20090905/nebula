package nebula.persistor;

import java.util.List;

public interface Persistor<T> {

    T get(String... keys);

    T merge(T v);

    void persist(T v);

    void remove(T v);

    void removeAll();

    List<T> list();

    List<T> query(String cause, Object... params);

}
package nebula.data;

import java.util.List;

import nebula.lang.FindableList;
import nebula.persistor.Persistor;

public class SimpleStore<T> implements Store<T> {
    FindableList<String, T> datas = new FindableList<String, T>();
    Persistor<T> p;

    @SuppressWarnings("unchecked")
    SimpleStore(Persistor<T> persistor) {
        p = persistor;
        List<T> list = p.list();

        for (Indentiable t : (List<Indentiable>) list) {
            datas.put(t.getIndentified(), (T) t);
        }
    }

    @Override
    public T get(String... keys) {
        assert (keys.length <= 1);
        return datas.get(keys[0]);
    }

    @Override
    public void persist(T v) {
        p.persist(v);
        datas.put(((Indentiable) v).getIndentified(), p.get(((Indentiable) v).getIndentified()));
    }
}

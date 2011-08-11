package nebula.data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import nebula.lang.FindableList;

public class Repos implements StoreProvider {
    Map<String, Store<?>> stores = new HashMap<String, Store<?>>();

    FindableList<Pattern, StoreProvider> provideres = new FindableList<Pattern, StoreProvider>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Store<T> get(Class<T> clz) {
        Store<T> store = (Store<T>) stores.get(clz);
        if (store != null) {
            return store;
        }

        return this.find(clz);
    }

    @Override
    public Store<?> get(String typeName) {
        Store<?> store = (Store<?>) stores.get(typeName);
        if (store != null) {
            return store;
        }

        return this.find(typeName);
    }

    Store<?> find(String name) {
        Store<?> store = null;

        for (Pattern p : provideres.getKeys().keySet()) {
            if (p.matcher(name).matches()) {
                store = provideres.get(p).get(name);
                if (store != null) {
                    stores.put(name, store);
                    return store;
                }
            }
        }
        return null;
    }

    <T> Store<T> find(Class<T> clz) {
        Store<T> store = null;

        for (Pattern p : provideres.getKeys().keySet()) {
            if (p.matcher(clz.getName()).matches()) {
                store = provideres.get(p).get(clz);
                if (store != null) {
                    stores.put(clz.getName(), store);
                    return store;
                }
            }
        }
        return null;
    }

    void bindTo(String regex, StoreProvider storeProvider) {
        regex = regex.replaceAll("[\\.]", "[.]");
        regex = regex.replaceAll("[\\*]", ".*");

        provideres.put(Pattern.compile(regex), storeProvider);
    }

}

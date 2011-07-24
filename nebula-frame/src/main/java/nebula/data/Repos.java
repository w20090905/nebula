package nebula.data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import nebula.lang.FindableList;

public class Repos implements StoreProvider {
    Map<Class<?>, Store<?>> stores = new HashMap<Class<?>, Store<?>>();

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

    <T> Store<T> find(Class<T> clz) {
        Store<T> store = null;

        for (Pattern p : provideres.getKeys().keySet()) {
            if (p.matcher(clz.getName()).matches()) {
                store = provideres.get(p).get(clz);
                if (store != null) {
                    stores.put(clz, store);
                    return store;
                }
            }
        }
        return null;
    }

    <T> void bindTo(String regex, StoreProvider storeProvider) {
        regex = regex.replaceAll("[\\.]", "[.]");
        regex = regex.replaceAll("[\\*]", ".*");

        provideres.put(Pattern.compile(regex), storeProvider);
    }
}

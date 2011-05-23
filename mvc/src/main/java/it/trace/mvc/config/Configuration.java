package it.trace.mvc.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private final Map<Object, Namespace> namespaces = new HashMap<Object, Namespace>();

    public Namespace getNamespace(String key) {
        return namespaces.get(key);
    }

    public Map<Object, Namespace> getNamespaces() {
        return Collections.unmodifiableMap(namespaces);
    }

    public void addNamespace(Object key, Namespace namespace) {
        namespaces.put(key, namespace);
    }

    public void removeNamespace(Object key) {
        namespaces.remove(key);
    }

}

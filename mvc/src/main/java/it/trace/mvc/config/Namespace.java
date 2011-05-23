package it.trace.mvc.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class Namespace {

    private String name;
    private final Map<Object, Resource> resources = new LinkedHashMap<Object, Resource>();

    public Namespace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getResource(Object key) {
        return resources.get(key);
    }

    public Map<Object, Resource> getResources() {
        return Collections.unmodifiableMap(resources);
    }

    public void addResource(Object key, Resource resource) {
        resources.put(key, resource);
    }

    public void removeResource(Object key) {
        resources.remove(key);
    }

}

package it.trace.mvc.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Resource {

    private String name;
    private final Map<Object, Operation> operations = new LinkedHashMap<Object, Operation>();

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operation getOperation(String key) {
        return operations.get(key);
    }

    public Map<Object, Operation> getOperations() {
        return Collections.unmodifiableMap(operations);
    }

    public void addOperation(Object key, Operation operation) {
        operations.put(key, operation);
    }

    public void removeOperation(Object key) {
        operations.remove(key);
    }

}

package it.trace.mvc.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    protected Map<String, NamespaceConfig> namespaceConfigs = new HashMap<String, NamespaceConfig>();

    public NamespaceConfig getNamespaceConfig(String name) {
        return namespaceConfigs.get(name);
    }

    public void addNamespaceConfig(NamespaceConfig namespaceConfig) {
        namespaceConfigs.put(namespaceConfig.getName(), namespaceConfig);
    }

    public void addNamespaceConfigs(Iterable<NamespaceConfig> namespaceConfigs) {
        for (NamespaceConfig namespaceConfig : namespaceConfigs) {
            this.namespaceConfigs.put(namespaceConfig.getName(), namespaceConfig);
        }
    }

    public void removeNamespaceConfig(String name) {
        namespaceConfigs.remove(name);
    }

    public Map<String, NamespaceConfig> getNamespaceConfigs() {
        return Collections.unmodifiableMap(namespaceConfigs);
    }
}

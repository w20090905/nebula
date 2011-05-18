package it.trace.mvc.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class NamespaceConfig {

    private String name;
    private final Map<String, ActionConfig> actionConfigs = new LinkedHashMap<String, ActionConfig>();

    public NamespaceConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActionConfig getActionConfig(String name) {
        return actionConfigs.get(name);
    }

    public void addActionConfig(ActionConfig actionConfig) {
        actionConfigs.put(actionConfig.getName(), actionConfig);
    }

    public void removeActionConfig(String name) {
        actionConfigs.remove(name);
    }

    public Map<String, ActionConfig> getActionConfigs() {
        return Collections.unmodifiableMap(actionConfigs);
    }

}

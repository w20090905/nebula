package it.trace.mvc.config;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActionConfig {

    private String name;
    private Method method;
    private DataBinder dataBinder;
    private final Map<String, ResultConfig> resultConfigs = new LinkedHashMap<String, ResultConfig>();

    public ActionConfig(String name, Method method) {
        this.name = name;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public DataBinder getDataBinder() {
        return dataBinder;
    }

    public void setDataBinder(DataBinder dataBinder) {
        this.dataBinder = dataBinder;
    }

    public ResultConfig getResultConfig(Object name) {
        return resultConfigs.get(name);
    }

    public void addResultConfig(ResultConfig resultConfig) {
        resultConfigs.put(resultConfig.getName(), resultConfig);
    }

    public void removeResultConfig(String name) {
        resultConfigs.remove(name);
    }

    public Map<String, ResultConfig> getResultConfigs() {
        return Collections.unmodifiableMap(resultConfigs);
    }

    public Class<?> getActionClass() {
        return this.method.getDeclaringClass();
    }

}

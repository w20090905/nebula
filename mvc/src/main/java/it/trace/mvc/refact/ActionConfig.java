package it.trace.mvc.refact;

import it.trace.mvc.DataBinder;
import it.trace.mvc.inject.ContainerManager;
import it.trace.mvc.result.Result;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class ActionConfig {

    private String name;
    private Method method;
    private DataBinder dataBinder;
    private final Map<String, Result> resultConfigs = new LinkedHashMap<String, Result>();

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

    public Result getResultConfig(Object name) {
        return resultConfigs.get(name);
    }

    public void addResultConfig(String name, Result resultConfig) {
        resultConfigs.put(name, resultConfig);
    }

    public void removeResultConfig(String name) {
        resultConfigs.remove(name);
    }

    public Map<String, Result> getResultConfigs() {
        return Collections.unmodifiableMap(resultConfigs);
    }

    public Class<?> getActionClass() {
        return this.method.getDeclaringClass();
    }

    public HttpExecutor getExecutor(String id){
        ContainerManager.getInstance(this.getActionClass());
        return null;
    }

}

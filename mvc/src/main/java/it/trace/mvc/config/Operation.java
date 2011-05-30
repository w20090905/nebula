package it.trace.mvc.config;

import it.trace.mvc.binder.DataBinder;
import it.trace.mvc.executor.ActionExecutor;
import it.trace.mvc.inject.ContainerManager;
import it.trace.mvc.result.Result;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operation {

    private String name;
    private Method method = null;
    private DataBinder<Object> dataBinder = null;
    private final Map<Object, Result> results = new LinkedHashMap<Object, Result>();

    public Operation(String name) {
        this.name = name;
    }

    public Operation(Method method) {
        this.name = method.getName();
        this.method = method;
    }

    public Operation(String name, Method method) {
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

    public DataBinder<Object> getDataBinder() {
        return dataBinder;
    }

    public void setDataBinder(DataBinder<Object> dataBinder) {
        this.dataBinder = dataBinder;
    }

    public Map<Object, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }

    public Result getResult(Object key) {
        return results.get(key);
    }

    public void addResult(Object key, Result result) {
        results.put(key, result);
    }

    public void removeResult(Object key) {
        results.remove(key);
    }

    public ActionExecutor getExecutor(){
        Object instance = ContainerManager.getInstance(method.getDeclaringClass());
        return new ActionExecutor(this, instance);
    }

    public Class<?> getOperationClass() {
        return method.getDeclaringClass();
    }

}

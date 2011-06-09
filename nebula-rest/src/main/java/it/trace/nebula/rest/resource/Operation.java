package it.trace.nebula.rest.resource;

import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.executor.ActionExecutor;
import it.trace.nebula.rest.result.Result;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operation {

    private final String name;

    private final Method method;

    private final Map<Object, Result> results = new LinkedHashMap<Object, Result>();

    private DataBinder<Object> dataBinder = null;

    public Operation(String name) {
        this.name = name;
        this.method = null;
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

    public Method getMethod() {
        return method;
    }

    public void setDataBinder(DataBinder<Object> dataBinder) {
        this.dataBinder = dataBinder;
    }

    public DataBinder<Object> getDataBinder() {
        return dataBinder;
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

    public Class<?> getOperationClass() {
        return method.getDeclaringClass();
    }

    public ActionExecutor getExecutor(String accpet){
        ActionExecutor e = new ActionExecutor(this, accpet);
        return e;
    }

}

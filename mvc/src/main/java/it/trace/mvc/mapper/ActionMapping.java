package it.trace.mvc.mapper;


import it.trace.mvc.refact.ActionConfig;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class ActionMapping {

    //    private String namespace = "";
    //    private String name = "";
    //    private String method = "";
    private final Map<String, Object> params = new LinkedHashMap<String, Object>();
    private ActionConfig actionConfig;

    //    public String getNamespace() {
    //        return namespace;
    //    }
    //
    //    public void setNamespace(String namespace) {
    //        this.namespace = namespace;
    //    }
    //
    //    public String getName() {
    //        return name;
    //    }
    //
    //    public void setName(String name) {
    //        this.name = name;
    //    }
    //
    //    public String getMethod() {
    //        return method;
    //    }
    //
    //    public void setMethod(String method) {
    //        this.method = method;
    //    }

    public Map<String, Object> getParamters() {
        return Collections.unmodifiableMap(params);
    }

    public Object getParamter(String key) {
        return params.get(key);
    }

    public void addParamter(String key, Object value) {
        params.put(key, value);
    }

    public void removeParamter(String key) {
        params.remove(key);
    }

    public ActionConfig getActionConfig() {
        return actionConfig;
    }

    public void setActionConfig(ActionConfig actionConfig) {
        this.actionConfig = actionConfig;
    }

}

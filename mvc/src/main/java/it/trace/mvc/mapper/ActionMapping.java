package it.trace.mvc.mapper;

import it.trace.mvc.config.ActionConfig;

import java.util.Map;

public class ActionMapping {

    private String namespace = "";
    private String name = "";
    private String method = "";
    private Map<String, Object> params;
    private ActionConfig actionConfig;
    //    private HttpServletRequest request;
    //    private HttpServletResponse response;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public ActionConfig getActionConfig() {
        return actionConfig;
    }

    public void setActionConfig(ActionConfig actionConfig) {
        this.actionConfig = actionConfig;
    }

    //    public HttpServletRequest getRequest() {
    //        return request;
    //    }
    //
    //    public void setRequest(HttpServletRequest request) {
    //        this.request = request;
    //    }
    //
    //    public HttpServletResponse getResponse() {
    //        return response;
    //    }
    //
    //    public void setResponse(HttpServletResponse response) {
    //        this.response = response;
    //    }

}

package it.trace.mvc.config;

import java.util.Map;


public class ResultConfig {

    protected String name;
    protected Map<String,String> params;
    protected Template template;
    // type, value, renderer

    public String getName() {
        return name;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package it.trace.mvc.config;



public class ResultConfig {

    private String name;
    private Template template;

    public String getName() {
        return name;
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

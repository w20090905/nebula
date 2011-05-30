package it.trace.mvc.result;


import it.trace.mvc.result.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DisplayResult implements Result {

    private String name;
    private Template template;

    public DisplayResult(Template template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Object data) {
        template.render(request, response, data);
    }

}

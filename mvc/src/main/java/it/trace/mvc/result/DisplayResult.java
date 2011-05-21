package it.trace.mvc.result;


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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, Object data) {
        // TODO Auto-generated method stub
    }

}

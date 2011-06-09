package it.trace.nebula.rest.result.template.jsp;

import it.trace.nebula.rest.result.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspTemplate implements Template {

    private final String path;

    public JspTemplate(String path) {
        this.path = path;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response, Object model) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("没有找到JSP [" + path + "]");
        }
    }

}

package it.trace.mvc.config;

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

            // ++++++++++++++++++++++++++++++++++++++++++++++

            // ----------------------------------------------

            request.getRequestDispatcher(this.path).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();    // TODO
        }
    }

}

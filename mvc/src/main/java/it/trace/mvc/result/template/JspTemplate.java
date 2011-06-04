package it.trace.mvc.result.template;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class JspTemplate implements Template {

    private final String path;

    public JspTemplate(String path) {
        this.path = path;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response, Object model) {
        try {

            // ++++++++++++++++++++++++++++++++++++++++++++++
            Class<?> c = model.getClass();
            for (Method m : c.getMethods()) {
                if (m.getParameterTypes().length == 0
                        && m.getName().startsWith("get")
                        && !void.class.equals(m.getReturnType())) {

                    String fn = StringUtils.uncapitalize(m.getName().substring(3));

                    request.setAttribute(fn, m.invoke(model, new Object[] {}));
                }
            }
            // ----------------------------------------------

            request.getRequestDispatcher(this.path).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);  // TODO
        }
    }

}

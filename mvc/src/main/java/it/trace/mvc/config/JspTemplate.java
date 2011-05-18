package it.trace.mvc.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
            for (Method m : c.getDeclaredMethods()) {
                if (Modifier.isPublic(m.getModifiers())
                        && m.getParameterTypes().length == 0
                        && m.getName().startsWith("get")
                        && !void.class.equals(m.getReturnType())) {

                    String fn = StringUtils.uncapitalize(m.getName().substring(3));

                    request.setAttribute(fn, m.invoke(model, new Object[] {}));
                }
            }
            // ----------------------------------------------

            request.getRequestDispatcher(this.path).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();    // TODO
        }
    }

}

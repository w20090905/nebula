package it.trace.mvc.binder;

import it.trace.mvc.Constants;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Context {

    private HttpServletRequest request = null;
    private HttpSession session = null;
    private ServletContext app = null;
    private String id = null;

    public Context() {
    }

    public Context(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
        this.app = session.getServletContext();
    }

    public Context(HttpServletRequest request, String id) {
        this(request);
        this.id = id;
    }

    public Context(HttpServletRequest request, HttpSession session) {
        this.request = request;
        this.session = session;
        this.app = session.getServletContext();
    }

    public Context(HttpServletRequest request, HttpSession session, String id) {
        this(request, session);
        this.id = id;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public ServletContext getApp() {
        return app;
    }

    public void setApp(ServletContext app) {
        this.app = app;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    public <T> T getParameter(String key) {

        if (id != null && Constants.ID_PARAM_NAME.equals(key)) {
            return (T) id;
        }

        Object r = null;

        if (request.getParameterMap().containsKey(key)) {
            r = request.getParameter(key);
        } else if ((r = request.getAttribute(key)) != null) {
        } else if ((r = session.getAttribute(key)) != null) {
        } else if ((r = app.getAttribute(key)) != null) {
        }

        return (T) r;
    }

    public <T> T getParameter(String key, Class<T> returnType) {
        return getParameter(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] getParameterValues(String key) {

        Object r = null;

        if (request.getParameterMap().containsKey(key)) {
            r = request.getParameterValues(key);
        } else if ((r = request.getAttribute(key)) != null) {
        } else if ((r = session.getAttribute(key)) != null) {
        } else if ((r = app.getAttribute(key)) != null) {
        }

        return (T[]) r;
    }

    @SuppressWarnings("unchecked")
    public <T> T getRequestParameter(String key) {
        if (request.getParameterMap().containsKey(key)) {
            return (T) request.getParameter(key);
        }
        return (T) request.getAttribute(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] getRequestParameterValues(String key) {
        if (request.getParameterMap().containsKey(key)) {
            return (T[]) request.getParameterValues(key);
        }
        return (T[]) request.getAttribute(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSessionParameter(String key) {
        return (T)session.getAttribute(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getApplicationParameter(String key) {
        return (T) app.getAttribute(key);
    }

}

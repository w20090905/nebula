package it.trace.nebula.rest.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectResult implements Result {

    protected String path;

    public RedirectResult(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            response.sendRedirect(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }
}

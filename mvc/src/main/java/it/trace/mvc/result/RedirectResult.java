package it.trace.mvc.result;

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

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            response.sendRedirect(this.path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

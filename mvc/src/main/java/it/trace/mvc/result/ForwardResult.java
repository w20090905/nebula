package it.trace.mvc.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardResult implements Result {
    protected String path;

    public ForwardResult(String path) {
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
            request.getRequestDispatcher(this.path).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

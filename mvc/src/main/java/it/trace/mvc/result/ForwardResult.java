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
    public void execute(HttpServletRequest req, HttpServletResponse resp, Object data) {
        // TODO Forword to path

    }

}

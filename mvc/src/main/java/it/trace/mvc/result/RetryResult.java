package it.trace.mvc.result;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RetryResult implements Result {
    protected String path;

    public RetryResult(String path) {
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

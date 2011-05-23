package it.trace.mvc.executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface HttpExecutor {
    void execute(HttpServletRequest request, HttpServletResponse response);
}

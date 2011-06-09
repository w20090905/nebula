package it.trace.nebula.rest.executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executor {

    void process(HttpServletRequest request, HttpServletResponse response);

}

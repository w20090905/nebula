package it.trace.mvc.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Result {

    void execute(HttpServletRequest request, HttpServletResponse response, Object data);
}

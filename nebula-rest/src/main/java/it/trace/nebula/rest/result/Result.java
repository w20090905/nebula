package it.trace.nebula.rest.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Result {

    void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data);

    void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught);

}

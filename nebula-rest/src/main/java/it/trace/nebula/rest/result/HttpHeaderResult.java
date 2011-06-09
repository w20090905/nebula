package it.trace.nebula.rest.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHeaderResult implements Result {

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }

}

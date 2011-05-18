package it.trace.mvc.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultDataBinder implements DataBinder {

    @Override
    public void bind(HttpServletRequest request, HttpServletResponse response, Map<String, Object> extraParams, Object instance) {

    }

}

package it.trace.mvc.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataBinder {

    void bind(HttpServletRequest request, HttpServletResponse response, Map<String, Object> extraParams, Object instance);

}

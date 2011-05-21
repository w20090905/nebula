package it.trace.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataBinder<T> {

    void bind(HttpServletRequest request, HttpServletResponse response, Map<String, Object> extraParams, T instance);

}

package it.trace.mvc.binder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataBinder<T> {

    void bind(HttpServletRequest request, HttpServletResponse response, String id, T instance);

}

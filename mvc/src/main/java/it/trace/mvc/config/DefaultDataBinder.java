package it.trace.mvc.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DefaultDataBinder<T> implements DataBinder<T> {

    @Override
    public void bind(HttpServletRequest request, HttpServletResponse response,
            Map<String, Object> extraParams, T instance) {
        // TODO Auto-generated method stub

    }

}

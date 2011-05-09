package it.trace.mvc.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Template {

    void render(HttpServletRequest request, HttpServletResponse response, Object model);
}

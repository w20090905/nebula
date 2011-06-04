package it.trace.mvc;

import javax.servlet.http.HttpServletRequest;

public enum HttpMethod {

    GET,
    POST,
    PUT,
    DELETE,
    HEAD,
    OPTIONS,
    TRACE;

    public final static String HTTP_METHOD_PARAM_NAME = "__httpMethod";

    public static HttpMethod parse(HttpServletRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return GET;
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            String httpMethodParamValue = request.getParameter(HTTP_METHOD_PARAM_NAME);
            if ("PUT".equalsIgnoreCase(httpMethodParamValue)) {
                return PUT;
            } else if ("DELETE".equalsIgnoreCase(httpMethodParamValue)) {
                return DELETE;
            }
            return POST;
        } else if ("PUT".equalsIgnoreCase(request.getMethod())) {
            return PUT;
        } else if ("DELETE".equalsIgnoreCase(request.getMethod())) {
            return DELETE;
        } else if ("HEAD".equalsIgnoreCase(request.getMethod())) {
            return HEAD;
        } else if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return OPTIONS;
        } else if ("TRACE".equalsIgnoreCase(request.getMethod())) {
            return TRACE;
        }

        return null;
    }

}

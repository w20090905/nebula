package it.trace.nebula.rest.helper;

import it.trace.nebula.rest.annotations.HttpMethod;
import it.trace.nebula.rest.annotations.MimeType;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    public static final String HTTP_METHOD_PARAM_NAME = "__http_method";

    public static String getHttpMethod(HttpServletRequest request) {

        if (HttpMethod.GET.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.GET;
        } else if (HttpMethod.POST.equalsIgnoreCase(request.getMethod())) {
            String httpMethodParamValue = request.getParameter(HTTP_METHOD_PARAM_NAME);
            if (HttpMethod.PUT.equalsIgnoreCase(httpMethodParamValue)) {
                return HttpMethod.PUT;
            } else if (HttpMethod.DELETE.equalsIgnoreCase(httpMethodParamValue)) {
                return HttpMethod.DELETE;
            }
            return HttpMethod.POST;
        } else if (HttpMethod.PUT.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.PUT;
        } else if (HttpMethod.DELETE.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.DELETE;
        } else if (HttpMethod.HEAD.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.HEAD;
        } else if (HttpMethod.OPTIONS.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.OPTIONS;
        } else if (HttpMethod.TRACE.equalsIgnoreCase(request.getMethod())) {
            return HttpMethod.TRACE;
        }

        return request.getMethod();
    }

    public static String getAccept(HttpServletRequest request) {
        // 获取客户端中的请求数据类型
        String accept = request.getHeader("accept");
        if (accept == null) {
            accept = MimeType.WILDCARD;
        }
        return accept.toLowerCase();
    }

    // public synchronized static String getContentType(HttpServletRequest
    // request) {
    // // 获取客户端中的请求数据类型
    // String contentType = request.getContentType();
    // if(contentType != null){
    // String[] cTypes = contentType.split(";");
    // contentType = cTypes[0];
    // }
    //
    // if (contentType == null || contentType.trim().equals(""))
    // contentType = MimeType.CONTENT_OF_APPLICATION_X_WWW_FORM_URLENCODED;
    // return contentType;
    // }

    //     public synchronized static String getAccepte(HttpServletRequest request)
    //     {
    //     // 获取客户端中的请求数据类型
    //     String accept = request.getHeader("accept");
    //     if (accept == null || accept.indexOf(MimeType.MIME_OF_ALL) != -1)
    //     accept = "*/*";
    //     accept = accept.toLowerCase();
    //     return accept;
    //     }
    //
    // public synchronized static String getMimeType(HttpServletRequest request)
    // {
    // // 获取客户端中的请求数据类型
    // String accept = RequestHelper.getAccepte(request);
    // // 缺省的数据返回类型
    // String mimeType = accept.split(",")[0];
    // if (mimeType.equals(MimeType.MIME_OF_ALL))
    // mimeType = MimeType.MIME_OF_TEXT_HTML;
    // return mimeType;
    // }
}

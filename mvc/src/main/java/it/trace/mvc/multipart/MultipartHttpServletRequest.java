package it.trace.mvc.multipart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MultipartHttpServletRequest extends HttpServletRequestWrapper {

    public MultipartHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

}

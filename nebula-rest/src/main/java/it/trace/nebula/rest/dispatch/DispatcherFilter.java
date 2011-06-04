package it.trace.nebula.rest.dispatch;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DispatcherFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
    ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
    //
    //    private final static String PACKAGES_PARAM_NAME = "actionPackages";
    //
    //    private Configuration configuration;
    //    private ExecutorFinder finder;
    //
    //    @Override
    //    public void init(FilterConfig filterConfig) throws ServletException {
    //
    //        String[] packages = filterConfig.getInitParameter(PACKAGES_PARAM_NAME).split("\\s+");
    //        configuration = RESTfulConventionConfigBuilder.createConfiguration(packages);
    //
    //        finder = ContainerManager.getInstance(ExecutorFinder.class);
    //        finder.init(configuration);
    //
    //    }
    //
    //    @Override
    //    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    //
    //        HttpServletRequest request = (HttpServletRequest) req;
    //        HttpServletResponse response = (HttpServletResponse) res;
    //
    //        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //        //        for (Object name : request.getParameterMap().keySet()) {
    //        //            System.out.println(name + " : " + request.getParameter((String) name));
    //        //        }
    //        System.out.println("HttpMethod : " + request.getMethod() + " | URL : "+ getPath(request));
    //        System.out.println(request.getParameterMap().size());
    //        for (@SuppressWarnings("rawtypes") Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
    //            Object name = e.nextElement();
    //            System.out.println(name + " : " + request.getParameter((String) name));
    //
    //        }
    //
    //        // -----------------------------------------------------------
    //
    //        HttpExecutor executor = finder.find(HttpMethod.parse(request), getPath(request));
    //
    //        if (executor != null) {
    //            executor.execute(request, response);
    //        } else {
    //            chain.doFilter(request, response);
    //        }
    //
    //    }
    //
    //    @Override
    //    public void destroy() {
    //        finder = null;
    //        configuration = null;
    //    }
    //
    //    private String getPath(HttpServletRequest request) {
    //        String servletPath = request.getServletPath();
    //
    //        String requestUri = request.getRequestURI();
    //        // Detecting other characters that the servlet container cut off (like anything after ';')
    //        if (requestUri != null && servletPath != null && !requestUri.endsWith(servletPath)) {
    //            int pos = requestUri.indexOf(servletPath);
    //            if (pos > -1) {
    //                servletPath = requestUri.substring(requestUri.indexOf(servletPath));
    //            }
    //        }
    //
    //        if (servletPath != null && servletPath.isEmpty()) {
    //            return servletPath;
    //        }
    //
    //        int startIndex = "".equals(request.getContextPath()) ? 0 : request.getContextPath().length();
    //        int endIndex = request.getPathInfo() == null ? requestUri.length() : requestUri.lastIndexOf(request.getPathInfo());
    //
    //        if (startIndex > endIndex) { // this should not happen
    //            endIndex = startIndex;
    //        }
    //
    //        return requestUri.substring(startIndex, endIndex);
    //    }
    //
}

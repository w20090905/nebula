package it.trace.mvc.http;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.executor.SimpleRESTfulExecutorFinder;
import it.trace.mvc.executor.HttpExecutor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherFilter implements Filter {

    private Configuration configuration;
    SimpleRESTfulExecutorFinder finder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        //        configuration = new ConfigurationFactory().create(filterConfig);
        finder = new SimpleRESTfulExecutorFinder(configuration);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpExecutor executor = finder.find(HttpMethod.parse(request), getServletPath(request));

        if (executor != null) {
            executor.execute(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    private String getServletPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();

        String requestUri = request.getRequestURI();
        // Detecting other characters that the servlet container cut off (like anything after ';')
        if (requestUri != null && servletPath != null && !requestUri.endsWith(servletPath)) {
            int pos = requestUri.indexOf(servletPath);
            if (pos > -1) {
                servletPath = requestUri.substring(requestUri.indexOf(servletPath));
            }
        }

        if (null != servletPath && !"".equals(servletPath)) {
            return servletPath;
        }

        int startIndex = "".equals(request.getContextPath()) ? 0 : request.getContextPath().length();
        int endIndex = request.getPathInfo() == null ? requestUri.length() : requestUri.lastIndexOf(request.getPathInfo());

        if (startIndex > endIndex) { // this should not happen
            endIndex = startIndex;
        }

        return requestUri.substring(startIndex, endIndex);
    }

}

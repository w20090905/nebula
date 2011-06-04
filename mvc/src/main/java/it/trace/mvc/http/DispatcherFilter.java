package it.trace.mvc.http;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.builder.RESTfulConventionConfigBuilder;
import it.trace.mvc.executor.ExecutorFinder;
import it.trace.mvc.executor.HttpExecutor;
import it.trace.mvc.inject.ContainerManager;

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

    private final static String PACKAGES_PARAM_NAME = "actionPackages";

    private Configuration configuration;
    private ExecutorFinder finder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String[] packages = filterConfig.getInitParameter(PACKAGES_PARAM_NAME).split("\\s+");
        configuration = RESTfulConventionConfigBuilder.createConfiguration(packages);

        finder = ContainerManager.getInstance(ExecutorFinder.class);
        finder.init(configuration);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpExecutor executor = finder.find(HttpMethod.parse(request), getPath(request));

        if (executor != null) {
            executor.execute(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        finder = null;
        configuration = null;
    }

    private String getPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();

        String requestUri = request.getRequestURI();
        // Detecting other characters that the servlet container cut off (like anything after ';')
        if (requestUri != null && servletPath != null && !requestUri.endsWith(servletPath)) {
            int pos = requestUri.indexOf(servletPath);
            if (pos > -1) {
                servletPath = requestUri.substring(requestUri.indexOf(servletPath));
            }
        }

        if (servletPath != null && servletPath.isEmpty()) {
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

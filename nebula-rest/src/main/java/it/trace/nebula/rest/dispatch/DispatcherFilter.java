package it.trace.nebula.rest.dispatch;

import it.trace.nebula.rest.context.ApplicationContext;
import it.trace.nebula.rest.executor.Executor;
import it.trace.nebula.rest.helper.RequestHelper;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherFilter implements Filter {

    protected Pattern excludedUrlPattern;

    protected ExecutorFinder finder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = ApplicationContext.getInstance();
        excludedUrlPattern = null;
        finder = new SimpleRESTfulExecutorFinder(context);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String url = request.getServletPath();
        if (isExcludedUrl(url)) {
            chain.doFilter(request, response);
            return;
        }

        Executor executor = finder.lookup(RequestHelper.getHttpMethod(request), RequestHelper.getAccept(request), url);

        if (executor != null) {
            executor.process(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    protected boolean isExcludedUrl(String url) {
        return excludedUrlPattern != null ? excludedUrlPattern.matcher(url).matches() : false;
    }

}

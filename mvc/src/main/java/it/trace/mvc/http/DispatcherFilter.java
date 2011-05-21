package it.trace.mvc.http;

import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.builder.ConfigurationFactory;
import it.trace.mvc.refact.ActionExecutorFinder;
import it.trace.mvc.refact.HttpExecutor;
import it.trace.mvc.util.RequestUtils;

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

    //private ActionMapper finder;
    private Configuration configuration;
    //    private ActionInvocation invocation;
    ActionExecutorFinder finder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        configuration = new ConfigurationFactory().create(filterConfig);

        //finder = ContainerManager.getInstance(ActionMapper.class);
        //invocation = ContainerManager.getInstance(ActionInvocation.class);

        finder =  new ActionExecutorFinder(configuration);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println(request.getMethod());

        // TODO ActionMapping -> delete???
        HttpExecutor ex = finder.find(request.getMethod(), RequestUtils.getServletPath(request));

        if (ex != null) {
            ex.execute(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

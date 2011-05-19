package it.trace.mvc;

import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.builder.ConfigurationFactory;
import it.trace.mvc.inject.ContainerManager;
import it.trace.mvc.mapper.ActionMapper;
import it.trace.mvc.mapper.ActionMapping;

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

    private ActionMapper mapper;
    private Configuration configuration;
    private ActionInvocation invocation;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        configuration = new ConfigurationFactory().create(filterConfig);

        mapper = ContainerManager.getInstance(ActionMapper.class);
        invocation = ContainerManager.getInstance(ActionInvocation.class);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println(request.getMethod());

        // TODO ActionMapping -> delete???
        ActionMapping mapping = mapper.getActionMapping(request, response, configuration);

        if (mapping != null) {
            invocation.invoke(request, response, mapping);
        } else {
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

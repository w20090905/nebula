package it.trace.mvc;

import it.trace.mvc.config.ConfigurationManager;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        ActionMapper mapper = BeanFactory.getBean(ActionMapper.class);
        ActionMapping mapping = mapper.getActionMapping(request, response, ConfigurationManager.getConfiguration());

        ActionInvocation invocation = BeanFactory.getBean(ActionInvocation.class);
        invocation.invoke(request, response, mapping);

        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

package it.trace.nebula.rest.sample.filter;

import it.trace.nebula.rest.dispatch.DispatcherFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tt.Dtest;

import net.sf.json.JSON;

public class MyFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    		new Dtest().SayHello();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println(request.getServletPath());

        System.out.println(JSON.class);
        System.out.println(DispatcherFilter.class);
        System.out.println(DispatcherFilter.JSP_TEMPLATES_BASE_PATH);
        System.out.println("===================");

        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}

package it.trace.mvc.refact;

import it.trace.mvc.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActionExecutor implements HttpExecutor {

    protected ActionConfig config;
    protected Object action;

    public ActionExecutor(ActionConfig config,Object action){
        this.config = config;
        this.action = action;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object s = config.getMethod().invoke(action, null);
            Result result =  config.getResultConfig(s);
            result.execute(request, response, action);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package it.trace.mvc.executor;

import it.trace.mvc.Constants;
import it.trace.mvc.binder.Context;
import it.trace.mvc.binder.DataBinder;
import it.trace.mvc.config.Operation;
import it.trace.mvc.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionExecutor implements HttpExecutor {

    protected Operation operation;
    protected Object action;
    protected String id = null;

    public ActionExecutor(Operation operation, Object action) {
        this.operation = operation;
        this.action = action;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DataBinder<Object> dataBinder = operation.getDataBinder();
        if (dataBinder != null) {
            dataBinder.bind(new Context(request, id), action);
        }

        Object resultCode;
        try {
            resultCode = operation.getMethod().invoke(action, Constants.EMPTY_OBJECT_ARRAY);
        } catch (Exception e) {
            Result result = operation.getResult(e.getClass());
            if (result != null) {
                result.execute(request, response, e);
                return;
            } else {
                throw new RuntimeException(e);
            }
        }

        if (resultCode != null) {
            Result result = operation.getResult(resultCode);
            result.execute(request, response, action);
        }

    }

}

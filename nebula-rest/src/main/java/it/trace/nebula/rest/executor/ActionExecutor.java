package it.trace.nebula.rest.executor;

import it.trace.nebula.rest.annotations.HttpMethod;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.binder.PutContext;
import it.trace.nebula.rest.exception.ValidationException;
import it.trace.nebula.rest.helper.RequestHelper;
import it.trace.nebula.rest.resource.Operation;
import it.trace.nebula.rest.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionExecutor implements Executor {

    protected Object resourceId = null;

    protected Operation operation;

    protected String accpet;

    protected Result result;

    public ActionExecutor(Operation operation) {
        this.operation = operation;
    }

    public ActionExecutor(Operation operation, String accpet) {
        this.operation = operation;
        this.accpet = accpet;
        this.result = operation.getResult(accpet);
    }

    public ActionExecutor(Operation operation, String accpet, Result result) {
        this.operation = operation;
        this.accpet = accpet;
        this.result = result;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setResourceId(Object resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {

        // 校验
        // 执行
        // 捕获异常

        DataBinder<Object> dataBinder = operation.getDataBinder();
        Object[] input = null;
        if (dataBinder != null) {

            Context context;

            // TODO ======================================

            if (HttpMethod.PUT.equals(RequestHelper.getHttpMethod(request))) {
                context = new PutContext(request, (String) this.resourceId);
            } else {
                context = new Context(request, (String) this.resourceId);
            }

            // ===========================================

            input = dataBinder.bind(context, operation.getMethod());
        }

        Object action;
        try {
            action = operation.getOperationClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Object returnValue;
        try {
            returnValue = operation.getMethod().invoke(action, input);
            result.onSuccess(request, response, returnValue);
        } catch (Exception e) {
            e.printStackTrace();
            result.onFailure(request, response, e);
        }
    }

    protected void validate() throws ValidationException {

    }

    protected void invoke() {

    }

}

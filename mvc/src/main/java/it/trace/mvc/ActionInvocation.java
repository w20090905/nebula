package it.trace.mvc;

import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.DataBinder;
import it.trace.mvc.config.ResultConfig;
import it.trace.mvc.inject.ContainerManager;
import it.trace.mvc.mapper.ActionMapping;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionInvocation {

    protected final static Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    public void invoke(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {

        ActionConfig actionConfig = mapping.getActionConfig();

        Object actionInstance = ContainerManager.getInstance(actionConfig.getActionClass());

        DataBinder dataBinder = actionConfig.getDataBinder();
        dataBinder.bind(request, response, mapping.getParamters(), actionInstance);

        Object resultCode = null;
        try {
            resultCode = actionConfig.getMethod().invoke(actionInstance, EMPTY_OBJECT_ARRAY);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ResultConfig resultConfig = actionConfig.getResultConfig(resultCode);
        resultConfig.getTemplate().render(request, response, actionInstance);

    }

}

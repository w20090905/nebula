package it.trace.nebula.rest.result;

import it.trace.nebula.rest.result.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlResult extends ViewResult {

    public HtmlResult(Template template) {
        super(template);
    }

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response, Object data) {
        template.render(request, response, data);
    }

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, Throwable caught) {
        // TODO Auto-generated method stub

    }

}

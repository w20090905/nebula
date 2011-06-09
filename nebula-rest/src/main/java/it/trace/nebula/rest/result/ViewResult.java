package it.trace.nebula.rest.result;

import it.trace.nebula.rest.result.template.Template;

public abstract class ViewResult implements Result {

    protected Template template;

    public ViewResult(Template template) {
        this.template = template;
    }

}

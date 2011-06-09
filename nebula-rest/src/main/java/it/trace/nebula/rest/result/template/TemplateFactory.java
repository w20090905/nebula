package it.trace.nebula.rest.result.template;

public abstract class TemplateFactory {

    protected abstract void init();

    public abstract Template loadTemplate();

}

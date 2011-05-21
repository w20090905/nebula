package it.trace.mvc.config;

import it.trace.mvc.result.Template;

public abstract class TemplateFactory {

    private static TemplateFactory instance;
    public static TemplateFactory getTemplateFactory() {
        return instance;
    }

    public abstract Template loadTemplate(String path);

}

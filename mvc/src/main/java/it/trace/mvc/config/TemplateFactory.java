package it.trace.mvc.config;

public abstract class TemplateFactory {

    private static TemplateFactory instance;
    public static TemplateFactory getTemplateFactory() {
        return instance;
    }

    public abstract Template loadTemplate(String path);

}

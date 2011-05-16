package it.trace.mvc.inject;

import com.google.inject.Injector;

public class GuiceContainer implements Container {

    private static final long serialVersionUID = -6134908904927532509L;

    private Injector injector = null;

    public GuiceContainer() {
    }

    public GuiceContainer(Injector injector) {
        this.injector = injector;
    }

    public Injector getInjector() {
        return injector;
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
    public  <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }

}

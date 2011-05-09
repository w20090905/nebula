package it.trace.mvc;
import it.trace.mvc.mapper.ActionMapper;
import it.trace.mvc.mapper.RestfulActionMapper;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;


public class BeanFactory {

    private static Injector injector;

    static void create() {
        // TODO
        injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {

                bind(ActionMapper.class).to(RestfulActionMapper.class).in(Scopes.SINGLETON);
                bind(ActionInvocation.class).to(ActionInvocation.class).asEagerSingleton();

            }

        });
    }

    static void destroy() {
        injector = null;
    }

    public static <T> T getBean(final Class<T> clazz) {
        return injector.getInstance(clazz);
    }

}

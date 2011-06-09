package it.trace.nebula.rest.context.listener;

import it.trace.nebula.rest.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ContainerContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ApplicationContext context = ApplicationContext.getInstance();

        context.addModules(new Module() {
            @Override
            public void configure(Binder binder) {

            }
        });


        context.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}

package it.trace.listener;

import it.trace.nebula.rest.context.ApplicationContext;
import it.trace.nebula.rest.context.RESTfulConventionConfigBuilder;
import it.trace.nebula.rest.resource.Hierarchy;
import it.trace.nebula.rest.resource.Operation;
import it.trace.nebula.rest.resource.Resource;

import javax.servlet.ServletContextEvent;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ContainerContextListener extends it.trace.nebula.rest.context.listener.ContainerContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ApplicationContext context = RESTfulConventionConfigBuilder.createApplicationContext("it.trace.resources");



        for (Hierarchy h : context.getHierarchies().values()) {
            System.out.println(h.getName());
            for (Resource  r : h.getResources().values()) {
                System.out.println("  "  + r.getName());
                for (Operation o : r.getOperations().values()) {
                    System.out.println("    " + o.getName());
                }
            }
        }



        context.addModules(new Module() {
            @Override
            public void configure(Binder binder) {
            }
        });

        context.init();

    }

}

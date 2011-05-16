package it.trace.mvc.inject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.ClassUtils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceContainerContextListener implements ServletContextListener {

    private final static String MODULE_CLASS_PARAM_NAME = "guiceModuleClass";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String moduleClassParamValue = sce.getServletContext().getInitParameter(MODULE_CLASS_PARAM_NAME);

        List<Module> modules = new ArrayList<Module>();
        for (String className : moduleClassParamValue.split("\\s+")) {

            Class<?> clazz;
            try {
                clazz = ClassUtils.getClass(className);
            } catch (ClassNotFoundException e) {
                // TODO
                throw new RuntimeException("类\"" + className + "\"没有找到。");
            }

            if (!Module.class.isAssignableFrom(clazz)) {
                // TODO
                throw new RuntimeException("类\"" + className + "\"不是\"com.google.inject.Module\"的实现。");
            }

            Object instance;
            try {
                instance = clazz.newInstance();
            } catch (Exception e) {
                // TODO
                throw new RuntimeException("类\"" + className + "\"实例化时发生错误。");
            }

            modules.add((Module) instance);
        }

        Injector injector = Guice.createInjector(modules);
        ContainerManager.setContainer(new GuiceContainer(injector));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}

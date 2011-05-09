package it.trace.mvc.config;

import it.trace.mvc.PackageUtil;
import it.trace.mvc.config.builder.ConfigurationBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    public final static String ACTION_SUFFIX = "Action";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigurationBuilder.build();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    public static void main(String[] args) throws Exception {
        //        ClassFinder finder = new ClassFinder(Thread.currentThread().getContextClassLoader());
        //        List<Class> cl = finder.findClassesInPackage("", true);
        //
        //        for (Class c : cl) {
        //            System.out.println(c);
        //        }

        //        System.out.println(ContextLoaderListener.class.getClassLoader().getResource(""));

        for (String c : PackageUtil.findClassesInPackage("it")) {
            System.out.println(c);
        }
        System.out.println("end");

    }

}

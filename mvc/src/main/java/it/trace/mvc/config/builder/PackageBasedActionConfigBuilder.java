package it.trace.mvc.config.builder;

import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.NamespaceConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xbean.finder.ClassFinder;

public class PackageBasedActionConfigBuilder {

    public Collection<NamespaceConfig> builder(String rootPackage) {

        Map<Package, NamespaceConfig> configs = new HashMap<Package, NamespaceConfig>();

        for (@SuppressWarnings("rawtypes") Class c : findClassesInPackage(rootPackage)) {

            NamespaceConfig namespaceConfig;
            if (configs.containsKey(c.getPackage())) {
                namespaceConfig = configs.get(c.getPackage());
            } else {
                namespaceConfig = new NamespaceConfig(c.getPackage().getName().replaceAll("^" + rootPackage + "(?:\\.|)", "")); // TODO
                configs.put(c.getPackage(), namespaceConfig);
            }

            for (Method m : c.getDeclaredMethods()) {
                ActionConfig actionConfig = new ActionConfig(StringUtils.uncapitalize(c.getSimpleName()).replaceAll("Action$", "") + "." + m.getName(), m); // TODO
                namespaceConfig.addActionConfig(actionConfig);
            }

        }

        return configs.values();
    }

    @SuppressWarnings("rawtypes")
    private List<Class> findClassesInPackage(String packageName) {

        List<Class> classes = new ArrayList<Class>();

        ClassFinder finder;
        try {
            finder = new ClassFinder(Thread.currentThread().getContextClassLoader());
        } catch (Exception e) {
            // TODO
            throw new RuntimeException(e.getCause());
        }

        classes = finder.findClassesInPackage(packageName, true);


        return classes;
    }

    public static void main(String[] args) {
        PackageBasedActionConfigBuilder b = new PackageBasedActionConfigBuilder();
        Collection<NamespaceConfig> ncs = b.builder("it.trace.mvc");
        System.out.println(ncs.size());
        for (NamespaceConfig namespaceConfig : ncs) {
            for (ActionConfig actionConfig : namespaceConfig.getActionConfigs().values()) {
                System.out.println(namespaceConfig.getName() + "  :  " + actionConfig.getName());
            }
        }
    }

}

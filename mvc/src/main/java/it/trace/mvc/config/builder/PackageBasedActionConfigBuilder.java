package it.trace.mvc.config.builder;

import it.trace.mvc.config.ActionConfig;
import it.trace.mvc.config.DataBinder;
import it.trace.mvc.config.JspTemplate;
import it.trace.mvc.config.NamespaceConfig;
import it.trace.mvc.config.ResultConfig;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.xbean.finder.ClassFinder;

public class PackageBasedActionConfigBuilder {

    private String actionClassSuffix = "Action";

    public Collection<NamespaceConfig> builder(String rootPackage) {

        Map<Package, NamespaceConfig> configs = new LinkedHashMap<Package, NamespaceConfig>();

        for (@SuppressWarnings("rawtypes") Class c : findClassesInPackage(rootPackage)) {

            // TODO 独立成方法
            if (c.getSimpleName().length() <= actionClassSuffix.length() || !c.getSimpleName().endsWith(actionClassSuffix)) {
                continue;
            }

            NamespaceConfig namespaceConfig;
            if (configs.containsKey(c.getPackage())) {
                namespaceConfig = configs.get(c.getPackage());
            } else {
                // TODO 太长
                String namespace = c.getPackage().getName().substring(rootPackage.length()).replace('.', '/');
                namespaceConfig = new NamespaceConfig(namespace);
                configs.put(c.getPackage(), namespaceConfig);
            }

            for (Method m : c.getDeclaredMethods()) {

                // TODO 独立成方法
                if (!Modifier.isPublic(m.getModifiers())) {
                    continue;
                } else if (m.getParameterTypes().length > 0) {
                    continue;
                } else if (!String.class.isAssignableFrom(m.getReturnType())) {
                    continue;
                } else if (m.getName().startsWith("get")) {
                    continue;
                }

                String simpleActionName = StringUtils.uncapitalize(c.getSimpleName());
                // TODO 太长
                simpleActionName = simpleActionName.substring(0, simpleActionName.length() - actionClassSuffix.length());;

                ActionConfig actionConfig = new ActionConfig(simpleActionName + "/" + m.getName(), m);

                // TODO
                actionConfig.setDataBinder(new DataBinder() {

                    @Override
                    public void bind(HttpServletRequest request, HttpServletResponse response,
                            Map<String, Object> extraParams, Object instance) {
                        // TODO Auto-generated method stub

                    }
                });

                // TODO
                ResultConfig rc;
                rc = new ResultConfig();
                rc.setName("success");
                rc.setTemplate(new JspTemplate("/" + simpleActionName + "/" + m.getName() + ".jsp"));   // TODO
                actionConfig.addResultConfig(rc);

                namespaceConfig.addActionConfig(actionConfig);
            }

        }
        // =================================
        printAll(configs.values());
        // =================================

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

    public void setActionClassSuffix(String actionClassSuffix) {
        if (actionClassSuffix == null) {
            throw new RuntimeException("不能为 null");    // TODO
        }
        this.actionClassSuffix = actionClassSuffix;
    }

    public static void printAll(Collection<NamespaceConfig> ncs) {
        System.out.println("NamespaceConfig的总数 = " + ncs.size());
        System.out.println();
        for (NamespaceConfig namespaceConfig : ncs) {
            System.out.println("NamespaceConfig :" + namespaceConfig.getName());
            for (ActionConfig actionConfig : namespaceConfig.getActionConfigs().values()) {
                System.out.println("  " + actionConfig.getName());
            }
        }
    }

    public static void main(String[] args) {
        PackageBasedActionConfigBuilder b = new PackageBasedActionConfigBuilder();
        Collection<NamespaceConfig> ncs = b.builder("it.trace.mvc");
        printAll(ncs);
    }

}

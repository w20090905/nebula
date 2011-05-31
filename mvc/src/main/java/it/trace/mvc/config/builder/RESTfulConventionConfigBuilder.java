package it.trace.mvc.config.builder;

import it.trace.mvc.Constants;
import it.trace.mvc.binder.DataBinder;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.Namespace;
import it.trace.mvc.config.Operation;
import it.trace.mvc.config.Resource;
import it.trace.mvc.result.DisplayResult;
import it.trace.mvc.result.RedirectResult;
import it.trace.mvc.result.template.JspTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.xbean.finder.ClassFinder;

public class RESTfulConventionConfigBuilder {

    public final static String ACTION_PACKAGE_NAME = "action";
    public final static String ACTION_CLASS_SUFFIX = "Action";

    protected Configuration config;

    public RESTfulConventionConfigBuilder() {
        this.config = new Configuration();
    }

    public RESTfulConventionConfigBuilder(Configuration config) {
        this.config = config;
    }

    public Namespace addNamespace(String namespaceName) {
        Namespace n = new Namespace(namespaceName);
        config.addNamespace(n.getName(), n);
        return n;
    }

    public Namespace addNamespaceNotExists(String namespaceName) {
        Namespace n = config.getNamespace(namespaceName);
        return n == null ? addNamespace(namespaceName) : n;
    }

    public Resource addResource(Namespace namespace, String resourceName) {
        Resource r = new Resource(resourceName);
        namespace.addResource(r.getName(), r);
        return r;
    }

    public Resource addResource(Class<?> clazz) {

        String pkg = clazz.getPackage().getName();
        int subPkgPos = pkg.contains(ACTION_PACKAGE_NAME) ? pkg.lastIndexOf(ACTION_PACKAGE_NAME) + ACTION_PACKAGE_NAME.length() : -1;
        String namespaceNm = pkg.substring(subPkgPos == pkg.length() ? subPkgPos : subPkgPos + 1);
        Namespace namespace = addNamespaceNotExists(namespaceNm);

        String resourceNm = getResourceName(clazz.getSimpleName());
        Resource resource = addResource(namespace, resourceNm);

        for (Method m : getOperationMethods(clazz)) {
            addOperation(resource, m);
        }

        return resource;
    }

    private String getResourceName(String className) {
        if (className.length() > ACTION_CLASS_SUFFIX.length() && className.endsWith(ACTION_CLASS_SUFFIX)) {
            return StringUtils.uncapitalize(className.substring(0, className.length() - ACTION_CLASS_SUFFIX.length()));
        } else {
            return StringUtils.uncapitalize(className);
        }
    }

    private List<Method> getOperationMethods(Class<?> clazz) {

        List<Method> methods = new ArrayList<Method>();

        // 不遍历父类的方法
        for (Method m : clazz.getDeclaredMethods()) {
            if (isOperationMethod(m)) {
                methods.add(m);
            }
        }

        return methods;
    }

    private boolean isOperationMethod(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        } else if (method.getParameterTypes().length > 0) {
            return false;
        } else if (!String.class.isAssignableFrom(method.getReturnType())) {
            return false;
        } else if (method.getName().startsWith("get")) {
            return false;
        }
        return true;
    }

    public Operation addOperation(Resource resource, String operationName) {
        Operation o = new Operation(operationName);
        resource.addOperation(o.getName(), o);
        return o;
    }

    public Operation addOperation(Resource resource, Method method) {

        Operation o = new Operation(method);
        resource.addOperation(o.getName(), o);
        addDataBind(o);

        if ("list".equals(method.getName())) {
            o.addResult("success", new DisplayResult(new JspTemplate("/" + resource.getName() + "/list.jsp")));    // TODO
        } else if ("view".equals(method.getName())) {
            o.addResult("success", new DisplayResult(new JspTemplate("/" + resource.getName() + "/view.jsp")));    // TODO
        } else if ("editable".equals(method.getName())) {
            o.addResult("success", new DisplayResult(new JspTemplate("/" + resource.getName() + "/editable.jsp")));    // TODO
        } else if ("removable".equals(method.getName())) {
            o.addResult("success", new DisplayResult(new JspTemplate("/" + resource.getName() + "/removable.jsp")));    // TODO
        } else if ("editNew".equals(method.getName())) {
            o.addResult("success", new DisplayResult(new JspTemplate("/" + resource.getName() + "/editNew.jsp")));    // TODO
        } else if ("create".equals(method.getName())) {
            o.addResult("success", new RedirectResult(""));    // TODO
        } else if ("update".equals(method.getName())) {
            o.addResult("success", new RedirectResult("../"));    // TODO
        } else if ("remove".equals(method.getName())) {
            o.addResult("success", new RedirectResult("../"));    // TODO
        } else {

        }

        return o;
    }

    @SuppressWarnings("unchecked")
    public DataBinder<Object> addDataBind(Operation operation) {
        DataBinder<Object> db;
        try {
            // TODO 规则需要改
            Method m = operation.getOperationClass().getMethod("createDataBinder", Constants.EMPTY_CLASS_ARRAY);
            db = (DataBinder<Object>) m.invoke(null, Constants.EMPTY_OBJECT_ARRAY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        operation.setDataBinder(db);
        return db;
    }

    public Configuration getConfiguration() {
        return config;
    }

    public static Configuration createConfiguration(String ... packages) {

        RESTfulConventionConfigBuilder builder = new RESTfulConventionConfigBuilder();

        for (String pkg : packages) {
            for (Class<?> c : findClassesInPackage(pkg)) {
                if (c.getSimpleName().length() > ACTION_CLASS_SUFFIX.length() && c.getSimpleName().endsWith(ACTION_CLASS_SUFFIX)) {
                    builder.addResource(c);
                }
            }
        }

        return builder.getConfiguration();
    }

    @SuppressWarnings("rawtypes")
    private static List<Class> findClassesInPackage(String packageName) {

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
}

package it.trace.nebula.rest.context;

import it.trace.nebula.rest.annotations.MimeType;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.commons.ClassUtils;
import it.trace.nebula.rest.resource.Hierarchy;
import it.trace.nebula.rest.resource.Operation;
import it.trace.nebula.rest.resource.Resource;
import it.trace.nebula.rest.result.HtmlResult;
import it.trace.nebula.rest.result.RedirectResult;
import it.trace.nebula.rest.result.template.jsp.JspTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class RESTfulConventionConfigBuilder {

    public final static String ACTION_PACKAGE_NAME = "resources";
    public final static String ACTION_CLASS_SUFFIX = "Resource";

    protected ApplicationContext config;

    public RESTfulConventionConfigBuilder() {
        //        this.config = new ApplicationContext();
        this.config = ApplicationContext.getInstance();
    }

    public RESTfulConventionConfigBuilder(ApplicationContext config) {
        this.config = config;
    }

    public Hierarchy addHierarchy(String HierarchyName) {
        Hierarchy n = new Hierarchy(HierarchyName);
        config.addHierarchy(n.getName(), n);
        return n;
    }

    public Hierarchy addHierarchyNotExists(String HierarchyName) {
        Hierarchy n = config.getHierarchy(HierarchyName);
        return n == null ? addHierarchy(HierarchyName) : n;
    }

    public Resource addResource(Hierarchy Hierarchy, String resourceName) {
        Resource r = new Resource(resourceName);
        Hierarchy.addResource(r.getName(), r);
        return r;
    }

    public Resource addResource(Class<?> clazz) {

        String pkg = clazz.getPackage().getName();
        int subPkgPos = pkg.contains(ACTION_PACKAGE_NAME) ? pkg.lastIndexOf(ACTION_PACKAGE_NAME) + ACTION_PACKAGE_NAME.length() : -1;
        String HierarchyNm = pkg.substring(subPkgPos == pkg.length() ? subPkgPos : subPkgPos + 1);
        Hierarchy hierarchy = addHierarchyNotExists(HierarchyNm);

        String resourceNm = getResourceName(clazz.getSimpleName());
        Resource resource = addResource(hierarchy, resourceNm);

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
        //        if (!Modifier.isPublic(method.getModifiers())) {
        //            return false;
        //        } else if (method.getParameterTypes().length > 0) {
        //            return false;
        //        } else if (!String.class.isAssignableFrom(method.getReturnType())) {
        //            return false;
        //        } else if (method.getName().startsWith("get")) {
        //            return false;
        //        }
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
            o.addResult(MimeType.TEXT_HTML, new HtmlResult(new JspTemplate("/" + resource.getName() + "/list.jsp")));    // TODO
        } else if ("view".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new HtmlResult(new JspTemplate("/" + resource.getName() + "/view.jsp")));    // TODO
        } else if ("editable".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new HtmlResult(new JspTemplate("/" + resource.getName() + "/editable.jsp")));    // TODO
        } else if ("removable".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new HtmlResult(new JspTemplate("/" + resource.getName() + "/removable.jsp")));    // TODO
        } else if ("editNew".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new HtmlResult(new JspTemplate("/" + resource.getName() + "/editNew.jsp")));    // TODO
        } else if ("create".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new RedirectResult(""));    // TODO
        } else if ("update".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new RedirectResult("../"));    // TODO
        } else if ("remove".equals(method.getName())) {
            o.addResult(MimeType.TEXT_HTML, new RedirectResult("../"));    // TODO
        } else {

        }

        return o;
    }

    @SuppressWarnings("unchecked")
    public DataBinder<Object> addDataBind(Operation operation) {
        DataBinder<Object> db;
        try {
            // TODO 规则需要改
            Method m = operation.getOperationClass().getMethod("createDataBinder", new Class[0]);
            db = (DataBinder<Object>) m.invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        operation.setDataBinder(db);
        return db;
    }

    public ApplicationContext getApplicationContext() {
        return config;
    }

    public static ApplicationContext createApplicationContext(String ... packages) {

        RESTfulConventionConfigBuilder builder = new RESTfulConventionConfigBuilder();

        for (String pkg : packages) {
            for (Class<?> c : ClassUtils.getClasses(pkg)) {
                if (c.getSimpleName().length() > ACTION_CLASS_SUFFIX.length() && c.getSimpleName().endsWith(ACTION_CLASS_SUFFIX)) {
                    builder.addResource(c);
                }
            }
        }

        return builder.getApplicationContext();
    }
}

package it.trace.nebula.rest.context;

import it.trace.nebula.rest.resource.Hierarchy;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ApplicationContext {

    protected static volatile ApplicationContext context;

    protected Injector injector = null;

    protected Set<Module> modules = new HashSet<Module>();

    private final Map<Object, Hierarchy> hierarchies = new HashMap<Object, Hierarchy>();

    public ApplicationContext() {

    }

    public static ApplicationContext getInstance() {
        if (context == null) {
            synchronized (ApplicationContext.class) {
                if (context == null) {
                    context = new ApplicationContext();
                }
            }
        }
        return context;
    }

    public Hierarchy getHierarchy(String key) {
        return hierarchies.get(key);
    }

    public Map<Object, Hierarchy> getHierarchies() {
        return Collections.unmodifiableMap(hierarchies);
    }

    public void addHierarchy(Object key, Hierarchy hierarchy) {
        hierarchies.put(key, hierarchy);
    }

    public void removeHierarchy(Object key) {
        hierarchies.remove(key);
    }

    /* IoC Container **************************************/

    public <T> T getBean(Class<T> clazz) {
        assert injector != null;
        return injector.getInstance(clazz);
    }

    public ApplicationContext addModules(Module... modules) {
        Collections.addAll(this.modules, modules);
        return this;
    }

    public ApplicationContext addModules(Iterable<? extends Module> modules) {
        for (Module module : modules) {
            this.modules.add(module);
        }
        return this;
    }

    public void init() {
        synchronized (this) {
            injector = Guice.createInjector(modules);
        }
    }

}

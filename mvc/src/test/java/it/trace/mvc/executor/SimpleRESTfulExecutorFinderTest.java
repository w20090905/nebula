package it.trace.mvc.executor;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Configuration;
import it.trace.mvc.config.Namespace;
import it.trace.mvc.config.Operation;
import it.trace.mvc.config.Resource;

import org.junit.Assert;
import org.junit.Test;

public class SimpleRESTfulExecutorFinderTest {

    @Test
    public void find() {

        Configuration c = new Configuration();
        Namespace n = null;
        Resource r = null;

        n = new Namespace("normal");
        r = new Resource("person");
        r.addOperation("list", new DummyOperation("list"));
        r.addOperation("view", new DummyOperation("view"));
        r.addOperation("editable", new DummyOperation("editable"));
        r.addOperation("removable", new DummyOperation("removable"));
        r.addOperation("editNew", new DummyOperation("editNew"));
        r.addOperation("create", new DummyOperation("create"));
        r.addOperation("update", new DummyOperation("update"));
        r.addOperation("remove", new DummyOperation("remove"));
        r.addOperation("abc", new DummyOperation("abc"));
        n.addResource(r.getName(), r);
        r = new Resource("dept");
        r.addOperation("list", new DummyOperation("list"));
        n.addResource(r.getName(), r);
        c.addNamespace(n.getName(), n);

        n = new Namespace("another");
        r = new Resource("person");
        r.addOperation("list", new DummyOperation("list"));
        n.addResource(r.getName(), r);
        c.addNamespace(n.getName(), n);

        n = new Namespace("");
        r = new Resource("person");
        r.addOperation("list", new DummyOperation("list"));
        n.addResource(r.getName(), r);
        c.addNamespace(n.getName(), n);

        n = new Namespace("admin.dept");
        r = new Resource("person");
        r.addOperation("list", new DummyOperation("list"));
        n.addResource(r.getName(), r);
        c.addNamespace(n.getName(), n);

        SimpleRESTfulExecutorFinder ef = new SimpleRESTfulExecutorFinder();
        ef.init(c);
        DummyActionExecutor e;

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/person/");
        Assert.assertEquals("list", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/person/Tom");
        Assert.assertEquals("view", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/person/Tom!editable");
        Assert.assertEquals("editable", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/person/Tom!removable");
        Assert.assertEquals("removable", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/person/new");
        Assert.assertEquals("editNew", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.POST, "/normal/person/");
        Assert.assertEquals("create", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.PUT, "/normal/person/Tom");
        Assert.assertEquals("update", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.DELETE, "/normal/person/Tom");
        Assert.assertEquals("remove", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.DELETE, "/normal/person/Tom!abc");
        Assert.assertEquals("abc", e.getOperation().getName());
        Assert.assertEquals("Tom", e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.DELETE, "/normal/person/!abc");
        Assert.assertEquals("abc", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/normal/dept/");
        Assert.assertEquals("list", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/another/person/");
        Assert.assertEquals("list", e.getOperation().getName());
        Assert.assertNull(e.getId());

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/nothing/none/");
        Assert.assertNull(e);

        e = (DummyActionExecutor) ef.find(HttpMethod.GET, "/admin/dept/person");
        Assert.assertNull(e);

    }

    private class DummyOperation extends Operation {

        public DummyOperation(String name) {
            super(name);
        }

        public it.trace.mvc.executor.ActionExecutor getExecutor() {
            return new DummyActionExecutor(this, null);
        }

    }

    private class DummyActionExecutor extends ActionExecutor {

        public DummyActionExecutor(DummyOperation operation, Object object) {
            super(operation, object);
        }

        public it.trace.mvc.config.Operation getOperation() {
            return super.operation;
        }

        public String getId() {
            return super.id;
        }
    }

}

package it.trace.mvc.mapper;

import it.trace.mvc.zzz.config.Configuration;
import it.trace.mvc.zzz.config.NamespaceConfig;

import org.junit.Test;

public class RestfulActionMapperTest {

    @Test
    public void getActionMapping() throws Exception {

        Configuration config = new Configuration();
        config.addNamespaceConfig(new NamespaceConfig("/"));
        config.addNamespaceConfig(new NamespaceConfig("/first"));
        config.addNamespaceConfig(new NamespaceConfig("/first/second"));



    }



    //    @Test
    //    public void parseNameAndNamespace() throws Exception {
    //        System.out.println("/sd/".lastIndexOf("/"));
    //            Configuration config = new Configuration();
    //            config.addNamespaceConfig(new NamespaceConfig("/"));
    //            config.addNamespaceConfig(new NamespaceConfig("/first"));
    //            config.addNamespaceConfig(new NamespaceConfig("/first/second"));
    //
    //        ActionMapping am = new ActionMapping();
    //
    //        RestfulActionMapper testClass = new RestfulActionMapper();
    //        Method testMethod = testClass.getClass().getDeclaredMethod(
    //                "parseNameAndNamespace",
    //                String.class, Configuration.class, ActionMapping.class);
    //        testMethod.setAccessible(true);
    //
    //        testMethod.invoke(testClass, "person", config, am);
    //        Assert.assertEquals("/", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/person", config, am);
    //        Assert.assertEquals("/", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "person/", config, am);
    //        Assert.assertEquals("/", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/person/", config, am);
    //        Assert.assertEquals("/", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "first/person", config, am);
    //        Assert.assertEquals("/first", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/first/person", config, am);
    //        Assert.assertEquals("/first", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "first/person/", config, am);
    //        Assert.assertEquals("/first", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/first/person/", config, am);
    //        Assert.assertEquals("/first", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "first/second/person", config, am);
    //        Assert.assertEquals("/first/second", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/first/second/person", config, am);
    //        Assert.assertEquals("/first/second", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "first/second/person/", config, am);
    //        Assert.assertEquals("/first/second", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/first/second/person/", config, am);
    //        Assert.assertEquals("/first/second", am.getNamespace());
    //        Assert.assertEquals("person", am.getName());
    //
    //        testMethod.invoke(testClass, "/first/first", config, am);
    //        Assert.assertEquals("/first", am.getNamespace());
    //        Assert.assertEquals("first", am.getName());
    //
    //    }
    //
    //    @Test
    //    public void parseActionName() {
    //    }
}

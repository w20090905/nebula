//package it.trace.mvc.config.builder;
//
//import it.trace.mvc.zzz.config.NamespaceConfig;
//import it.trace.mvc.zzz.config.builder.PackageBasedActionConfigBuilder;
//import it.trace.mvc.zzz.refact.ActionConfig;
//import it.trace.mvc.zzz.result.Result;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//
//public class PackageBasedActionConfigBuilderTest {
//
//    public final static Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[] {};
//
//    @Test
//    public void builder() throws Exception {
//
//        String rootPackage = "it.trace.mvc.config.builder.testcase";
//
//        PackageBasedActionConfigBuilder testClass = new PackageBasedActionConfigBuilder();
//        Collection<NamespaceConfig> output = testClass.builder(rootPackage);
//
//        Assert.assertEquals(3, output.size());
//        List<NamespaceConfig> ncl = new ArrayList<NamespaceConfig>(output);
//
//        NamespaceConfig nc;
//        ActionConfig ac;
//        Result rc;
//
//        // NamespaceConfig -> /
//        nc = ncl.get(0);
//        Assert.assertEquals("", nc.getName());
//        Assert.assertEquals(4, nc.getActionConfigs().size());
//        // ActionConfig -> person/edit
//        ac = nc.getActionConfig("person/edit");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/edit", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.PersonAction.class.getDeclaredMethod("edit", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> person/list
//        ac = nc.getActionConfig("person/list");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/list", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.PersonAction.class.getDeclaredMethod("list", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/add
//        ac = nc.getActionConfig("dept/add");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/add", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.DeptAction.class.getDeclaredMethod("add", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/delete
//        ac = nc.getActionConfig("dept/delete");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/delete", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.DeptAction.class.getDeclaredMethod("delete", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//
//        // NamespaceConfig -> first
//        nc = ncl.get(1);
//        Assert.assertEquals("/first", nc.getName());
//        Assert.assertEquals(4, nc.getActionConfigs().size());
//        // ActionConfig -> person/edit
//        ac = nc.getActionConfig("person/edit");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/edit", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.PersonAction.class.getDeclaredMethod("edit", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> person/list
//        ac = nc.getActionConfig("person/list");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/list", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.PersonAction.class.getDeclaredMethod("list", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/add
//        ac = nc.getActionConfig("dept/add");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/add", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.DeptAction.class.getDeclaredMethod("add", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/delete
//        ac = nc.getActionConfig("dept/delete");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/delete", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.DeptAction.class.getDeclaredMethod("delete", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//
//        // NamespaceConfig -> second
//        nc = ncl.get(2);
//        Assert.assertEquals("/first/second", nc.getName());
//        Assert.assertEquals(4, nc.getActionConfigs().size());
//        // ActionConfig -> person/edit
//        ac = nc.getActionConfig("person/edit");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/edit", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.second.PersonAction.class.getDeclaredMethod("edit", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> person/list
//        ac = nc.getActionConfig("person/list");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("person/list", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.second.PersonAction.class.getDeclaredMethod("list", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/add
//        ac = nc.getActionConfig("dept/add");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/add", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.second.DeptAction.class.getDeclaredMethod("add", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//        // ActionConfig -> dept/delete
//        ac = nc.getActionConfig("dept/delete");
//        Assert.assertNotNull(ac);
//        Assert.assertEquals("dept/delete", ac.getName());
//        Assert.assertEquals(it.trace.mvc.config.builder.testcase.first.second.DeptAction.class.getDeclaredMethod("delete", EMPTY_CLASS_ARRAY), ac.getMethod());
//        Assert.assertNotNull(ac.getDataBinder());
//        Assert.assertEquals(1, ac.getResultConfigs().size());
//        rc = ac.getResultConfig("success");
//        Assert.assertEquals("success", rc.getName());
//        Assert.assertNotNull(rc);
//        Assert.assertNotNull(rc.getTemplate());
//
//    }
//
//}

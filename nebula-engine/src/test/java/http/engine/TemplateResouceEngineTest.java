package http.engine;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import http.io.Loader;
import http.io.Source;
import http.resource.StaticResource;
import http.resource.StaticTemplateResouce;
import http.resource.TypeTemplateResouce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.TypeDatastore;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.lang.TypeStandalone;
import nebula.server.Resource;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import util.InheritHashMap;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("deprecation")
public class TemplateResouceEngineTest extends TestCase {

	@Mock
	Configuration templateConfig;
	@Mock
	Broker<DataStore<Entity>> attributes;
	@Mock
	TypeLoader typeLoader;
	@Mock
	TypeDatastore typeBrokers;
	@Mock
	DataRepos dataWareHouse;
	@Mock
	Loader loader;

	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testResolve_loader_findSource() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader,typeBrokers, dataWareHouse, templateConfig);

		String path = "test.js";
		Source source = mock(Source.class);

		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve(path);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticResource.class, resource.getClass());
	}

	public final void testResolve_Static_No_Theme() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String path = "/theme/test.js";
		String reqPath = "/theme/test.js";

		Source source = mock(Source.class);
		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve(reqPath);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticResource.class, resource.getClass());
	}

	public final void testResolve_Static_Theme() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String path = "/theme/angular/test.js";
		String reqPath = "/theme/angular/test.js";

		Source source = mock(Source.class);
		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve(reqPath);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticResource.class, resource.getClass());
	}

	public final void testResolve_Static_Theme_1() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String reqPath = "/theme/angular/test.js";
		String path = "/theme/test.js";

		Source source = mock(Source.class);
		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve(reqPath);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticResource.class, resource.getClass());
	}

	public final void testResolve_Static_Theme_2() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String reqPath = "/theme/angular/test.js";
		String path = "/test.js";

		Source source = mock(Source.class);
		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve(reqPath);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticResource.class, resource.getClass());
	}

	public final void testResolve_StaticTemplateResouce() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String path = "test.js";
		when(loader.findSource(path)).thenReturn(null);

		Resource resource = templateResouceEngine.resolve(path);

		verify(loader, times(1)).findSource(path);

		assertNotNull(resource);
		assertEquals(StaticTemplateResouce.class, resource.getClass());
	}

	public final void testResolve_Type_1() throws IOException, ServletException {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, typeBrokers,dataWareHouse, templateConfig);

		String reqPath = "/theme/angularjs/Type-simple-list.html";

		@SuppressWarnings("unchecked")
		Broker<Type>  typeBroker = (Broker<Type>)mock(Broker.class);
		Type type = mock(Type.class);

		when(typeBrokers.getBroker("Type")).thenReturn(typeBroker);
		when(typeBroker.get()).thenReturn(type);

		when(type.getStandalone()).thenReturn(TypeStandalone.Master);
		when(type.getAttrs()).thenReturn(mock(InheritHashMap.class));

		Resource resource = templateResouceEngine.resolve(reqPath);

		assertNotNull(resource);
		assertEquals(TypeTemplateResouce.class, resource.getClass());

		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getMethod()).thenReturn("GET");

		TemplateLoader templateLoader = mock(TemplateLoader.class);
		when(templateConfig.getTemplateLoader()).thenReturn(templateLoader);
		Object templateResource = mock(Object.class);

		String path = "angularjs/master_simple_list.html.ftl";
		when(templateLoader.findTemplateSource(path)).thenReturn(templateResource);

		Template template = mock(Template.class);
		when(templateConfig.getTemplate(path)).thenReturn(template);

		ServletOutputStream outputStream = mock(ServletOutputStream.class);
		when(resp.getOutputStream()).thenReturn(outputStream);

		resource.handle(req, resp);

		verify(templateLoader, times(1)).findTemplateSource(path);
	}

	public final void testTemplateResouceEngine() {
		// fail("Not yet implemented"); // TODO
	}

	public final void testStaticResourceEngine() {
		// fail("Not yet implemented"); // TODO
	}

}

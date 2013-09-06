package http.engine;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import http.io.Loader;
import http.io.Source;
import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.TypeLoader;
import nebula.server.Resource;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
public class TemplateResouceEngineTest extends TestCase {

	@Mock
	Configuration templateConfig;
	@Mock
	Broker<DataStore<Entity>> attributes;
	@Mock
	TypeLoader typeLoader;
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

	public final void testResolve() {

		when(dataWareHouse.define(String.class, Entity.class, "Attribute")).thenReturn(attributes);
		TemplateResouceEngine templateResouceEngine = new TemplateResouceEngine(loader, typeLoader, dataWareHouse, templateConfig);

		String path = "dsfdsf";
		Source source = mock(Source.class);

		when(loader.findSource(path)).thenReturn(source);

		Resource resource = templateResouceEngine.resolve("");

		assertNotNull(resource);
	}

	public final void testTemplateResouceEngine() {
		fail("Not yet implemented"); // TODO
	}

	public final void testStaticResourceEngine() {
		fail("Not yet implemented"); // TODO
	}

}

package http.startup;

import freemarker.template.Configuration;
import http.engine.DataResouceEngine;
import http.engine.StaticResourceEngine;
import http.engine.TemplateResouceEngine;
import http.engine.TypeResouceEngine;
import http.server.BasicResourceContainer;
import httpd.io.ClassPathLoader;
import httpd.io.FileSystemLoader;
import httpd.io.Loader;
import httpd.io.MultiLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.impl.PersistenceMem;
import nebula.frame.DataWareHouse;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

@SuppressWarnings("deprecation")
public class ConfigModule extends AbstractModule {
	private Log log = LogFactory.getLog(this.getClass());

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	@Override
	protected void configure() {

		try {
			File root = null;
			URL url = this.getClass().getResource("/htdocs/WEB-INF/web.xml");
			if (url != null) {
				root = new File(url.getPath()).getParentFile().getParentFile();
			}

			if (root == null) {
				root = new File("htdocs");
			}

			if (log.isTraceEnabled()) {
				log.trace("Root path : " + root.getAbsolutePath());
			}

			if (!root.exists()) {
				throw new RuntimeException("cannot find htdocs");
			}

			EditableTypeLoader typeLoader = new EditableTypeLoader(new SystemTypeLoader(), new File(root, "WEB-INF/nebula"));

			this.bind(EditableTypeLoader.class).toInstance(typeLoader);
			this.bind(TypeLoader.class).toInstance(typeLoader);

			Loader loader = new MultiLoader(new ClassPathLoader(this.getClass().getClassLoader(), "htdocs"),
					new FileSystemLoader(root));
			this.bind(Loader.class).toInstance(loader);

			this.bind(DataWareHouse.class);
						
			this.bind(new TypeLiteral<Persistence<Entity>>(){}).toInstance(new PersistenceMem(typeLoader));
			
			Configuration freemarkerConfiguration = new Configuration();
			freemarkerConfiguration.setTemplateUpdateDelay(1);
			freemarkerConfiguration.setDirectoryForTemplateLoading(new File(root, "template"));
			this.bind(Configuration.class).toInstance(freemarkerConfiguration);

			this.bind(StaticResourceEngine.class).in(Singleton.class);
			this.bind(ResourceEngine.class).to(StaticResourceEngine.class).in(Singleton.class);
			this.bind(DataResouceEngine.class);

			this.bind(Container.class).to(BasicResourceContainer.class).in(Singleton.class);

			this.bind(new TypeLiteral<Configurable<BasicResourceContainer>>() {
			}).toInstance(new Configurable<BasicResourceContainer>() {
				StaticResourceEngine staticEngine;
				DataResouceEngine dataResouceEngine;
				TypeResouceEngine typeResouceEngine;
				// EditableStaticResourceEngine editableStaticEngine;
				TemplateResouceEngine templateResouceEngine;

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(DataResouceEngine engine) {
					this.dataResouceEngine = engine;
				}

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(TypeResouceEngine engine) {
					this.typeResouceEngine = engine;
				}

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(StaticResourceEngine engine) {
					this.staticEngine = engine;
				}

				//
				// @SuppressWarnings("unused")
				// @Inject
				// public void setEngine(EditableStaticResourceEngine engine){
				// this.editableStaticEngine=engine;
				// }

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(TemplateResouceEngine engine) {
					this.templateResouceEngine = engine;
				}

				@Override
				public void configure(BasicResourceContainer site) {
					site.register("*", staticEngine);
					site.register("/template/*", templateResouceEngine);
					site.register("/angularjs/*", templateResouceEngine);
					site.register("/d/Type/*", typeResouceEngine);
					site.register("/e/*", staticEngine);
					site.register("/d/*", dataResouceEngine);
					site.register("/d/Type", typeResouceEngine);
					site.register("/d/Type/*", typeResouceEngine);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}

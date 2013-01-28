package http.startup;

import freemarker.template.Configuration;
import http.engine.EntityResouceEngine;
import http.engine.SystemFunctionResouceEngine;
import http.engine.LongTermStaticResourceEngine;
import http.engine.StaticResourceEngine;
import http.engine.TemplateResouceEngine;
import http.engine.TypeResouceEngine;
import http.io.ClassPathLoader;
import http.io.FileSystemLoader;
import http.io.Loader;
import http.io.MultiLoader;
import http.resource.EntityFilterBuilder;
import http.resource.TypeFilterBuilder;
import http.server.BasicResourceContainer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import nebula.data.Entity;
import nebula.data.DataPersister;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.EntityDbDataPersister;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.TypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

@SuppressWarnings("deprecation")
public class ConfigModule extends AbstractModule {
	private Log log = LogFactory.getLog(this.getClass());

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	static final String PATH_OF_ROOT = "htdocs2";

	@Override
	protected void configure() {
		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String dburl = "jdbc:derby:db/nebula;create=true";
		String username = "user";
		String password = "password";

		try {
			File root = null;
			URL url = this.getClass().getResource("/"+ PATH_OF_ROOT + "/WEB-INF/web.xml");
			if (url != null) {
				root = new File(url.getPath()).getParentFile().getParentFile();
			}

			if (root == null) {
				root = new File(PATH_OF_ROOT);
			}

			if (log.isTraceEnabled()) {
				log.trace("Root path : " + root.getAbsolutePath());
			}

			if (!root.exists()) {
				throw new RuntimeException("cannot find " + PATH_OF_ROOT);
			}

			EditableTypeLoader typeLoader = new EditableTypeLoader(new SystemTypeLoader(), new File(root,
					"WEB-INF/nebula"));

			this.bind(EditableTypeLoader.class).toInstance(typeLoader);
			this.bind(TypeLoader.class).toInstance(typeLoader);

			Loader loader = new MultiLoader(new ClassPathLoader(this.getClass().getClassLoader(), PATH_OF_ROOT),
					new FileSystemLoader(root));
			this.bind(Loader.class).toInstance(loader);


			this.bind(EntityFilterBuilder.class);
			this.bind(TypeFilterBuilder.class);

			this.bind(DbConfiguration.class).toInstance(
					DbConfiguration.getEngine(driverclass, dburl, username, password));
			
			this.bind(new TypeLiteral<DataPersister<Entity>>() {
			}).to(EntityDbDataPersister.class).in(Scopes.SINGLETON);

			Configuration freemarkerConfiguration = new Configuration();
			freemarkerConfiguration.setDefaultEncoding("utf-8");
			freemarkerConfiguration.setEncoding(Locale.getDefault(),"utf-8");
			freemarkerConfiguration.setTemplateUpdateDelay(1);
			freemarkerConfiguration.setDirectoryForTemplateLoading(new File(root, "template"));
			this.bind(Configuration.class).toInstance(freemarkerConfiguration);

			this.bind(StaticResourceEngine.class).in(Singleton.class);
			this.bind(ResourceEngine.class).to(StaticResourceEngine.class).in(Singleton.class);
			this.bind(EntityResouceEngine.class);

			this.bind(Container.class).to(BasicResourceContainer.class).in(Singleton.class);

			this.bind(new TypeLiteral<Configurable<BasicResourceContainer>>() {
			}).toInstance(new Configurable<BasicResourceContainer>() {
				StaticResourceEngine staticEngine;
				EntityResouceEngine dataResouceEngine;
				TypeResouceEngine typeResouceEngine;
				// EditableStaticResourceEngine editableStaticEngine;
				TemplateResouceEngine templateResouceEngine;
				SystemFunctionResouceEngine prepareResouceEngine;
				private LongTermStaticResourceEngine longTermStaticEngine;

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(EntityResouceEngine engine) {
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
				
				@SuppressWarnings("unused")
				@Inject
				public void setEngine(LongTermStaticResourceEngine engine) {
					this.longTermStaticEngine = engine;
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

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(SystemFunctionResouceEngine engine) {
					this.prepareResouceEngine = engine;
				}

				@Override
				public void configure(BasicResourceContainer site) {
					site.register("*", staticEngine);
					site.register("/template/*", templateResouceEngine);
					site.register("/angularjs/*", templateResouceEngine);
					site.register("/angularjszice/*", templateResouceEngine);
					site.register("/e/*", staticEngine);
					site.register("/lib/*", longTermStaticEngine);
					site.register("/img/*", longTermStaticEngine);
					site.register("/images/*", longTermStaticEngine);
					site.register("/css/*", longTermStaticEngine);
					site.register("/d/*", dataResouceEngine);
					site.register("/f/*", prepareResouceEngine);
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

package http.startup;

import http.engine.DataResouceEngine;
import http.engine.StaticResourceEngine;
import http.server.BasicResourceContainer;
import httpd.io.ClassPathLoader;
import httpd.io.Loader;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import nebula.compiler.SystemTypeLoader;
import nebula.frame.DataWareHouse;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

import freemarker.template.Configuration;

public class ConfigModule extends AbstractModule {

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	@Override
	protected void configure() {		
		
		try {
			File tempalteFolder = new File(this.getClass().getResource("/htdocs/template/Type_list.ftl").getPath());

			SystemTypeLoader typeLoader = new SystemTypeLoader();
			this.bind(SystemTypeLoader.class).toInstance(typeLoader);
			
			DataWareHouse dataWareHouse = new DataWareHouse();
			dataWareHouse.add(typeLoader.getList());
			this.bind(DataWareHouse.class).toInstance(dataWareHouse);
			
			Configuration freemarkerConfiguration = new Configuration();
			freemarkerConfiguration.setTemplateUpdateDelay(1);
			freemarkerConfiguration.setDirectoryForTemplateLoading(tempalteFolder.getParentFile());
			this.bind(Configuration.class).toInstance(freemarkerConfiguration);
			
			this.bind(StaticResourceEngine.class).in(Singleton.class);
			this.bind(ResourceEngine.class).to(StaticResourceEngine.class).in(Singleton.class);			
			this.bind(DataResouceEngine.class);
			
			this.bind(Container.class).to(BasicResourceContainer.class).in(Singleton.class);
			
			this.bind(new TypeLiteral<Configurable<BasicResourceContainer>>(){}).toInstance(new Configurable<BasicResourceContainer>() {
				StaticResourceEngine staticEngine;
				DataResouceEngine dataResouceEngine;

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(DataResouceEngine engine){
					this.dataResouceEngine=engine;
				}
				
				@SuppressWarnings("unused")
				@Inject
				public void setEngine(StaticResourceEngine engine){
					this.staticEngine=engine;
				}
				
				@Override
				public void configure(BasicResourceContainer site) {
					site.register("/d/*", dataResouceEngine);	
					site.register("*", staticEngine);							
				}
			});
			ClassPathLoader loader = new ClassPathLoader(this.getClass().getClassLoader(), "htdocs");
			this.bind(Loader.class).toInstance(loader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

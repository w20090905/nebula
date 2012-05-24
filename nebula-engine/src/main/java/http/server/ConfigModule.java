package http.server;

import httpd.io.ClassPathLoader;
import httpd.io.Loader;

import javax.inject.Inject;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

public class ConfigModule extends AbstractModule {

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	@Override
	protected void configure() {		
		this.bind(StaticResourceEngine.class).in(Singleton.class);
		this.bind(ResourceEngine.class).to(StaticResourceEngine.class).in(Singleton.class);
		this.bind(Container.class).to(BasicResourceContainer.class).in(Singleton.class);
		this.bind(new TypeLiteral<Configure<BasicResourceContainer>>(){}).toInstance(new Configure<BasicResourceContainer>() {
			StaticResourceEngine engine;
			
			@SuppressWarnings("unused")
			@Inject
			public void setStaticResourceEngine(StaticResourceEngine engine){
				this.engine=engine;
			}
			
			@Override
			public void configure(BasicResourceContainer site) {
				site.register("", engine);				
			}
		});
		ClassPathLoader loader = new ClassPathLoader(this.getClass().getClassLoader(), "apps");
		this.bind(Loader.class).toInstance(loader);
		
	}

}

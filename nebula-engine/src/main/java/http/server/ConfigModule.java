package http.server;

import httpd.io.ClassPathLoader;
import httpd.io.Loader;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;

public class ConfigModule extends AbstractModule {

	static final String APP_DEFINE_PATH = "app_define_path";
	static final String DB_DRIVERCLASS = "db_driverclass";
	static final String DB_URL = "db_url";
	static final String DB_USERNAME = "db_username";
	static final String DB_PASSWORD = "db_password";

	@Override
	protected void configure() {		
		this.bind(Container.class).to(BasicResourceContainer.class);
		this.bind(ResourceEngine.class).to(StaticResourceEngine.class);
		ClassPathLoader loader = new ClassPathLoader(this.getClass().getClassLoader(), "apps");
		this.bind(Loader.class).toInstance(loader);
	}

}

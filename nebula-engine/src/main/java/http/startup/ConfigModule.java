package http.startup;

import http.engine.DataResouceEngine;
import http.engine.StaticResourceEngine;
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

import nebula.frame.DataWareHouse;
import nebula.lang.SystemTypeLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.ResourceEngine;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import freemarker.template.Configuration;

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
			URL url =  this.getClass().getResource("/htdocs/WEB-INF/web.xml");
			if(url!=null){
				root = new File(url.getPath()).getParentFile().getParentFile();
			}
			
			if(root==null){				
				root = new File("htdocs");
			}
			
			if(log.isTraceEnabled()){
				log.trace("Root path : " + root.getAbsolutePath() );
			}
			
			if(!root.exists()){
				throw new RuntimeException("cannot find htdocs");
			}
			
			SystemTypeLoader typeLoader = new SystemTypeLoader(new SystemTypeLoader());
			typeLoader.loadFolder(new File(root,"WEB-INF/nebula"));
			
			this.bind(SystemTypeLoader.class).toInstance(typeLoader);
			
			DataWareHouse dataWareHouse = new DataWareHouse();
			dataWareHouse.add(typeLoader.getList());
			this.bind(DataWareHouse.class).toInstance(dataWareHouse);
			
			Configuration freemarkerConfiguration = new Configuration();
			freemarkerConfiguration.setTemplateUpdateDelay(1);
			freemarkerConfiguration.setDirectoryForTemplateLoading(new File(root,"template"));
			this.bind(Configuration.class).toInstance(freemarkerConfiguration);
			
			this.bind(StaticResourceEngine.class).in(Singleton.class);
			this.bind(ResourceEngine.class).to(StaticResourceEngine.class).in(Singleton.class);			
			this.bind(DataResouceEngine.class);
			
			this.bind(Container.class).to(BasicResourceContainer.class).in(Singleton.class);
			
			this.bind(new TypeLiteral<Configurable<BasicResourceContainer>>(){}).toInstance(new Configurable<BasicResourceContainer>() {
				StaticResourceEngine staticEngine;
				DataResouceEngine dataResouceEngine;
				TypeResouceEngine typeResouceEngine;

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(DataResouceEngine engine){
					this.dataResouceEngine=engine;
				}

				@SuppressWarnings("unused")
				@Inject
				public void setEngine(TypeResouceEngine engine){
					this.typeResouceEngine=engine;
				}
				
				@SuppressWarnings("unused")
				@Inject
				public void setEngine(StaticResourceEngine engine){
					this.staticEngine=engine;
				}
				
				@Override
				public void configure(BasicResourceContainer site) {
					site.register("/d/Type/*", typeResouceEngine);	
					site.register("/d/*", dataResouceEngine);	
					site.register("/e/*", staticEngine);	
					site.register("*", staticEngine);							
				}
			});
			
			Loader loader =new MultiLoader(new ClassPathLoader(this.getClass().getClassLoader(), "htdocs"),new FileSystemLoader(root));
			this.bind(Loader.class).toInstance(loader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

package it.trace.mvc.config.builder;

import it.trace.mvc.config.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

public class ConfigurationFactory {

    private final static String FILTER_ACTION_PACKAGE_PARAM_NAME = "actionPackages";

    private String configFilePath = "./setting.properties";
    private Properties properties = null;

    private PackageBasedActionConfigBuilder builder = null;

    public void init() {

        this.properties = new Properties();

        InputStream configFile;
        configFile = ConfigurationFactory.class.getClassLoader().getResourceAsStream(configFilePath);
        if (configFile != null) {
            try {
                this.properties.load(configFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public Configuration create(FilterConfig filterConfig) {

        Configuration config = new Configuration();

        init();

        String actonPackages = filterConfig.getInitParameter(FILTER_ACTION_PACKAGE_PARAM_NAME);
        if (actonPackages != null) {
            builder = new PackageBasedActionConfigBuilder();
            for (String p : actonPackages.split("\\s")) {
                config.addNamespaceConfigs(builder.builder(p));
            }
        } else {
            // TODO throw Exception
        }

        return config;
    }

    public Configuration create(ServletConfig servletConfig) {

        Configuration config = new Configuration();

        init();

        String actonPackages = servletConfig.getInitParameter(FILTER_ACTION_PACKAGE_PARAM_NAME);
        if (actonPackages != null) {
            builder = new PackageBasedActionConfigBuilder();
            for (String p : actonPackages.split("\\s")) {
                config.addNamespaceConfigs(builder.builder(p));
            }
        } else {
            // TODO throw Exception
        }

        return config;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }



}

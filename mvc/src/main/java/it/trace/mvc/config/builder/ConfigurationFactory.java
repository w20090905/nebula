package it.trace.mvc.config.builder;

import it.trace.mvc.config.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.FilterConfig;

public class ConfigurationFactory {

    private final static String FILTER_ACTION_PACKAGE_PARAM_NAME = "actionPackage";

    private String configFilePath = "./setting.properties";
    private Properties properties = null;

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

        init();

        return null;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }



}

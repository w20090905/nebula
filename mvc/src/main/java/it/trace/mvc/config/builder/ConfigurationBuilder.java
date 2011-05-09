package it.trace.mvc.config.builder;

import it.trace.mvc.config.Configuration;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationBuilder {

    private final static String CONFIG_FILE_NAME = "setting.properties";

    static {

        Properties p = new Properties();
        try {
            p.load(ConfigurationBuilder.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Configuration build() {
        return null;
    }
}

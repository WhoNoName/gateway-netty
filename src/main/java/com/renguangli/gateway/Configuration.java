package com.renguangli.gateway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration
 *
 * @author renguangli 2018/11/8 22:14
 * @since JDK 1.8
 */
public class Configuration {

    private static Properties properties = new Properties();

    private static final String DEFAULT_CONFIG_FILE_NAME = "gateway.properties";

    public static void initConfig(String fileName){
        try (InputStream in = Configuration.class.getClassLoader().getResourceAsStream(fileName)){
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initConfig(){
        initConfig(DEFAULT_CONFIG_FILE_NAME);
    }

    public static String getConfig(String configName, String defaultValue) {
        String config = properties.getProperty(configName);
        return config != null ? config : defaultValue;
    }

    public static int intValue(String configName, int defaultValue) {
        String config = properties.getProperty(configName);
        return config != null ? Integer.valueOf(config) : defaultValue;
    }
}

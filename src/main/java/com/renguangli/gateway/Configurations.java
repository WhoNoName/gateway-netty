package com.renguangli.gateway;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurations {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configurations.class);

    private static PropertiesConfiguration configuration;

    static {
        try {
            LOGGER.info("Loading config {}", ConfigConstants.DEFAULT_CONFIG_NAME);
            configuration = new PropertiesConfiguration(ConfigConstants.DEFAULT_CONFIG_NAME);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesConfiguration getConfiguration() {
        return configuration;
    }

}

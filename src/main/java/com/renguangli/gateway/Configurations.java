package com.renguangli.gateway;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Configurations
 *
 * @author renguangli 2018/11/15 21:37
 * @since JDK 1.8
 */
public class Configurations extends PropertyPlaceholderConfigurer {

    private static Map<String, String> properties = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String value = props.getProperty(key.toString());
            properties.put(key.toString(), value);
        }
    }

    // static method for accessing context properties
    public static int getIntProperty(String name, int defaultValue) {
        String value = properties.get(name);
        return value == null ? defaultValue : Integer.valueOf(value);
    }

    public static void setProperties(String name, String value) {
        properties.put(name, value);
    }

    public static void updateProperties(String name, String value) {
        properties.put(name, value);
    }
}

package com.renguangli.gateway;

import com.renguangli.gateway.endpoint.EndpointFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Gateway
 *
 * @author renguangli 2018/11/7 14:35
 * @since JDK 1.8
 */
public class Gateway {

    private static final String CONFIG_LOCATION = "applicationContext.xml";

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

    private static int port = Configurations.getIntProperty("server.port", 8088);

    public void start() throws InterruptedException {
        this.initFilters();
        new GatewayServer().start(port);
    }

    private void initFilters() {
        HttpServerHandler httpServerHandler = applicationContext.getBean(HttpServerHandler.class);
        httpServerHandler.addFilter(new EndpointFilter());
    }


}

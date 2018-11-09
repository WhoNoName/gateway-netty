package com.renguangli.gateway;

/**
 * BootStrap
 *
 * @author renguangli 2018/11/7 14:35
 * @since JDK 1.8
 */
public class BootStrap {

    public static void main(String[] args) throws InterruptedException {
        new GatewayServer().start();
    }

}

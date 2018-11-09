package com.renguangli.gateway;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * HikariDataSourceFactory
 *
 * @author renguangli 2018/10/29 13:18
 * @since JDK 1.8
 */
public class HikariDataSourceFactory extends UnpooledDataSourceFactory {

    public HikariDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }

}

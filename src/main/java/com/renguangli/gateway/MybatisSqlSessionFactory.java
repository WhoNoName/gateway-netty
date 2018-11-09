package com.renguangli.gateway;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * MybatisSqlSessionFactory
 *
 * @author renguangli 2018/10/29 14:20
 * @since JDK 1.8
 */
public class MybatisSqlSessionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisSqlSessionFactory.class);

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            LOGGER.info("Mybatis init starting .");
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            Configuration configuration = sqlSessionFactory.getConfiguration();
            LOGGER.info("Mybatis init start completed.");
        } catch (IOException e) {
            LOGGER.error("Mybatis init failed", e.getMessage());
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

}

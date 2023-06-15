package com.zfx.multipleDataSourceDemo.configuration;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipledatasourcedemo.demos.configuration
 * @ClassName: DataSourceConfiguration
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:16
 * @Version: 1.0
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * @Primary 注解用于标识默认使用的DataSource Bean，因为有两个DataSource Bean，该注解可用于mysqlDataSource或pgsqlDataSource,
     *          但不能用于dynamicDataSource dynamicDataSource Bean, 否则会产生循环调用
     * @ConfigurationProperties 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
     * @return DataSource MySQL
     */
    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlDataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.standby")
    public DataSource mysqlDataSource2() {
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @param mysqlDataSource MySQL数据源1
     * @param mysqlDataSource2 MySQL数据源2
     *
     * @return 返回DynamicDataSource类型，否则@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource报错
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                        @Qualifier("mysqlDataSource2") DataSource mysqlDataSource2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("mysqlDataSource", mysqlDataSource);
        targetDataSources.put("mysqlDataSource2", mysqlDataSource2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 将MySQL数据源作为默认指定的数据源
        dynamicDataSource.setDefaultTargetDataSource(mysqlDataSource);
        return dynamicDataSource;
    }

    /**
     *  配置mybatis的mapper的位置
     * @param dynamicDataSource
     * @return SqlSessionFactoryBean
     * @author zhangfuxing
     * @date 2023/6/14 17:24
     * @throws
    */
    @Primary
    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean(
            @Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
        // MyBatis-plus不能用SqlSessionFactory
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将dynamicDataSource作为数据源则不能实现切换
        mybatisSqlSessionFactoryBean.setDataSource(dynamicDataSource);
        // 配置 MyBatis-plus
        mybatisSqlSessionFactoryBean
                .setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return mybatisSqlSessionFactoryBean.getObject();
    }

    /**
     *  注入DataSourceTransactionManager用于事务管理
     * @param dynamicDataSource
     * @return PlatformTransactionManager
     * @author zhangfuxing
     * @date 2023/6/14 17:25
     * @throws
    */
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}

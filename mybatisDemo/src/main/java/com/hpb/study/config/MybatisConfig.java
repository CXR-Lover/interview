package com.hpb.study.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname MybatisConfig
 * @Description TODO
 * @Date 2020/2/6 11:40
 * @Created by CXR
 */
@Configuration
public class MybatisConfig {

    //事务管理器
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        System.out.println("调用事务管理器");
        return new DataSourceTransactionManager(dataSource);
    }

    //提供查询操作
    @Bean
    @ConditionalOnMissingBean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        System.out.println("调用查询提供器");
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    //提供支持定制化mybatis操作
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        System.out.println("定制化mybatis操作");
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(Boolean.TRUE);
            configuration.setLogPrefix("com.hpb.study");
        };

    }
}

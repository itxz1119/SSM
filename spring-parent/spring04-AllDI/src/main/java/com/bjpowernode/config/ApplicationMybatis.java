package com.bjpowernode.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.bjpowernode")
@MapperScan("com.bjpowernode.mapper")
@PropertySource("classpath:jdbc.properties")
public class ApplicationMybatis {

    @Value("${jdbc.mysql.username}")
    private String user;
    @Value("${jdbc.mysql.password}")
    private String pass;
    @Value("${jdbc.mysql.url}")
    private String url;
    @Value("${jdbc.mysql.driver}")
    private String driver;

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(pass);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driver);
        return druidDataSource;
    }

    //分页插件
    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor=new PageInterceptor();
        Properties properties=new Properties();
        properties.setProperty("value", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        //mybatis整合分页插件↓
        Interceptor[] plugins={pageInterceptor()};
        sessionFactoryBean.setPlugins(plugins);
        //mybatis整合分页插件↑
        sessionFactoryBean.setTypeAliasesPackage("com.bjpowernode.entity");
        ResourcePatternResolver resourcePatternResolver=new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("mapper/*Mapper.xml");
        sessionFactoryBean.setMapperLocations(resources);
        return sessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }
}

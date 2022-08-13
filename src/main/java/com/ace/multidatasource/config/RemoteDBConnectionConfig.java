package com.ace.multidatasource.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mariadb.jdbc.MariaDbDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @Classname: RemoteDBConnectionConfig
 * @Date: 4/4/2022 3:28 AM
 * @Author: garlam
 * @Description:
 */


@Configuration
@PropertySource(value = {"classpath:application.properties"}, encoding = "UTF-8", name = "application.properties")
public class RemoteDBConnectionConfig {
    private static final Logger log = LogManager.getLogger(RemoteDBConnectionConfig.class.getName());

//    @Value("${spring.datasource.remote.jdbc-url}")
//    private String jdbcUrl;
//    @Value("${spring.datasource.remote.username}")
//    private String username;
//    @Value("${spring.datasource.remote.password}")
//    private String password;
//    @Value("${spring.datasource.remote.driver-class-name}")
//    private String driverClassName;

    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        this.url = PropertiesEnvironment.environment.getProperty("spring.datasource.remote.jdbc-url");
        return url;
    }

    public String getUserName() {
        this.userName = PropertiesEnvironment.environment.getProperty("spring.datasource.remote.username");
        return userName;
    }

    public String getPassword() {
        this.password = PropertiesEnvironment.environment.getProperty("spring.datasource.remote.password");
        return password;
    }

    @Bean
    // @ConfigurationProperties(prefix = "spring.datasource.remote")
    public DataSource remoteDatasource() throws SQLException {

        //JDBC连, 不支持多数据库的事务管理
        //return DataSourceBuilder.create().build();


        MysqlXADataSource xaDataSource = new MysqlXADataSource(); //for mysql
      //  MariaDbDataSource xaDataSource = new MariaDbDataSource();
        xaDataSource.setUrl(getUrl());
        xaDataSource.setUser(getUserName());
        xaDataSource.setPassword(getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("remoteDatasource");
        atomikosDataSourceBean.setMaxPoolSize(30);
        atomikosDataSourceBean.setMinPoolSize(5);
        return atomikosDataSourceBean;


    }

    @Bean
    public SqlSessionFactory remoteSqlSessionFactory(DataSource remoteDatasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(remoteDatasource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer remoteMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("remoteSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.ace.multidatasource.remote.dao");
        return mapperScannerConfigurer;
    }

    //单数据库的事务管理器, 用于数据update/insert失败时, 事务rollback
//    @Bean
//    public TransactionManager remoteDbTransactionManager(DataSource remoteDatasource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(remoteDatasource);
//        return dataSourceTransactionManager;
//    }

}


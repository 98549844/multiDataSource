package com.ace.multidatasource.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mariadb.jdbc.MariaDbDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @Classname: DBConnectionConfig
 * @Date: 4/4/2022 3:29 AM
 * @Author: garlam
 * @Description:
 */

@Configuration
public class DBConnectionConfig {
    private static final Logger log = LogManager.getLogger(DBConnectionConfig.class.getName());


//    @Value("${spring.datasource.local.jdbc-url}")
//    private String jdbcUrl;
//    @Value("${spring.datasource.local.username}")
//    private String username;
//    @Value("${spring.datasource.local.password}")
//    private String password;
//    @Value("${spring.datasource.local.driver-class-name}")
//    private String driverClassName;

    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        this.url = PropertiesEnvironment.environment.getProperty("spring.datasource.local.jdbc-url");
        return url;
    }

    public String getUserName() {
        this.userName = PropertiesEnvironment.environment.getProperty("spring.datasource.local.username");
        return userName;
    }

    public String getPassword() {
        this.password = PropertiesEnvironment.environment.getProperty("spring.datasource.local.password");
        return password;
    }


    @Bean
    // @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource datasource() throws SQLException {
        //JDBC连, 不支持多数据库的事务管理
        //return DataSourceBuilder.create().build();

        MysqlXADataSource xaDataSource = new MysqlXADataSource(); //for mysql
       // MariaDbDataSource xaDataSource = new MariaDbDataSource();
        xaDataSource.setUrl(getUrl());
        xaDataSource.setUser(getUserName());
        xaDataSource.setPassword(getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("datasource");
        atomikosDataSourceBean.setMaxPoolSize(30);
        atomikosDataSourceBean.setMinPoolSize(5);
        return atomikosDataSourceBean;

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.ace.multidatasource.local.dao");
        return mapperScannerConfigurer;
    }

    //单数据库的事务管理器, 用于数据update/insert失败时, 事务rollback
//    @Bean
//    public TransactionManager dbTransactionManager(DataSource datasource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(datasource);
//        return dataSourceTransactionManager;
//    }
}


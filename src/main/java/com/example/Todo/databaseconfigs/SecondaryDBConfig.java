package com.example.Todo.databaseconfigs;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = {
        "com.example.Todo.Repositories.write"
},
        entityManagerFactoryRef="secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDBConfig {
    private final DataSourceProperty dataSourceProperty;
    private final HibernateProperties hibernateProperties;

    public SecondaryDBConfig(
            @Qualifier("secondaryDatasourceProperties") final DataSourceProperty dataSourceProperty,
            @Qualifier("hibernateProperties") final HibernateProperties hibernateProperties) {
        this.dataSourceProperty = dataSourceProperty;
        this.hibernateProperties = hibernateProperties;
    }
    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        return dataSourceProperty.getDataSource();
    }

    @Bean(name ="secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory( @Qualifier("secondaryDataSource") final DataSource secondaryDataSource){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(secondaryDataSource);

        entityManagerFactoryBean.setPackagesToScan("com.example.Todo.Model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        final var jpaProperties = new Properties();
        dataSourceProperty.generateProperties(jpaProperties);
        hibernateProperties.generateProperties(jpaProperties);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean(name = "secondaryTransactionManager")
    public JpaTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") final EntityManagerFactory entityManagerFactory
    ) {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }




}

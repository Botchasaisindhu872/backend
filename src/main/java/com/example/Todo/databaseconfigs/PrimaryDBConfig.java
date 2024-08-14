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
        "com.example.Todo.Repositories.read"
},
        entityManagerFactoryRef="primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDBConfig {
    private final DataSourceProperty dataSourceProperty;
    private final HibernateProperties hibernateProperties;
    public PrimaryDBConfig(
            @Qualifier("primaryDatasourceProperties") final DataSourceProperty dataSourceProperty,
            @Qualifier("hibernateProperties") final HibernateProperties hibernateProperties) {
            this.dataSourceProperty = dataSourceProperty;
            this.hibernateProperties = hibernateProperties;
        }
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return dataSourceProperty.getDataSource();
    }

    @Bean(name ="primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory( @Qualifier("primaryDataSource") final DataSource primaryDataSource){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(primaryDataSource);

        entityManagerFactoryBean.setPackagesToScan("com.example.Todo.Model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        final var jpaProperties = new Properties();
        dataSourceProperty.generateProperties(jpaProperties);
        hibernateProperties.generateProperties(jpaProperties);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean(name = "primaryTransactionManager")
    public JpaTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") final EntityManagerFactory entityManagerFactory
    ) {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }




}

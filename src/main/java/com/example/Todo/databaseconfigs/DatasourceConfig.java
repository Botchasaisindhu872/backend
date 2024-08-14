package com.example.Todo.databaseconfigs;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    @Bean(name="primaryDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSourceProperty primaryDataSourceProperties() {
        return new DataSourceProperty();
    }

    @Bean(name="secondaryDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSourceProperty secondaryDataSourceProperties() {
        return new DataSourceProperty();
    }




}

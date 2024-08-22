package com.example.Todo.config;

import lombok.NonNull;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceProperty {
    private String url;
    private String username;
    private String password;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url(getUrl())
                .username(getUsername())
                .password(getPassword())
                .build();
    }
    public void generateProperties(@NonNull final Properties jpaProperties){

        jpaProperties.put("spring.datasource.url",getUrl());
        jpaProperties.put("spring.datasource.username",getUsername());
        jpaProperties.put("spring.datasource.password",getPassword());

    }

}

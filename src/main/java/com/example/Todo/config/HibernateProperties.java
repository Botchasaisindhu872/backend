package com.example.Todo.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HibernateProperties {
    private String ddlAuto = "none";
    private Boolean showSql = true;
    private String dialect="org.hibernate.dialect.MySQLDialect";

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDdlAuto() {
        return ddlAuto;
    }

    public void setDdlAuto(String ddlAuto) {
        this.ddlAuto = ddlAuto;
    }

    public Boolean getShowSql() {
        return showSql;
    }

    public void setShowSql(Boolean showSql) {
        this.showSql = showSql;
    }

    public void generateProperties(@NonNull final Properties jpaProperties) {
        jpaProperties.put("spring.jpa.properties.hibernate.format_sql", getShowSql());
        jpaProperties.put("spring.jpa.hibernate.ddl-auto", getDdlAuto());
        jpaProperties.put("hibernate.dialect", getDialect());



    }
}

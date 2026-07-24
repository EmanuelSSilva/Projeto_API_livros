package com.apilibrary.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Bean
    public DataSource hikariDataSource(){

        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);


        config.setMaximumPoolSize(10); //configurando o pool da aplicação maximo de conexões liberadas
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("library-db-pool"); // Nome do pool
        config.setMaxLifetime(600000); // 600 mil ms de conexão (10 minutos de vida)
        config.setConnectionTimeout(100000); //tentativa de conexão se nao conseguir lança erro
        config.setConnectionTestQuery("select 1"); // query de teste, verifica se ta conectando.

        return new HikariDataSource(config);
    }
}

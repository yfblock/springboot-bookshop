package edu.hasust.zez.books.beans;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import io.github.yfblock.yfSql.Sql.BasicDataSource;

@Component
public class DataSourceBuilder {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws ClassNotFoundException {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
package com.zhazha.webflux.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@Configuration
@EnableTransactionManagement
public class MysqlConfig {

    @Bean
    public ConnectionFactory connectionFactory(R2dbcProperties properties) {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.parse(properties.getUrl());
        com.github.jasync.sql.db.Configuration configuration = new com.github.jasync.sql.db.Configuration(
                properties.getUsername(),
                Objects.requireNonNull(options.getValue(ConnectionFactoryOptions.HOST)).toString(),
                Integer.parseInt(String.valueOf(options.getValue(ConnectionFactoryOptions.PORT))),
                properties.getPassword(),
                Objects.requireNonNull(options.getValue(ConnectionFactoryOptions.DATABASE)).toString());
        // 创建 JasyncConnectionFactory 对象
        return new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
    }

    @Bean
    public ReactiveTransactionManager transactionManager(R2dbcProperties properties) {
        return new R2dbcTransactionManager(this.connectionFactory(properties));
    }

}

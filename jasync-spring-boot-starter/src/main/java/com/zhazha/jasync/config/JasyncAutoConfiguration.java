package com.zhazha.jasync.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.Configuration;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

//@AutoConfiguration
@EnableConfigurationProperties(value = JasyncAutoConfiguration.class)
@ConditionalOnClass(ConnectionFactory.class)
@ConditionalOnProperty(prefix = "com.zhazha.jasync", name = "enabled", havingValue = "true")
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE + 12)
@EnableTransactionManagement
public class JasyncAutoConfiguration {

    @Bean
    ConnectionPool connectionFactory(R2dbcProperties properties) {
        JasyncConnectionFactory connectionFactory = createConnectionFactory(properties);
        R2dbcProperties.Pool pool = properties.getPool();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        ConnectionPoolConfiguration.Builder builder = ConnectionPoolConfiguration.builder(connectionFactory);
        map.from(pool.getMaxIdleTime()).to(builder::maxIdleTime);
        map.from(pool.getMaxLifeTime()).to(builder::maxLifeTime);
        map.from(pool.getMaxAcquireTime()).to(builder::maxAcquireTime);
        map.from(pool.getMaxCreateConnectionTime()).to(builder::maxCreateConnectionTime);
        map.from(pool.getInitialSize()).to(builder::initialSize);
        map.from(pool.getMaxSize()).to(builder::maxSize);
        map.from(pool.getValidationQuery()).whenHasText().to(builder::validationQuery);
        map.from(pool.getValidationDepth()).to(builder::validationDepth);
        return new ConnectionPool(builder.build());
    }

    @NotNull
    private static JasyncConnectionFactory createConnectionFactory(R2dbcProperties properties) {
        ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.parse(properties.getUrl());
        Configuration configuration = new Configuration(
                properties.getUsername(),
                Objects.requireNonNull(connectionFactoryOptions.getValue(ConnectionFactoryOptions.HOST)).toString(),
                (Integer) connectionFactoryOptions.getValue(ConnectionFactoryOptions.PORT),
                properties.getPassword(),
                (String) connectionFactoryOptions.getValue(ConnectionFactoryOptions.DATABASE));
        JasyncConnectionFactory connectionFactory = new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
        return connectionFactory;
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionPool factory) {
        return new R2dbcTransactionManager(factory);
    }

}

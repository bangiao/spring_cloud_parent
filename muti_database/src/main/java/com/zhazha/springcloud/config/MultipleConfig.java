package com.zhazha.springcloud.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class MultipleConfig {

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            entityManagerFactoryRef="jpaEntityManagerFactory",
            transactionManagerRef="jpaTransactionManager",
            basePackages= { "com.zhazha.springcloud.jpa.entity" }) //设置Repository所在位置
    static class FirstConfig {
        @Resource(name = "jpaDataSource")
        private DataSource jpaDataSource;
        @Resource(name = "jpaJpaProperties")
        private JpaProperties jpaProperties;
        @Resource
        private HibernateProperties hibernateProperties;

        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(jpaDataSource)
                    .packages("com.zhazha.springcloud.jpa.entity")
                    .persistenceUnit("jpaPersistenceUnit")
                    .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                    .build();
        }

        @Primary
        @Bean
        public EntityManager jpaEntityManager(EntityManagerFactoryBuilder builder) {
            return Objects.requireNonNull(jpaEntityManagerFactory(builder).getObject()).createEntityManager();
        }

        @Primary
        @Bean
        public PlatformTransactionManager jpaTransactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(Objects.requireNonNull(jpaEntityManagerFactory(builder).getObject()));
        }
        
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            entityManagerFactoryRef="securityEntityManagerFactory",
            transactionManagerRef="securityTransactionManager",
            basePackages= { "com.zhazha.springcloud.jpa.entity" }) //设置Repository所在位置
    static class SecondConfig {
        @Resource(name = "securityDataSource")
        private DataSource jpaDataSource;
        @Resource(name = "securityJpaProperties")
        private JpaProperties jpaProperties;
        @Resource
        private HibernateProperties hibernateProperties;

        @Bean
        public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(jpaDataSource)
                    .packages("com.zhazha.springcloud.security.entity")
                    .persistenceUnit("securityPersistenceUnit")
                    .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                    .build();
        }

        @Bean
        public EntityManager securityEntityManager(EntityManagerFactoryBuilder builder) {
            return Objects.requireNonNull(securityEntityManagerFactory(builder).getObject()).createEntityManager();
        }

        @Bean
        public PlatformTransactionManager securityTransactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(Objects.requireNonNull(securityEntityManagerFactory(builder).getObject()));
        }
    }

    @Configuration
    static class DatasourceConfig {
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.jpa")
        public DataSource jpaDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.security")
        public DataSource securityDataSource() {
            return DataSourceBuilder.create().build();
        }
    }

    @Configuration
    static class JpaConfig {
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.jpa.jpa")
        public JpaProperties jpaJpaProperties() {
            return new JpaProperties();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.jpa.security")
        public JpaProperties securityJpaProperties() {
            return new JpaProperties();
        }
    }

}

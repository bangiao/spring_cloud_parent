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
            entityManagerFactoryRef="firstEntityManagerFactory",
            transactionManagerRef="firstTransactionManager",
            basePackages= { "com.zhazha.springcloud.first.entity" }) //设置Repository所在位置
    static class FirstConfig {
        @Resource(name = "firstDataSource")
        private DataSource firstDataSource;
        @Resource(name = "firstJpaProperties")
        private JpaProperties jpaProperties;
        @Resource
        private HibernateProperties hibernateProperties;

        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(firstDataSource)
                    .packages("com.zhazha.springcloud.first.entity")
                    .persistenceUnit("firstPersistenceUnit")
                    .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                    .build();
        }

        @Primary
        @Bean
        public EntityManager firstEntityManager(EntityManagerFactoryBuilder builder) {
            return Objects.requireNonNull(firstEntityManagerFactory(builder).getObject()).createEntityManager();
        }

        @Primary
        @Bean
        public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(Objects.requireNonNull(firstEntityManagerFactory(builder).getObject()));
        }
        
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            entityManagerFactoryRef="secondEntityManagerFactory",
            transactionManagerRef="secondTransactionManager",
            basePackages= { "com.zhazha.springcloud.first.entity" }) //设置Repository所在位置
    static class SecondConfig {
        @Resource(name = "secondDataSource")
        private DataSource firstDataSource;
        @Resource(name = "secondJpaProperties")
        private JpaProperties jpaProperties;
        @Resource
        private HibernateProperties hibernateProperties;

        @Bean
        public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(firstDataSource)
                    .packages("com.zhazha.springcloud.second.entity")
                    .persistenceUnit("secondPersistenceUnit")
                    .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                    .build();
        }

        @Bean
        public EntityManager secondEntityManager(EntityManagerFactoryBuilder builder) {
            return Objects.requireNonNull(secondEntityManagerFactory(builder).getObject()).createEntityManager();
        }

        @Bean
        public PlatformTransactionManager secondTransactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(Objects.requireNonNull(secondEntityManagerFactory(builder).getObject()));
        }
    }

    @Configuration
    static class DatasourceConfig {
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.first")
        public DataSource firstDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.second")
        public DataSource secondDataSource() {
            return DataSourceBuilder.create().build();
        }
    }

    @Configuration
    static class JpaConfig {
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.jpa.first")
        public JpaProperties firstJpaProperties() {
            return new JpaProperties();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.jpa.second")
        public JpaProperties secondJpaProperties() {
            return new JpaProperties();
        }
    }

}

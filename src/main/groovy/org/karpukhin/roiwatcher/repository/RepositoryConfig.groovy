package org.karpukhin.roiwatcher.repository

import groovy.transform.CompileStatic
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Configuration for entity repositories
 *
 * @author Pavel Karpukhin
 * @since 13.12.16
 */
@CompileStatic
@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySources([
    @PropertySource('classpath:/application.properties'),
    @PropertySource(value = 'file:${roiWatcherHome}/application.properties', ignoreResourceNotFound = true)
])
class RepositoryConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        new PropertySourcesPlaceholderConfigurer()
    }

    @Bean
    @ConfigurationProperties('dataSource')
    DataSource dataSource() {
        new BasicDataSource()
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        def adapter = new HibernateJpaVendorAdapter()
        adapter.database = Database.POSTGRESQL

        def factory = new LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource()
        factory.jpaVendorAdapter = adapter
        factory.packagesToScan = 'org.karpukhin.roiwatcher.model'
        factory.jpaPropertyMap = ['hibernate.hbm2ddl.auto': 'validate']
        factory
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        new JpaTransactionManager(entityManagerFactory)
    }
}

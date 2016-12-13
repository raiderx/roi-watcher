package org.karpukhin.roiwatcher.repository

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * @author Pavel Karpukhin
 * @since 13.12.16
 */
@Configuration
class RepositoryConfig {

    @Bean
    DataSource dataSource() {
        new BasicDataSource()
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        def adapter = new HibernateJpaVendorAdapter()
        adapter.generateDdl = true
        adapter.showSql = true
        adapter.database = Database.POSTGRESQL

        def factory = new LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource()
        factory.jpaVendorAdapter =  adapter
        factory.packagesToScan = 'org.karpukhin.roiwatcher.model'
        factory
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        new JpaTransactionManager(entityManagerFactory)
    }

}

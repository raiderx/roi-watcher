package org.karpukhin.roiwatcher.repository

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.karpukhin.roiwatcher.model.PetitionPreview
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.PlatformTransactionManager

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.not
import static org.hamcrest.Matchers.nullValue
import static org.junit.Assert.assertThat


/**
 * @author Pavel Karpukhin
 * @since 13.12.16
 */
@CompileStatic
@ContextConfiguration(classes = Config.class)
@RunWith(SpringRunner.class)
class PetitionPreviewRepositoryTest {

    @Autowired
    private PetitionPreviewRepository previewRepository

    @Test
    void test() {
        def petition = new PetitionPreview(url: 'some-url', title: 'some-title')
        previewRepository.save(petition)

        assertThat(petition.id, is(not(nullValue())))
    }

    @EnableJpaRepositories
    static class Config {

        @Bean
        DataSource dataSource() {
            new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
        }

        @Bean
        LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            def adapter = new HibernateJpaVendorAdapter();
            adapter.generateDdl = true
            adapter.showSql = true
            adapter.database = Database.H2

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
}

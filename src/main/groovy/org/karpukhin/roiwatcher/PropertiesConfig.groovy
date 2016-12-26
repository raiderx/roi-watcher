package org.karpukhin.roiwatcher

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

/**
 * @author Pavel Karpukhin
 * @since 26.12.16
 */
@CompileStatic
@Configuration
@PropertySources([
        @PropertySource('classpath:/application.properties'),
        @PropertySource(value = 'file:${roiWatcherHome}/application.properties', ignoreResourceNotFound = true)
])
class PropertiesConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        new PropertySourcesPlaceholderConfigurer()
    }
}

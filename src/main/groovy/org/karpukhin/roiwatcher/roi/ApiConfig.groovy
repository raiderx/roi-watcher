package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.karpukhin.roiwatcher.PropertiesConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@Configuration
@EnableConfigurationProperties
@Import(PropertiesConfig.class)
class ApiConfig {

    @Bean
    @ConfigurationProperties('httpClientConnectionManager')
    PoolingHttpClientConnectionManager httpClientConnectionManager() {
        new PoolingHttpClientConnectionManager()
    }

    @Bean
    RoiApi roiApi(HttpClientConnectionManager httpClientConnectionManager) {
        new RoiApiImpl(new RoiStreamProviderImpl(httpClientConnectionManager))
    }
}

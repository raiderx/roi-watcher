package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@Configuration
class ApiConfig {

    @ConfigurationProperties('httpClientConnectionManager')
    PoolingHttpClientConnectionManager httpClientConnectionManager() {
        new PoolingHttpClientConnectionManager()
    }

    @Bean
    RoiApi roiApi() {
        new RoiApiImpl(new RoiStreamProviderImpl(httpClientConnectionManager()))
    }
}

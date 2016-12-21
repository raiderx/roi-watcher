package org.karpukhin.roiwatcher.roi

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@Configuration
class ApiConfig {

    @Bean
    RoiApi roiApi() {
        new RoiApiImpl(new RoiStreamProviderImpl())
    }
}

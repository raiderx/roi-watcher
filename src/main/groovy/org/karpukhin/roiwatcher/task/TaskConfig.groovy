package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

/**
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@ComponentScan('org.karpukhin.roiwatcher.task')
@Configuration
class TaskConfig {

    @Bean
    @ConfigurationProperties('updatePreviewsTaskScheduler')
    FixedRateScheduler updatePreviewsTaskScheduler(UpdatePreviewsTask updatePreviewsTask) {
        new FixedRateScheduler(updatePreviewsTask)
    }

    @Bean
    @ConfigurationProperties('taskScheduler')
    TaskScheduler taskScheduler() {
        new ThreadPoolTaskScheduler()
    }
}

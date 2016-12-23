package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

/**
 * Configuration for executing background tasks
 *
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@ComponentScan('org.karpukhin.roiwatcher.task')
@Configuration
class TaskConfig {

    /**
     * Declares bean performing task for updating list of previews.
     * Bean uses configuration properties starting with prefix updatePreviewsTaskScheduler.
     *
     * @param updatePreviewsTask task for updating list of previews
     * @return bean performing task for updating list of previews
     */
    @Bean
    @ConfigurationProperties('updatePreviewsTaskScheduler')
    FixedRateScheduler updatePreviewsTaskScheduler(UpdatePreviewsTask updatePreviewsTask) {
        new FixedRateScheduler(updatePreviewsTask)
    }

    /**
     * Declares bean for scheduling different tasks.
     * Bean uses configuration properties starting with prefix taskScheduler.
     *
     * @return bean for scheduling different tasks.
     */
    @Bean
    @ConfigurationProperties('taskScheduler')
    TaskScheduler taskScheduler() {
        new ThreadPoolTaskScheduler()
    }
}

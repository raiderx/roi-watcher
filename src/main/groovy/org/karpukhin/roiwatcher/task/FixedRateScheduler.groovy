package org.karpukhin.roiwatcher.task

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.Trigger
import org.springframework.scheduling.TriggerContext

import javax.annotation.PostConstruct
import java.text.SimpleDateFormat

import static org.springframework.util.Assert.notNull

/**
 * @author Pavel Karpukhin
 * @since 21.12.16
 */
@CompileStatic
@Slf4j
class FixedRateScheduler {

    private static final String TIME_FORMAT = "HH:mm:ss";

    private long interval = 1000

    private long initialDelay = 0

    private final Runnable task

    @Autowired
    private TaskScheduler scheduler

    FixedRateScheduler(Runnable task) {
        notNull(task, 'Parameter \'task\' can not be null')
        this.task = task
    }

    @PostConstruct
    void postConstruct() {
        def trigger = new Trigger() {
            @Override
            Date nextExecutionTime(TriggerContext triggerContext) {
                return FixedRateScheduler.this.nextExecutionTime(triggerContext)
            }
        }
        scheduler.schedule(task, trigger)
    }

    long getInterval() {
        interval
    }

    void setInterval(long interval) {
        this.interval = interval
    }

    long getInitialDelay() {
        initialDelay
    }

    void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay
    }

    private Date nextExecutionTime(TriggerContext triggerContext) {
        def last = triggerContext.lastScheduledExecutionTime()
        def next =  new Date(last == null ? (System.currentTimeMillis() + initialDelay) : (last.time + interval))
        if (log.infoEnabled) {
            log.info('Next time task will be executed at {}', new SimpleDateFormat(TIME_FORMAT).format(next))
        }
        next
    }
}

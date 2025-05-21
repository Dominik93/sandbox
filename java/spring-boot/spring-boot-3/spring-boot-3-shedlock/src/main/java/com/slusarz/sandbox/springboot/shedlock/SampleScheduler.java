package com.slusarz.sandbox.springboot.shedlock;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
public class SampleScheduler {

    @Scheduled(fixedRateString = "1000")
    @SchedulerLock(name = "sample", lockAtLeastFor = "PT30S", lockAtMostFor = "PT1M")
    void schedule() {
        log.info("Execute {}", LocalDateTime.now());
    }

}

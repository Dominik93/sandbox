package com.slusarz.sandbox.springboot.shedlock;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.inmemory.InMemoryLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public LockProvider lockProvider() {
        return new InMemoryLockProvider();
    }

    @Bean
    SampleScheduler sampleScheduler() {
        return new SampleScheduler();
    }

}
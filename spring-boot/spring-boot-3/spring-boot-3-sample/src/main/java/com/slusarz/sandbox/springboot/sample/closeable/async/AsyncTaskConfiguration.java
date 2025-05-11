package com.slusarz.sandbox.springboot.sample.closeable.async;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ConditionalOnProperty(value = "spring.async-task.enabled", havingValue = "true")
public class AsyncTaskConfiguration {

    @Bean
    AsyncTask asyncTask() {
        return new AsyncTask();
    }

    @Bean
    AsyncTaskInitializer asyncTaskInitializer(AsyncTask asyncTask) {
        return new AsyncTaskInitializer(asyncTask);
    }

}

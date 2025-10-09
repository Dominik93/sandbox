package com.slusarz.sandbox.springboot.testlisteners.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SampleService sampleService() {
        return new SampleService();
    }

}

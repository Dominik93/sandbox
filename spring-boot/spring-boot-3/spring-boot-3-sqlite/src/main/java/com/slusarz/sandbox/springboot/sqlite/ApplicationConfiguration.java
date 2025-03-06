package com.slusarz.sandbox.springboot.sqlite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SampleService sampleService(SampleRepository sampleRepository) {
        return new SampleService(sampleRepository);
    }

    @Bean
    ApplicationInitializer applicationInitializer(SampleService sampleService) {
        return new ApplicationInitializer(sampleService);
    }


}

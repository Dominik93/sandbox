package com.slusarz.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

    @Bean
    ExtendedServerRequestObservationConvention extendedServerRequestObservationConvention() {
        return new ExtendedServerRequestObservationConvention();
    }

    @Bean
    ExtendedDefaultClientRequestObservationConvention extendedDefaultClientRequestObservationConvention() {
        return new ExtendedDefaultClientRequestObservationConvention();
    }

}
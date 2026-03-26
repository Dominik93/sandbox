package com.slusarz.sandbox.springboot.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(group = "sample", types = {SampleService.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RestClientHttpServiceGroupConfigurer baseUrlGroupConfigurer() {
        return groups -> {
            groups.filterByName("sample").forEachClient((group, builder) ->
                    builder.baseUrl("https://sample.com"));
        };
    }


    @Bean
    RestClientHttpServiceGroupConfigurer apiVersionInserterGroupConfigurer() {
        return groups -> {
            groups.filterByName("sample").forEachClient((group, builder) ->
                    builder.apiVersionInserter(ApiVersionInserter.builder()
                            .useHeader("x-api").build()));
        };
    }


}
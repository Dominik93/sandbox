package com.slusarz.sandbox.springboot.starter.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StarterConfiguration {

    @PostConstruct
    void starterConfiguration() {
        log.info("Loaded starter configuration");
    }

}

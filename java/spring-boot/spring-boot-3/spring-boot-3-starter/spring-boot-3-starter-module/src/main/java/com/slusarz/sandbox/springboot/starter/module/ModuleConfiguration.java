package com.slusarz.sandbox.springboot.starter.module;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ModuleConfiguration {

    @PostConstruct
    void module() {
        log.info("Loaded module");
    }


}

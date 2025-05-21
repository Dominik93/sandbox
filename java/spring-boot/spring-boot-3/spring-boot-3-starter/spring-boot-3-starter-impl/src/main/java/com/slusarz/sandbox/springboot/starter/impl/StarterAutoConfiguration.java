package com.slusarz.sandbox.springboot.starter.impl;

import com.slusarz.sandbox.springboot.starter.api.ModuleAutoConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

@Slf4j
@ModuleAutoConfiguration(name = "sample")
@Import({StarterConfiguration.class})
public class StarterAutoConfiguration {

    @PostConstruct
    void starter() {
        log.info("Loaded starter");
    }

}

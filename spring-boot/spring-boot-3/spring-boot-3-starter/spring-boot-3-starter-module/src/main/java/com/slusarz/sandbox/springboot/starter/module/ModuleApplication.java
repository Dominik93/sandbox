package com.slusarz.sandbox.springboot.starter.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class ModuleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ModuleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }


}

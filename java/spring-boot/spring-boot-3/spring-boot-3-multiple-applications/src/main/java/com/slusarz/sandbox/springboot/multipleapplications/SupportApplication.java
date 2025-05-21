package com.slusarz.sandbox.springboot.multipleapplications;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SupportApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SupportApplication.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }

}
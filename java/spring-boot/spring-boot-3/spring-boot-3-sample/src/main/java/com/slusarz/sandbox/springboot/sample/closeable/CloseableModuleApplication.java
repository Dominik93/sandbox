package com.slusarz.sandbox.springboot.sample.closeable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CloseableModuleApplication {

    public static void main(String[] args) {
        CloseableSpringApplicationBuilder.closeOn(CloseableSpringApplicationBuilder.NONE_WEB_TYPE)
                .sources(CloseableModuleApplication.class)
                .run(args);
    }


}

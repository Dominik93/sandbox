package com.slusarz.sandbox.springboot.restclient;

import com.slusarz.sandbox.springboot.restclient.examples.error.ErrorHandling;
import com.slusarz.sandbox.springboot.restclient.examples.header.Headers;
import com.slusarz.sandbox.springboot.restclient.examples.log.Log;
import com.slusarz.sandbox.springboot.restclient.examples.method.Method;
import com.slusarz.sandbox.springboot.restclient.examples.multiresponse.MultiResponse;
import com.slusarz.sandbox.springboot.restclient.examples.timeout.Timeout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Method get() {
        return new Method();
    }

    @Bean
    ErrorHandling errorHandling() {
        return new ErrorHandling();
    }

    @Bean
    Headers headers() {
        return new Headers();
    }

    @Bean
    Log log() {
        return new Log();
    }

    @Bean
    MultiResponse multiResponse() {
        return new MultiResponse();
    }

    @Bean
    Timeout timeout() {
        return new Timeout();
    }

}
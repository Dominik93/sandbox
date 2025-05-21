package com.slusarz.sandbox.springboot.timezone;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class Application {

    @Value("${configuration.application-time-zone}")
    String timeZone;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        log.info("Set time zone to {}, so now is {}", timeZone, LocalDateTime.now());
    }

}
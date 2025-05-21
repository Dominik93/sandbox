package com.slusarz.sandbox.springboot.restclient.examples.method;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Request {

    private String sample;

    private LocalDateTime dateTime;

}

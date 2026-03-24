package com.slusarz.sandbox.springboot.restclient;

import org.springframework.web.service.annotation.GetExchange;

public interface SampleService {
    @GetExchange("/sample")
    String getSamples();

}

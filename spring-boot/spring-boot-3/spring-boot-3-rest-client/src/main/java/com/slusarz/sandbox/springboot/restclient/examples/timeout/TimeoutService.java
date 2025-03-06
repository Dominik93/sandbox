package com.slusarz.sandbox.springboot.restclient.examples.timeout;

import org.springframework.web.service.annotation.PostExchange;

public interface TimeoutService {

    @PostExchange("/timeout/5")
    void timeout5s();

    @PostExchange("/timeout/1")
    void timeout1s();
}

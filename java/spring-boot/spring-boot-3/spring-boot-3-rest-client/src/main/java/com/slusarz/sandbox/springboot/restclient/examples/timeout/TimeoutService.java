package com.slusarz.sandbox.springboot.restclient.examples.timeout;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.PostExchange;

public interface TimeoutService {

    @PostExchange("/timeout/{time}")
    void timeout(@PathVariable("time") String time);

}

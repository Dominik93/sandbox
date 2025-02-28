package com.slusarz.sandbox.springboot.restclient.examples.log;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface LogService {

    @GetExchange("/log/{id}")
    LogResponse pathParam(@PathVariable("id") String id);

    @GetExchange("/log")
    LogResponse requestParam(@RequestParam("id") String id);

    @PostExchange("/log")
    void body(@RequestBody BodyRequest request);
}

package com.slusarz.sandbox.springboot.restclient.examples.method;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface MethodService {
    @GetExchange("/sample")
    GetResponse getSamples();
    @GetExchange("/sample/{id}")
    GetResponse getSamplesById(@PathVariable("id") String id);

    @DeleteExchange("/sample/{id}")
    void deleteSamplesById(@PathVariable("id") String id);

    @PutExchange("/sample/{id}")
    void putSamplesById(@PathVariable("id") String id, @RequestBody Request body);

}

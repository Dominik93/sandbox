package com.slusarz.sandbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@Slf4j
@RestController
public class SampleHttpEndpoint {

    @Autowired
    private RestClient client;

    @GetMapping("/sample")
    String sample() {
        return client.get().uri("https://jsonplaceholder.typicode.com/posts/1").retrieve().toEntity(String.class).getBody();
    }

}

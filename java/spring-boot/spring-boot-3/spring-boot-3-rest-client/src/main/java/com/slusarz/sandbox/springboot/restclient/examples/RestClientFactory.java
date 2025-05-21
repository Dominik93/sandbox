package com.slusarz.sandbox.springboot.restclient.examples;

import org.springframework.web.client.RestClient;

public class RestClientFactory {
    public static RestClient.Builder create() {
        return RestClient.builder().baseUrl("http://localhost:8000/");
    }

}

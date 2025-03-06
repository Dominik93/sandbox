package com.slusarz.sandbox.springboot.restclient.examples.timeout;

import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

public class Timeout {

    private TimeoutService service;

    public Timeout() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(Duration.ofSeconds(2));
        requestFactory.setReadTimeout(Duration.ofSeconds(2));
        RestClient restClient = RestClientFactory.create()
                .requestFactory(requestFactory)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        this.service = factory.createClient(TimeoutService.class);
    }

    public void timeout() {
        service.timeout5s();
    }

    public void noTimeout() {
        service.timeout1s();
    }


}

package com.slusarz.sandbox.springboot.restclient.examples.log;

import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
public class Log {
    private LogService service;

    public Log() {
        RestClient restClient = RestClientFactory.create()
                .requestInterceptor(new LogClientHttpRequestInterceptor())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(LogService.class);
    }

    public void pathParam(String id) {
        service.pathParam(id);
    }

    public void requestParam(String id) {
        service.requestParam(id);
    }

    public void body(String id) {
        BodyRequest bodyRequest = new BodyRequest();
        bodyRequest.setId(id);
        service.body(bodyRequest);
    }
}

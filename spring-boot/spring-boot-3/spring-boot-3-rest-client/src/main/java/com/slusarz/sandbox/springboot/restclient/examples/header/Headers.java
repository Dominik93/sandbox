package com.slusarz.sandbox.springboot.restclient.examples.header;

import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class Headers {
    private HeaderService service;

    public Headers() {
        RestClient restClient = RestClientFactory.create()
                .defaultHeaders(httpHeaders -> httpHeaders.add("X-DEFAULT-SAMPLE", "X-DEFAULT-SAMPLE-VALUE"))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(HeaderService.class);
    }

    public void defaultHeader() {
        service.defaultHeader();
    }

    public void customHeader(String value) {
        service.customHeader(value);
    }

}

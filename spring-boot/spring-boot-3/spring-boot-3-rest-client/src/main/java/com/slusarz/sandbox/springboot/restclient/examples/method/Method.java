package com.slusarz.sandbox.springboot.restclient.examples.method;

import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@AllArgsConstructor
public class Method {

    private MethodService service;

    public Method() {
        RestClient restClient = RestClientFactory.create().build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(MethodService.class);
    }

    public List<String> get() {
        return service.getSamples().getSample();
    }

    public List<String> get(String id) {
        return service.getSamplesById(id).getSample();
    }

    public void delete(String id) {
        service.deleteSamplesById(id);
    }

    public void put(String id) {
        Request request = new Request();
        request.setSample(id);
        service.putSamplesById(id, request);
    }


}

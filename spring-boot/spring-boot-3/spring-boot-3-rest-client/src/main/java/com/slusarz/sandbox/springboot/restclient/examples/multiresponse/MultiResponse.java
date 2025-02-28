package com.slusarz.sandbox.springboot.restclient.examples.multiresponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class MultiResponse {

    private RestClient restClient;

    private MultiResponseHttpClientConverter multiResponseHttpClientConverter;

    public MultiResponse() {
        restClient = RestClientFactory.create().build();
        multiResponseHttpClientConverter = MultiResponseHttpClientConverter.builder()
                .objectMapper(new ObjectMapper())
                .defaultConverter(SuccessResponse.class)
                .converter(HttpStatusCode.valueOf(422), Error422Response.class)
                .converter(HttpStatusCode.valueOf(500), Error500Response.class)
                .build();
    }

    public Object multiResponse(String code) {
        return restClient.get().uri("/multi-response/" + code).exchange(multiResponseHttpClientConverter);
    }


}

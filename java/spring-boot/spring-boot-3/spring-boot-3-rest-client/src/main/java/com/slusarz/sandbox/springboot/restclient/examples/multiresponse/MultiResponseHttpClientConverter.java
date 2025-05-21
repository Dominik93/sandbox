package com.slusarz.sandbox.springboot.restclient.examples.multiresponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Singular;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.Map;

@Builder
public class MultiResponseHttpClientConverter implements RestClient.RequestHeadersSpec.ExchangeFunction {

    @Singular
    private Map<HttpStatusCode, Class> converters;

    private Class defaultConverter;

    private ObjectMapper objectMapper;

    @Override
    public Object exchange(HttpRequest clientRequest, RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) throws IOException {
        return convert(response, converters.getOrDefault(response.getStatusCode(), defaultConverter));
    }

    private Object convert(RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse responseEntity, Class type) {
        try {
            return objectMapper.readValue(responseEntity.getBody(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

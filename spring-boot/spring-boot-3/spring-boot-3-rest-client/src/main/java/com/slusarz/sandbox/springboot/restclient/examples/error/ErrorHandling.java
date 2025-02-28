package com.slusarz.sandbox.springboot.restclient.examples.error;

import com.slusarz.sandbox.springboot.restclient.examples.RestClientFactory;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.io.IOException;
import java.net.URI;

public class ErrorHandling {
    private ErrorService service;

    public ErrorHandling() {
        RestClient restClient = RestClientFactory.create()
                .defaultStatusHandler(new Status422ResponseErrorHandler())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(ErrorService.class);
    }

    public void error422() {
        service.get422Error();
    }

    public void error404() {
        service.get404Error();
    }

    static class Status422ResponseErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(422));
        }

        @Override
        public void handleError(URI url, HttpMethod method, ClientHttpResponse response) {
            throw new HttpErrorException(response);
        }
    }

    @ToString
    @AllArgsConstructor
    static class HttpErrorException extends RuntimeException {
        private ClientHttpResponse response;
    }


}

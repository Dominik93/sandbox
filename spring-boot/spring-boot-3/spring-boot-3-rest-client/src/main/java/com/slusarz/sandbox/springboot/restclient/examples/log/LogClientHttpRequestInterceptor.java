package com.slusarz.sandbox.springboot.restclient.examples.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static java.util.stream.Collectors.joining;

@Slf4j
public class LogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        long start = System.currentTimeMillis();
        try {
            ClientHttpResponse response = execution.execute(request, body);
            long time = System.currentTimeMillis() - start;
            return logResponse(response, time);
        } catch (Exception e) {
            long time = System.currentTimeMillis() - start;
            logException(e, time);
            throw e;
        }
    }

    private void logRequest(HttpRequest request, byte[] body) {
        if (body != null && body.length > 0) {
            log.info("Request {} {}, body: {}, headers: {}",
                    request.getMethod(), request.getURI(), new String(body, StandardCharsets.UTF_8), getHeaders(request.getHeaders()));
        } else {
            log.info("Request: {} {}, headers: {}",
                    request.getMethod(), request.getURI(), getHeaders(request.getHeaders()));
        }
    }

    private ClientHttpResponse logResponse(ClientHttpResponse response, long time) throws IOException {
        byte[] responseBody = response.getBody().readAllBytes();
        if (responseBody.length > 0) {
            log.info("Response status: {}, body: {}, headers: {}, time: {} ms",
                    response.getStatusCode(), new String(responseBody, StandardCharsets.UTF_8), getHeaders(response.getHeaders()), time);
        } else {
            log.info("Response status: {}, headers: {}, time: {} ms",
                    response.getStatusCode(), getHeaders(response.getHeaders()), time);
        }

        return new BufferingClientHttpResponseWrapper(response, responseBody);
    }

    private void logException(Exception e, long time) {
        log.info("Error: {}, time: {} ms", e.getMessage(), time);
    }

    private String getHeaders(HttpHeaders headers) {
        return headers.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(joining(" "));
    }

    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;
        private final byte[] body;

        public BufferingClientHttpResponseWrapper(ClientHttpResponse response,
                                                  byte[] body) {
            this.response = response;
            this.body = body;
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(body);
        }

        // Delegate other methods to wrapped response
        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }
}

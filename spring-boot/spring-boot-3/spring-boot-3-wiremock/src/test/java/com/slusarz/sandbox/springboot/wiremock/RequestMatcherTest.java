package com.slusarz.sandbox.springboot.wiremock;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "request-matcher", port = 8000)})
class RequestMatcherTest {

    RestClient restClient = RestClient.builder()
            .messageConverters(List.of(new MappingJackson2HttpMessageConverter()))
            .baseUrl("http://localhost:8000")
            .build();

    @Test
    void shouldMatchResponseBasedOnRequestBody() {
        Request request = new Request();
        request.setValue("OK");

        Response response = restClient.post()
                .uri("/request-matcher").body(request)
                .retrieve().body(Response.class);

        assertThat(response.getMessage()).isEqualTo("OK");
    }

    @Data
    static class Request {
        private String value;
    }

    @Data
    static class Response {
        private String message;
    }
}
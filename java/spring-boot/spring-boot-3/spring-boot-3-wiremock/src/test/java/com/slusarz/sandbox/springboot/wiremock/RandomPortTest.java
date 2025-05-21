package com.slusarz.sandbox.springboot.wiremock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "random-port")})
class RandomPortTest {

    @Value("${wiremock.server.port}")
    private String port;

    @Test
    void shouldMockWithRandomPort() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();

        String response = restClient.get()
                .uri("/random-port")
                .retrieve().body(String.class);

        assertThat(response).isNotNull();
    }

}
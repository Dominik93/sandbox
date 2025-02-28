package com.slusarz.sandbox.springboot.restclient.examples.error;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class ErrorHandlingTest {

    @Autowired
    private ErrorHandling errorHandling;

    @Test
    void shouldThrowHttpErrorException() {
        assertThatThrownBy(() -> errorHandling.error422()).isInstanceOf(ErrorHandling.HttpErrorException.class);
    }

    @Test
    void shouldThrowHttpClientErrorException() {
        assertThatThrownBy(() -> errorHandling.error404()).isInstanceOf(HttpClientErrorException.NotFound.class);
    }

}
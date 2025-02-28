package com.slusarz.sandbox.springboot.restclient.examples.log;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class LogTest {

    @Autowired
    private Log log;

    @Test
    void shouldLogPathParam() {
        assertThatNoException().isThrownBy(() -> log.pathParam("id"));
    }

    @Test
    void shouldLogRequestParam() {
        assertThatNoException().isThrownBy(() -> log.requestParam("id"));
    }


    @Test
    void shouldLogBody() {
        assertThatNoException().isThrownBy(() -> log.body("1"));
    }

}